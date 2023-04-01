# Spring

# Spring Bean

## Java Bean 이란?

bean는 하나 이상의 객체를 하나의 표준화된 객체(빈)로 캡슐화하는 클래스이다. 이 표준화를 통해 bean을 보다 일반적인 방식으로 처리할 수 있어 코드 재사용과 내부 검사가 용이하다. 

자바에서 빈(bean)은 데이터와 동작을 캡슐화하기 위한 특정 디자인 패턴을 준수하는 소프트웨어 구성 요소이다.

## 특징

1. 기본 생성자(public default constructor) - 이는 리플렉션(reflection)을 사용하여 빈을 인스턴스화하기 위해 필요합니다.
2. 게터(getter)와 세터(setter) 메서드를 가진 비공개(private) 속성 - 이를 통해 다른 객체가 속성에 접근하고 수정할 수 있습니다.
3. Serializable 인터페이스 구현 - 이를 통해 빈을 바이트 스트림으로 쉽게 직렬화하여 저장하거나 전송할 수 있습니다.
4. 네이밍 컨벤션(naming conventions) 따르기 - 빈의 게터와 세터 메서드의 이름은 접근하는 속성의 이름과 일치해야 하며, 빈 클래스의 이름은 파스칼 표기법(PascalCase)으로 작성되어야 합니다.

## Spring bean

스프링(Spring)에서 빈(Bean)은 스프링 IoC(Inversion of Control) 컨테이너에 의해 생성되고 관리되는 객체를 말합니다. 즉, 빈은 스프링 컨테이너에 의해 생성, 구성 및 관리되는 클래스의 인스턴스입니다.

스프링 컨테이너는 빈을 생성하고 관리하는 책임을 가지며, 의존성, 초기화 방법, 소멸 방법 등 다양한 속성으로 구성할 수 있습니다. 빈은 XML 구성 파일, 어노테이션 또는 Java 기반 구성 클래스를 통해 정의될 수 있습니다.

빈은 스프링 애플리케이션의 기본 구성 요소이며, 단순한 값 객체부터 복잡한 비즈니스 서비스까지 어떤 유형의 객체도 나타낼 수 있습니다. 스프링 컨테이너를 사용하여 빈을 관리함으로써 개발자는 객체 생성 및 관리와 같은 문제를 걱정할 필요 없이 비즈니스 로직 작성에 집중할 수 있습니다.

## IoC(Inversion of Control)

기존의 소프트웨어 개발 방식에서는 애플리케이션 코드가 의존성을 생성하고 관리하는 책임을 가지고 있었습니다. 그러나 제어 반전 원칙을 통해 이 책임은 컨테이너나 프레임워크와 같은 다른 시스템이 관리하도록 바뀝니다.

스프링에서는 컨테이너는 애플리케이션의 객체 라이프사이클을 관리하고, 객체를 생성하고 구성하는 등의 책임을 담당합니다.

IOC를 사용함으로써 장정은 개발자는 비즈니스 로직 작성에 집중할 수 있다. 객체 생성 및 관리와 같은 문제는 스프링 컨테이너에서 처리하므로, 코드를 더 모듈화하고 확장 가능하게 만들 수 있습니다. 또한 IOC는 객체 간의 느슨한 결합을 촉진하므로, 애플리케이션은 더 유연하고 유지보수하기 쉬운 구조로 만들 수 있습니다.

총적으로, IOC는 개발자가 더 나은 품질의 소프트웨어를 작성할 수 있게 해주는 강력한 원칙입니다. 더 모듈화되고 유연하며 유지보수하기 쉬운 코드를 작성할 수 있습니다. 

## Autowired

**`@Autowired`**는 Spring Framework에서 자동으로 의존성을 주입하는 데 사용되는 어노테이션입니다.

**`@Autowired`**를 사용하여 클래스 멤버 (필드, setter 메서드 또는 생성자와 같은)를 주석 처리하면 Spring은 런타임에 해당하는 빈을 자동으로 감지하고 주석이 달린 멤버에 적절한 빈을 주입합니다.

예를 들면

```java
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    // UserRepository에 대한 Setter 메서드
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // UserService 구현 메서드는 간결함을 위해 생략
}

@Repository
public class UserRepositoryImpl implements UserRepository {
    // UserRepository 메서드의 구현은 간결함을 위해 생략
}
```

Spring 컨테이너가 **`UserServiceImpl`** 빈을 생성하고 관리할 때, **`setUserRepository()`**에 대한 **`@Autowired`** 주석을 자동으로 감지하고 적절한 **`UserRepository`** 빈을 주입하여 **`UserServiceImpl`**이 **`UserRepository`** 구현을 수동으로 생성하지 않고도 사용할 수 있도록 합니다.

**`@Autowired`**는 생성자 매개 변수 또는 클래스 필드에 직접 사용될 수 있으며, **`@Qualifier`**와 같은 다른 어노테이션과 결합하여 동일한 유형의 여러 빈이 있는 경우 주입할 빈을 지정할 수 있습니다.

## Dependency Injection

> DI란 객체 자체가 종속성을 생성하는 객체가 아닌, 객체에 존속성을 제공하여, 결합성은 낮추는 디자인 패턴.
객체가 종속성을 만드는 대신 종속성이 서드 파티 구성 요소에 의해 객체를 주입
> 

DI를 통해 객체를 분리할 수 있으며 모듈화, 테스트 및 유지보수가 가능하고, IoC 컨테이너를 사용하여 DI를 구현한다.

스프링은 생성자 주입과 세터 주입 두 가지 유형의 DI를 지원한다. 

생성자 주입은 **객체에 종속성을 전달**하는 것을 포함하는 반변, 세터 주입은 **세터 메서드를 통해 종속성을 전달**하는 것을 포함한다.

### DI의 장점

- Decoupling of components : 응용 프로그램의 구성 요소가 느슨하게 결합되어 있어서 유지 보수가 용이
- Simplified testing: 구성 요소가 분리되어 있기 때문에 분리된 상태에서 테스트 할 수 있으므로 애플리케이션에 대한 유닛 테스트를 쉽게 작성 가능
- Easy configuration: 애플리케이션의 구성이 외부화되어 있으므로 소스코드를 수정하지 않고, 값을 변경 가능

### DI의 예제

```java
public interface OrderRepository {
    public void save(Order order);
}

@Component
public class OrderRepositoryImpl implements OrderRepository {
    public void save(Order order) {
        // Implementation of save method
    }
}

@Component
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
```

`OrderRepository` 를 인터페이스로 선언하고, 이것을 상속받는 `OrderRepositoryImpl`를 선언한다. 그리고 둘 다 `@Component` 어노테이션이 붙어 있는데, 이 어노테이션은 spring에게 라이프사이클 관리와 중속성 주입에 사용할 수 있도록 선언해 주는 역할을 한다.

`OrderService` 클래스에는 `OrderRepository`의 인스턴스를 **매개 변수**로 사용하는 생성자가 있습니다. `@AutoWired` 주석은 `OrderRepository` 인스턴스가 생성될 때 `OrderService` 클래스에 삽입하도록 Spring에 알립니다. 다음은 생성자 주입의 예입니다.

`OrderService`의 `saveOrder` 메서드는 주입된 `OrderRepository` 인스턴스에 대한 저장 메서드를 호출합니다.

DI를 사용함으로써 `OrderService` 클래스를 `OrderRepository` 클래스에서 분리하여 모듈화하고 테스트하기 쉽게 만들었습니다. `OrderService` 클래스를 수정하지 않고도 `OrderRespository` 구현을 다른 구현으로 쉽게 전환할 수 있습니다.

## IoC와 DI

IoC는 제어 흐름들 반전시키는 설계 패턴 및 아키텍쳐 원칙으로, 응용프로그램이 이벤트 흐름을 제어하는 대신 외부 프레임워크 또는 컨테이너가 이벤트 흐름을 제어하게 하는 방식을 말한다.

반면에, DI는 사물에 종속성을 주입하는 것을 다루는 IoC의 구현 방식. DI는 객체가 자체 종속성을 생성하는 것이 아니라 객체의 종속성이 객체에 주입되는 IoC를 구현하는 방법을 말한다.

요컨데, IoC는 원리인 반면에 DI는 IoC를 구현하는데 사용되는 기술.

## Spring Container

Spring 프레임워크에서 Spring 컨테이너는 응용프로그램에서 객체의 라이프사이클을 관리하는 중앙 구성요소이며, IoC(Inversion of Control) 컨테이너라고도 한다.

Spring 컨테이너는 객체를 생성, 구성하고, 수명 주기를 관리하는 역할을 합니다. 컨테이너는 XML 파일, Java 어노테이션 또는 Java 코드 형식의 구성 메타데이터를 사용한다.

Spring 컨테이너가 시작되면 구성 메타데이터를 읽고 정의된 개체를 생성합니다. 그런 다음 컨테이너는 이러한 개체의 종속성을 주입하고 메타데이터에 따라 개체를 구성합니다. 이를 통해 객체를 느슨하게 결합할 수 있으며 테스트 및 유지보수가 용이합니다.

Spring 컨테이너에는 `BeanFactory`와 `ApplicationContext`의 두 가지 유형이 있습니다. `BeanFactory`는 기본 컨테이너이고 `ApplicationContext`는 국제화, 이벤트 전파 및 리소스 로드와 같은 추가 기능을 제공하는 고급 컨테이너입니다.

Spring 컨테이너는 객체의 라이프사이클 관리 외에도 트랜잭션 관리, 보안 및 캐싱과 같은 다른 서비스도 제공합니다. 스프링 프레임워크의 핵심 구성 요소이며 스프링 기반 응용 프로그램에서 광범위하게 사용됩니다.

## Spring AOP

AOP는 개발자들이 모듈화하고 concerns이라고 불리는 별도의 단위로 캡슐화할 수 있도록 함으로써 소프트웨어 개발에서 cross-cutting concerns 문제를 해결한다. 

cross-cutting concerns는 이러한 어플리케이션 전체에 걸쳐 존재하는 우려 사항 또는 기능이며, 종종 여러 모듈에 걸쳐 차단되어 전통적인 객체 지향 프로그래밍(OOP) 패러다임 내에서 관리하고 유지하기 어렵다. cross-cutting concerns의 예로는 로깅, 보안, 캐싱 및 트랜잭션 관리가 있다.

Spring AOP는 개발자가 이러한 aspect 사항을 캡슐화하는 측면을 정의하고 join points라고 알려져 있는 어플리케이션 실행에 적용할 수 있도록 합니다. 적절한 join points에 aspect를 주입함으로써 AOP는 코드베이스를 더 모듈화하고, 유지보수 가능하며, 이해하기 쉽게 유지할 수 있도록 도와준다.

### Spring AOP의 중요 개념

1. Aspect: cross-cutting concerns 모듈 단위.
2. Join point: 어플리케이션 실행에서 aspect가 적용될 수 있는 특정 지점
3. Advice: 특정한 join point에서 aspect에 의해 수행되는 작업
4. Pointcut: Advice와 일치해야 하는 결합점을 정의하는 표현.
5. Target object: 하나 이상의 aspects에 의해 Advice 되는 객체
6. AOP proxy: 대상 객체를 둘러싸는 aspect 코드를 실행하는 동적으로 생성된 프록시.
7. Weaving: 컴파일 시간이나, 로드 시간, 런타임에  타겟 객체에 aspect를 적용하는 프로세스. 

스프링 AOP는 프록시 기반 접근법을 사용하는데, 이는 런타임에 동적 프록시를 생성하여 대상 객체에 측면을 짜넣는다는 것을 의미한다. 이를 통해 유연성이 향상되고 기존 애플리케이션과의 통합이 쉬워집니다.