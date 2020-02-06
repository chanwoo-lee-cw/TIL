# Spring

1998년 EJB라는 기술의 Framework의 시초

2005년 정도부터 FrameWork 기반의 개발이 주류를 이루게 된다.

[^FrameWork]: 반제품 형태의 SW, 개발 생산성과 유지보수성이 높다.

Status, Spring, Mybatis(Ibatis)<sub>JDBC</sub>, Hibernate

Spring IoC, Spring JDBC, Spring MVC, Spring Android, Spring Hadoop



## Spring FW

- 고급 API
  - 원하는 기능을 추가 삭제 가능하다

1. Spring IoC ---> Java Application ---> SpringIoc
2. Spring MVC ---> Web 기반 --->  springedu
3. Mybatis --> Web기반

### Spring IoC

- 결합도를 느슨하게 하기 위해서 사용돈다.

  - 결합도를 낮추면 유지보수성이 높은 프로그램을 만들 수 있다. 예를 들면 인터페이스

- IoC

  - IoC

    - `ClassPathXmlApplicationContext("[file]")` 을 통해 컨테이너 객체를 초기화 시킨다.

  - DL

    - 사용자가 직접 Bean을 통해 직접 설정하고 요청하는 형대
    - `(타입명)context.getBean("빈이름")`으로 id를 통해 변수를 담아 사용한다.

  - DI - IoC에서 가장 자주 쓰이는 형태

    - 클래스간에 의존 관계를 판단해서 자동적으로 할당한다.

    1. Construction Injection : 생성자를 통해서 객체 바인딩(의존관계를 연결) - 요청할 때마다 원하는 것을 받는다. 
    2.  Setter Injection : setter메서드를 이용해서 객체 바인딩(의존관계를 연결)  - 미리 세팅해 주고 원하는 것을 받는다.

#### Maven

- 자바용 프로젝트 관리 도구
- 만약 라이브러리를 하나하나 직접 가져오지 않게 해주고, 만약 업데이트가 있다면 자동으로 라이브러리 업데이트 해준다.
- 이곳 저곳 사이트에 나눠져 있는 라이브러를 통합 관리하는 사이트가 있다.

#### autowire

`autowire="byName"` : setter

1. 프로퍼티 명과 동일한 명칭의 빈을 찾아서 해당 객체 주입
2. 없으면 null 주입

`autowire="byType"` : setter

1. 타입으로 찾아서 1개이면 해당 객체 주입
2. 타입으로 찾아서 2개 이상이면 NoUniqueBeanDefinitionException 발생
3. 없으면 null 주입

`autowire="constructor"` : constructor

1. 타입으로 찾아서 1개 이상이면 해당 갱게 주입
2. 타입으로 찾아서 2개 이상이면 매개변수 명과 동일한 id값을 갖는 객체 주입
3. 없으면 null 주입





서블릿 할때 상속하는 것

http seiion의 객체

인액티브 객체

get과 포스트의 차이점

쿼리 문자열이 전달 되었을때 그런게 없을때

ser과 set어트리 부트

jsp내장객체와 EL 내장객체

세션객체 삭제하고 싶을때

수행상의 특징

요청하는 클라이언트의 ip주소 알아내는 방법

Spring Ioc가 무엇이냐

seterinjection 처리하고 싶을때

필터 한문제

스코프 4종류

statement의 설명문

요청방식이 안 맞으면 응답되는 코드 405코드

POL.xml