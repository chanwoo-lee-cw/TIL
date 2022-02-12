# Adapter Pattern

분류: Design Pattern
소분류: Structural Patterns
작성일시: February 7, 2022 6:11 PM
최종 편집일시: February 8, 2022 9:31 PM

## 0. 목차

## 1.요약

> 어댑터 패턴(Adapter pattern)은 클래스의 인터페이스를 사용자가 기대하는 다른 인터페이스로 변환하는 패턴으로, 호환성이 없는 인터페이스 때문에 함께 동작할 수 없는 클래스들이 함께 작동하도록 해준다.
> 

## 2. 의의

어댑터 패턴은 하나의 인터페이스로 모든걸 구현하기 힘들기 때문에 사용한다.

기존 인터페이스가 이제 실제 서비스와 맞지 않아서 다시 만들어야 할 때, 새로 만들어야 하는데 이 라이브러리를 다른 곳에서도 사용하고 있을 때, 이 라이브러리를 고치면 다른 곳에도 영향을 끼치게 된다. 이 경우 Adoptor 을 사용하면 기존 라이브러리를 건들지 않고 새로운 기능을 추가시킬 수 있다.

## 3. 장단점

### 3.1 장점

1. 기존 코드를 변경하지 않을 수 있다.
2. 기존 코드를 변경하지 않기 때문에 클래스 재활용성을 증가시킬 수 있다.

### 3.2 단점

1. 구성 요소를 위해 클래스를 증가 시켜야 하기 때문에 복잡도 증가
2. 클래스 Adapter 의 경우 상속을 사용하기 때문에 유연하지 않다.
3. 객체 Adapter 의 경우는 대부분의 코드를 다시 작성해야 하기 때문에 효율적이지 않다.

## 4. 구현

```java
interface Power_110V {
    void trunOn();
}
```

```java
class Bulb_110V implements Power_110V{
    @Override
    public void trunOn() {
        System.out.println("110V Bulb turn on");
    }
}
```

```java
interface Power_220V {
    void trunLight();
}
```

```java
class Bulb_220V implements Power_220V{
    @Override
    public void trunLight() {
        System.out.println("220V Bulb Light on");
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Power_110V bulb_110V = new Bulb_110V();
    }
}
```

이런 식으로 코드가 짜여 있을 때, 110V의 인터페이스에 220V의 전구를 끼우는 방법이다.

둘의 코드가 다르기 때문에(두 전구가 상속하는 인터페이스가 다르고, 두 메서드도 다르다,) 직접 끼워 줄 수 있는 방법은 없다.

### 객체 어댑터

이런 경우에

```java
class Adaptor implements Power_110V {
    Power_220V power_220V;

    public Adaptor(Power_220V power_220V) {
        this.power_220V = power_220V;
    }

    @Override
    public void trunOn() {
        power_220V.trunLight();
    }
}
```

### 클래스

```java
class Adaptor extends Bulb_220V implements Power_110V {

    @Override
    public void trunOn() {
        trunLight();
    }
}
```

### 클라이언트

```java
public class Main {
    public static void main(String[] args) {
        Power_220V bulb_220V = new Bulb_220V();
        Adaptor adaptor = new Adaptor(bulb_220V);

        adaptor.trunOn();
    }
}
```

이런 어댑터를 생성해주면 된다.

220V의 전구를 마치 110V의 전구를 사용하는 것처럼 `turnOn()` 메서드를 통해 킬 수 있게 된다.

## 5. 출처

[어댑터 패턴 - 위키백과, 우리 모두의 백과사전 (wikipedia.org)](https://ko.wikipedia.org/wiki/%EC%96%B4%EB%8C%91%ED%84%B0_%ED%8C%A8%ED%84%B4)

[[Design Pattern] Adapter 패턴 (kscory.com)](https://kscory.com/dev/design-pattern/adapter)