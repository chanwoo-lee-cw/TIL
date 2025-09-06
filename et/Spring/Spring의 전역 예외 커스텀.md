# Spring의 전역 예외 커스텀

## 시작하며

개발을 하다보면 커스텀 에러 메세지를 작성하는것은 반쯤 필수가 된다.

그렇지만, 이때 회사의 표준화된 에러 포멧이 있다면 좋겠지만, 이때 종종 내가 만들어야 하는 경우가 있는데 이때 회사 내의 표준화된 상태가 있으면 좋지만, 없으면 어떻게 해야되는지 고민을 하게 된다.

그래서 찾아보니 알게된 형태가 [RFC7807](https://datatracker.ietf.org/doc/html/rfc7807)라는 국제 표준 에러 포멧이 있어서 해당 부분을 구현하는 방법을 조사해보았다.

이를 통해 예외 핸들링에 대해 다시 한번 고민해보고, 다뤄볼 만한 기회가 됬으면 좋겠다.



## RFC 7807

>RFC 7807은 HTTP API에서 문제 상세 정보를 표기하기 위한 표준 문서이다.
>즉, HTTP API에서 에러(문제) 응답을 보낼 때 일관된 JSON 형식의 표준을 정리한 문제이다

HTTP 상태 코드로는 종종 충분한 예외 정보를 전달하지 않기 때문에, 해당 부분을 보완하기 위해 나온 공용 표준이다.

```http
HTTP/1.1 403 Forbidden
Content-Type: application/problem+json
Content-Language: en

{
"type": "https://example.com/probs/out-of-credit",
"title": "You do not have enough credit.",
"detail": "Your current balance is 30, but that costs 50.",
"instance": "/account/12345/msgs/abc",
"balance": 30,
"accounts": ["/account/12345",
                "/account/67890"]
}
```

예제를 보면 알 수 있다.

- `type` (string) : 
  - 에러 유형을 식별하는 URI (ex. `https://example.com/probs/out-of-credit`)
    - 명시하지 않으면 `"about:blank"` 를 기본값으로 사용한다. -> 문제가 HTTP 상태 코드 외에는 추가적인 의미가 없음을 나타낸다.(ex. Bad Request, Not Found) 
  - 반드시 URI(Uniform Resource Identifier)여야 하지만, 실제로는 그 URI가 꼭 존재할 필요는 없고, 문제의 의미와 처리 방식을 알려주기만 하면 된다. - > 전역적으로 충돌 없는 네임스페이스를 보장하기 위해서
- `title` (string)
  - 에러에 대한 짧은 한 줄 설명.
  - 문제 발생시마다 동일한 정보가 와야한다
- `status` (number)
  - HTTP 상태 코드와 동일하다
  - 이 형식을 이해하지 이해하지 못하는 다른 HTTP 소프트웨어에게 동일한 상태 코드를 전달하기 위해 사용한다.
- `detail` (string)
  - 개발자가 보고서 원인을 확인할 수 있을 상세한 메세지
  - 디버깅 정보를 제공하기보단 클라이언트가 문제를 수정할 수 었도록 돕는데 중점을 두어야한다.
  - 단, 소비자는 정보를 얻기 위해 Detail을 분석해서는 안된다 -> 에러가 발생하기 쉬워진다.
- `instance` (string)
  - 문제의 특정 발생을 식별하는 URI 참조입니다. 참조가 해제될 경우 추가 정보를 제공할 수도 있고 제공하지 않을 수도 있다.



개발자는 `type`을 기본 식별자로 사용하며, `title`은 URI의 의미를 인식하지 못하고, 이를 발견할 수 없는 사용자에게만 권장된다.

### 장점

- API마다 다른 에러 구조를 쓰지 않고, 통일된 형태로 분석하기 쉽고, 처리하기 좋다.
- 표준 필드 외에도 서비스 상황에 맞게 추가 필드를 추가함으로써 유연하게 사용가능하다.
  - 예시) `balance`, `accounts` 같은 추가 정보







## 참고 문헌

- [https://datatracker.ietf.org/doc/html/rfc7807](https://datatracker.ietf.org/doc/html/rfc7807)
- [https://velog.io/@choicore/Spring-에서-전역-예외-커스텀-feat.-RFC-7807](https://velog.io/@choicore/Spring-에서-전역-예외-커스텀-feat.-RFC-7807)
- [https://news.hada.io/topic?id=21229](https://news.hada.io/topic?id=21229)