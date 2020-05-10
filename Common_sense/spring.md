# Spring
## 스프링 프레임 워크
- 자바 플랫폼을 위한 오픈 소스 애플리케이션 프레임워크로서 간단히 스프링이라고도 불린다. 동적인 웹사이트를 개발하기 위한 여러가지 특징
- 우리나라 전자정부 표준 프레임 워크 기반 기술로서 쓰이고 있다.

### Spirng IoC
프로그램에서 필요한 객체 생성과 객체 주입을 스프링FM이 한다. 즉, FM이 관리하는 것을 bean이라고 하며 이 일을 담당하는 FM의 구성요소를 IoC라고 한다.

- Spring DI는 객체간 결합도를 느슨하게 하는 스프링 핵심 기술이다.

- spring DI의 컨테이너 초기화
```java
ApplicationContext factory 
		    = new ClassPathXmlApplicationContext("sampleanno01/bean.xml");
```
- DI의 예
```java
UserShow ob1=factory.getBean("myProcess1", UserShow.class);
```

### Spring MVC
- spring mvc의 처리 흐름
    - 스프링 mvc는 프론트 컨트롤러 패턴을 적용한다. 프롵느 컨트롤러 패턴이란, 하나의 핸들러 객체를 통해서 요청을 할당하고, 일관 처리를 작성할 수 있게 하는 개발 패턴
    - 브라우저로 부터 받은 요청은 Dispacher Sevlet이 모두 관리한다.