# Spring Filter

> **서블릿 필터(Servlet Filter)**의 개념을 기반으로 하며, **HTTP 요청과 응답을 가로채서 전처리/후처리를 수행하는 컴포넌트**

## Filter란

Spring에서 Web Application에서 관리되는 영역으로 HTTP 요청을 컨트롤러에 전달하기 전에 처리할 수 있는 프레임 워크 기능이다.

Filter란 Web 애플리케이션에서 관리되는 영역으로 Client로 부터 오는 요청과 응답에 대해 최초/최종 단계의 위치이며 이를 통해 요청과 응답의 정보를 확인 및 변경, 그리고 처리를 할 수 있다.

주로 보안, 로깅, 인코딩 처리 등의 컨트롤러에 진입하기 전에 공통적인 처리가 필요할 때 사용된다.



## Filter의 처리 흐름



![그림1](file:///Users/chanwoo/Documents/Study/TIL/et/Spring/%EA%B7%B8%EB%A6%BC1.png)



## 참고 문헌

- [https://gardeny.tistory.com/35](https://gardeny.tistory.com/35)
- [https://mangkyu.tistory.com/173](https://mangkyu.tistory.com/173)
- [https://velog.io/@ieejo716/Spring-Filter-Interceptor](https://velog.io/@ieejo716/Spring-Filter-Interceptor)
- [https://velog.io/@thing-zoo/Spring-Filter](https://velog.io/@thing-zoo/Spring-Filter)