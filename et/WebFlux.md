# WebFlux

## WebFlux란?

> Spring boot에서 제공하는 비동기, 논블로킹 웹 프레임 워크

즉, 많은 요청을 동시에 처리해야하는 서비스에서, 쓰레드를 더 적게 사용하면서 효율적으로 동작하도록 만든 웹 기술



## WebFlux의 목표

- 비동기 및 이벤트 기반 프로그래밍을 통해 높은 확장성과 성능을 제공하는 것이다.
- 대량의 동시 연결 및 높은 부하에도 효과적으로 대응할 수 있도록 한다.



## MVC(Blocking) vs WebFlux(Non-Blocking)

### Spring MVC

- 요청 1개당 쓰레드 1개 사용한다.
- 하나의 작업이 끝날때까지 쓰레드가 묶여있는다.
- 외부 API 요청시, 응답이 올때까지 쓰레드가 묶여있는다.
- 동시 접속이 많아지면 쓰레드가 늘어나서 서버가 느려진다.
- Tomcat Servlet 기반

### Spring WebFlux

- 요청이 들어와도 쓰레드가 묶이지 않는다.
- 기다릴 필요가 있는 작업은 "콜백 신호"만 받고 쓰레드는 다른 작업 처리
- 적은 쓰레드로 많은 요청 처리한다.
- Reactive Streams 기반 (Mono, Flux)



## WebFlux의 특징

### 반응형 프로그래밍(Reactive Programming)

- 이벤트 중이시며, 쓰레드 대신 데이터 스트림에 초점을 맞춘다.
- 다량의 동시 요청을 처리하는 애플리케이션의 확장성과 응답성을 향상시키는데 효과적이다.

### 비동기 및 비차단

- Asynchronous 및 Non-Blocking I/O 방식을 사용하여 적은 수의 스레드로 많은 수의 동시 요청을 처리할 수 있다.
- Synchronous 및 Blocking을 사용하는 기존 서블릿(Servlet) 기반 Spring Web MVC 방식과 달리, 높은 처리량을 제공

### 함수형 프로그래밍

-  불변 데이터와 순수 함수를 사용하는 함수형 프로그래밍 스타일을 지향한다.
- 람다(Lambda)와 스트림(Stream)과 같은 기능적 추상화를 제공하여 반응형 코드를 보다 간결하고 읽기 쉽게 작성할 수 있다.

### 스프링 부트와의 통합

- Spring Boot 생태계와 완전히 통합되어 있어 강력한 자동 설정 기능을 제공한다.





##  참고 문헌

- [https://m.blog.naver.com/seek316/223311717538](https://m.blog.naver.com/seek316/223311717538)
- [https://techblog.woowahan.com/12903](https://techblog.woowahan.com/12903)