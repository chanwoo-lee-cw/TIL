## 순서대로 설치
### 에러 : `ld: library not found for -lssl`

https://github.com/brianmario/mysql2/issues/795

- 이것 저것 다 시도해봐서 아닐 수도 있음. 하지만 다 안되다가 마지막 줄 하니 되서 좀 미스테리

1. xcode 커멘드 라인 설치
   
   ```shell
   xcode-select --install
   ```
   
2. Xcode 설치 됬는지 확인

   ~~~shell
   xcode-select --print-path
   ~~~

3. 보통 여기서 끝나는거 같음

4. xcode가 설치 되어 있는 경우

   ~~~shell
   xcode-select --install
   xcode-select: error: command line tools are already installed, use "Software Update" to install updates
   ~~~

5. Openssl 설치

   ~~~shell
   brew install openssl
   # 혹시 모르니 설치후 나오는 명령어들도 쳐보자
   # 그리고 이거 치고 됨
   export LIBRARY_PATH=$LIBRARY_PATH:/usr/local/opt/openssl/lib/
   # 여기서 되면 다행이고 안되면 다른 것도 시도
   ~~~

6. postgreDB 설치 등등.

   ~~~
   brew install postgresql
   ~~~



----

하루 종일 하다 기뻐서 혹시 몰라서 저장