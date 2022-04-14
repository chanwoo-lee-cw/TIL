# ngrok 사용법

[ngrok - secure introspectable tunnels to localhost](https://ngrok.com/)

## 설치방법

### 공통

[Setup - ngrok](https://dashboard.ngrok.com/get-started/setup)에 로그인 한다.

### 설치법 2가지

1. 파일을 사용해 설치

   1. 직접 파일을 받는다.

      ![스크린샷 2022-04-14 오후 3.33.09.png](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Ffa56ea18-c9d8-406e-896a-c22e379feb78%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-04-14_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.33.09.png?table=block&id=62da44dc-4012-416a-882a-409b99fe1c69&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)

   2. 파일을 받고 나면 하단 파일이 생긴다.

      ![스크린샷 2022-04-14 오후 3.35.35.png](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F45c98b3f-e6fa-42a8-b932-d04f162c6132%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-04-14_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.35.35.png?table=block&id=9d9b0aef-b931-446f-83f9-75f68e11d42e&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)

   3. 토큰 설정(필수는 아닌걸로 보임) - 로그인 필수https://dashboard.ngrok.com/get-started/setup 링크로 가서 authtoken을 알아내서 입력한다.

      ```bash
      ngrok config add-authtoken {{authtoken}}
      ```

   4. 다운받은 해당 경로에 가서 실행을 한다.

      ```bash
      cd /Users/cwlee/Documents
      ./ngrok http 14000
      ```

2. brew를 이용해 설치

   1. 터미널에서 설치한다.

      ```bash
      brew install ngrok
      ```

   2. 토큰 설정(필수는 아닌걸로 보임) - 로그인 필수https://dashboard.ngrok.com/get-started/setup 링크로 가서 authtoken을 알아내서 입력한다. `{{authtoken}}`에 자신의 토큰을 집어 넣는다.

      ```bash
      ngrok config add-authtoken {{authtoken}}
      ```

   3. 터미널을 통해 실행

      ```bash
      ngrok http 14000
      ```

### 공통

![스크린샷 2022-04-14 오후 3.53.34.png](https://ritualforrain.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fb3ff10f8-6e73-4507-baf7-0eed073d0f31%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-04-14_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.53.34.png?table=block&id=33aee7b8-5df8-40a4-bf49-42252cf76f7f&spaceId=3534bf20-7a07-42e1-b6b0-c55e873160bb&width=2000&userId=&cache=v2)

이런식으로 할당 받은 url이 나타난다.

예를 들면 14000 포트로 http 형식으로 보내고 싶으면

```bash
<http://6458-210-123-124-221.ngrok.io>
```

해당 url을 사용하면 된다.

## 공통적인 주의 사항

**포트 번호 확인** : 예를들면 요청을 받고 싶은 플라스크가 14000 포트로 실행 했다면

```bash
ngrok http 14000
```

식으로 실행해야 한다. 은근히 자주하게 되는 실수니 주의할 것