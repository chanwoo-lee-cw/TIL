# 1. 싱글톤(Singleton)

## 1.1. 싱글톤 패턴이란

- 애플리케이션이 시작될 때 **최초 한번만** 메모리를 할당하고, 그 메모리에 인스턴스를 만들어 사용하는 디자인 패턴
- 생성자가 여러번 호출 되더라도 실제로 생성되는 객체는 하나고, 최초 생성 이후에 호출된 생성자는 최초에 생성한 객체를 반환한다. (private로 하면 다시 생성 못하게 할 수 있다.)



## 1.2. 싱글톤 패턴을 쓰는 이유

1. 고정된 메모리를 얻으며 한번의 new를 통해 인스턴스를 사용하기 때문에 메모리 낭비를 방지할 수 있음.
2. 싱글톤으로 만들어진 클래스의 인스턴스는 전역 인스턴스이기 때문에 다른 클래스의 인스턴스들이 데이터를 공유하기 쉽다.
3. 공통된 객체를 여러개 생성해서 사용하는 DBCP(DataBase Connection Pool)와 같은 상황에서 많이 사용된다.
4. 인스턴스가 한개만 존재하는 것을 보증하고 싶을 때 사용



## 1.3. 문제점

- 너무 많은 싱글톤 인스턴스가 선언되면 메모리 소모가 크다.
- 데이터를 자주 공유 시킬 경우 결합도가 높아져 객체 지향 언어의 설계 원칙을 위반
- Garbage Collector가 제대로 작동하기 않는 경우가 있어서 한번만 쓰는 경우에는 메모리 낭비가 심할 수도 있다.
- **멀티쓰레드 환경에서 다중 엑세스로 인한 데드락 현상 발생 가능**



## 1.4. 자바의 싱글턴 패턴

### 1.4.1. Eager Initialization

```java
public class Singleton {
    // Eager Initialization
    private static Singleton uniqueInstance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
      return uniqueInstance; 
    } 
}
```

- Static 키워드의 특징을 사용하여, 클래스 로더가 초기화 되는 시점(즉, 컴파일 시점에 모든게 정해진다.)
- 이른 초기화 방식은 클래스 로더에 의해 클래스가 최초로 로딩 될 때 객체가 생성되기때문에 Thread-safe 합니다.



### 1.4.2. Lazy Initialization with synchronized

```java
public class Singleton {
    private static Singleton uniqueInstance;

    private Singleton() {}

    // Lazy Initailization
    public static synchronzied Singleton getInstance() {
      if(uniqueInstance == null) {
         uniqueInstance = new Singleton();
      }
      return uniqueInstance;
    }
}
```

- 컴파일 시점이 아닌, 인스턴스가 필요한 시점에 요청
  - 즉, 런타임 시점에 생성되는 동적 바인딩 형태
- 마찬가지로 Thread-safe 하지만, synchronized를 사용하면 속도가 느리다.
  - getInstance매서드의 속도가 중요하지 않은 경우에 사용한다.



### 1.4.3. Lazy Initialization. LazyHolder

```java
public class Singleton {

    private Singleton() {}

    /**
     * static member class
     * 내부클래스에서 static변수를 선언해야하는 경우 static 내부 클래스를 선언해야만 한다.
     * static 멤버, 특히 static 메서드에서 사용될 목적으로 선언
     */
    private static class InnerInstanceClazz() {
        // 클래스 로딩 시점에서 생성
        private static final Singleton uniqueInstance = new Singleton();
    }

    public static Singleton getInstance() {
        return InnerInstanceClazz.instance;
    }
    
}
```

- JVM의 클래스 초기화 과정에서 보장되는 원자적 특성을 이용하여 싱글턴의 초기화 문제에 대한 책임을 JVM에 떠넘긴다.
  - 개발자가 직접 동기화 문제에 대해 코드를 작성하고 문제를 회피하려고 할 때 생기는 복잡한 구조와 비용을 방지.

- holder안에 선언된 instance가 static이기 때문에 클래스 로딩시점에 한번만 호출될 것이며 final을 사용해 다시 값이 할당되지 않도록 만든 방법.
  - 직접 static final로 선언하면 컴파일 시점에 생성 되므로 사용하지 않을때도 로드 된디. inner클래스로 선언하면 getInstance()로 접근할 때 할당된다.
- Thread-safe하다

- **가장 많이 사용하고 일반적인 Singleton 클래스 사용 방법이다.**





----

출처 :

https://jeong-pro.tistory.com/86

[https://medium.com/webeveloper/싱글턴-패턴-singleton-pattern-db75ed29c36](https://medium.com/webeveloper/싱글턴-패턴-singleton-pattern-db75ed29c36)