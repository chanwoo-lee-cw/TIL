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



필드에 설정된 @Autowired  - Spring FW 전용

1. 타입으로 찾아서 1개이면 해당 객체 주입
2. 타입으로 찾아서 2개 이상이면 변수명과 동일한 id 값을 갖는 객체 주입
3. 없으면 NoSuchBeanDefinitionException 발생
        (required = false 속성을 사용하여 없으면 null 이 되게 지정 가능)
4. @Qualifier(value="xxx")를 추가로 사용해서 변수명이 아닌 다른 이름 지정 가능





필드에 설정된 @Resource  - Java
(1) 변수명과 동일한 id 값을 갖는 빈을 찾아서 해당 객체 주입
(2) 타입으로 찾아서 1개이면 객체 주입
(3) 타입으로 찾아서 2개이상 이면 NoUniqueBeanDefinitionException 발생
(4) 없으면 NoSuchBeanDefinitionException 발생



### MVC

HTML은 Webapp 밑에 리소스에 보관한다.

WEB-INF는 라이브러리 같은 것에 보관 클라이언트가 접근 불관

이 밑에 빈 설정 파일을 spring 디렉토리 안에 보관한다.



기본 세팅

```XML
	<filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
<!-- 이 서버에 있는 모든 페이지는 CharacterEncodingFilter라는 필터를 거쳐라-->
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
<!-- 인코딩이라는 이름이 UTF-8 이라는 값을 저장-->
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```

WEB.XML에 인코딩을 하는 필터를 건다.



```XML
<properties>
		<java-version>1.8</java-version>
		<!-- 1.8로 바꾸자 -->
		<org.springframework-version>5.0.2.RELEASE</org.springframework-version>
		<!-- 5.0.2로 바꾸자 -->
		<org.aspectj-version>1.6.10</org.aspectj-version>
		<org.slf4j-version>1.6.6</org.slf4j-version>
	</properties>
```

POM.XML 내용을 바꾸자.

버전을 3.1.1로 놔두면 멀티 파일 같은 것을 미지원한다.



