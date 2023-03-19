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