## 1. 팩토리 매서트 패턴이란?

> 객체를 생성하기 위해 인터페이스를 정의하지만, 어떤 클래스의 인터페이스를 생성할지에 대한 결정은 서브 클래스가 내리도록한다.

### 1.1. 구조

![그림1](https://user-images.githubusercontent.com/50443940/101377550-4e7eae00-38f5-11eb-9274-1e21a87bb98b.png)

- Product
  - 팩토리 메서드로 생성될 객체의 인터페이스
- ConcreteProduct
  - 필요한 객체가 생성되는 클래스
- Creator
  - 팩토리 메서드를 갖는 클래스
- ConcreteCreator
  - 팩토리 메서드를 구현하는 클래스로 ConcreteProduct 객체를 생성

## 2. 정리

- 객체 생성 처리를 서브 클래스로 분리해 처리하도록 캡슐화 하는 패턴
  - 객체 생성을 코드를 별도의 클래스/메소드로 분리함으로써 객체 생성의 변화에 대비하는데 유용하다.
  - 특정 기능의 구현은 개별 클래스를 통해 제공 되는 것이 바람직한 설계이다.
    - 기능의 변경이나 상황에 따른 기능의 선택은 해당 객체를 생성하는 코드의 변경을 초래
    - 상황에 따라 적절한 객체를 생성하는 코드는 자주 중복될 수 있다.
    - 객체 생성 방식의 변화는 해당하는 모든 코드 부분을 변경해야 하는 문제가 발생한다.
- `Creator`의 서브 클래스에 팩토리 메소드를 정의 하여, 팩토리 메소드 호출로 적절한 `ConcreteProduct` 인스턴스를 반환하게 한다.



### 2.1. 고려할 점

- 팩토리 메소드 패턴의 구현 방법은 크게 2가지
  1. Creator를 추상 클래스로 정의하고, 팩토리 메소드는 abstract로 선언
  2. Creator가 구체 클래스이고, 팩토리 메소드의 기본 구현을 제공하는 방법.
- 팩토리 메소드의 인자를 통해 다양한 Product를 생성하게 한다.
  - 팩토리 메소드에 잘못된 인자가 들어올 경우의 런타임 에러 처리에 대해 고민할 것.
  - Enum 등을 사용하는 것도 고려할 필요가 있다.
- 팩토리 메서드 패턴의 개념과 적용 방법
  1. 객체 생성을 전담하는 별도의 Factory 클래스 이용
     - 스트래티지 패턴과 싱글턴 패턴을 이용한다.
  2. 상속 이용: 하위 클래스에서 적합한 클래스의 객체를 생성
     - 스트래티지 패턴, 싱글턴 패턴과 템플릿 메서드 패턴을 이용한다.



## 3. 예제

### 3.1 예제1

#### 3.1.1. Product interface

```java
interface class Product {
    public abstract void use();
}
```

#### 3.1.2 Concrete Product

```java
class IDCard implements Product {
    private String owner;

    public IDCard(String owner) {
        System.out.println(owner + "의 카드를 만듭니다.");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println(owner + "의 카드를 사용합니다.");
    }

    public String getOwner() {
        return owner;
    }
}
```

#### 3.1.3 Factory

```java
abstract class Factory {
    public final Product create(String owner) {
        Product p = createProduct(owner);
        registerProduct(p);
        return p;
    }
    protected abstract Product createProduct(String owner);
    protected abstract void registerProduct(Product p);
}
```

#### 3.1.4 ConcreteFactory

```java
class IDCardFactory extends Factory {
    private List<String> owners = new ArrayList<>();

    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    protected void registerProduct(Product p) {
        owners.add(((IDCard) p).getOwner());
    }

    public List<String> getOwners() {
        return owners;
    }
}
```



### 3.2. 예제2

#### 3.2.1. Product interface

```java
public interface Greeting {
	public void greet();	
}
```

#### 3.2.1. Concrete Product

```java
public class MorningGreetingImpl implements Greeting{
	@Override
	public void greet() {
		System.out.println("좋은 아침입니다.");
	}
}
```

```java
public class AfternoonGreetingImpl implements Greeting {
	@Override
	public void greet() {
		System.out.println("좋은 오후에요");
	}
}
```

```java
public class EveningGreetingImpl implements Greeting {
	@Override
	public void greet() {
		System.out.println("좋은 저녁이에요.");
	}
}
```

```java
public class NightGreetingImpl implements Greeting {
	@Override
	public void greet() {
		System.out.println("좋은 밤이에요.");
	}
}
```

#### 3.2.3 Factory

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample2.InterFoo;

public class GreetingTest {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("exam1/beans.xml");

		LocalTime ob1 = (LocalTime) factory.getBean("time");
		int time = ob1.getHour();
		Greeting ob2 = null;
		if (6 <= time && time < 12) {
			ob2 = (MorningGreetingImpl) factory.getBean("Morning");
		} else if (12 <= time && time < 17) {
			ob2 = (AfternoonGreetingImpl) factory.getBean("Afternoon");
		} else if (17 <= time && time < 20) {
			ob2 = (EveningGreetingImpl) factory.getBean("Evening");
		} else {
			ob2 = (NightGreetingImpl) factory.getBean("NightNight");
		}
		ob2.greet();
	}
}
```

#### 3.2.4 Bean.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="Morning" class="exam1.MorningGreetingImpl" scope="prototype"/>
	
	<bean id="Afternoon" class="exam1.AfternoonGreetingImpl" scope="prototype"/>
	
	<bean id="Evening" class="exam1.EveningGreetingImpl" scope="prototype"/>
	
	<bean id="Night" class="exam1.NightGreetingImpl" scope="prototype"/>
	
	<bean id="time" class="java.time.LocalTime" factory-method="now"/>
</beans>
```



## 4. 참고

- https://johngrib.github.io/wiki/factory-method-pattern/
- https://gmlwjd9405.github.io/2018/08/07/factory-method-pattern.html