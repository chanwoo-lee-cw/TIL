# crontab

## crontab 이란?

리눅스/유닉스 기반 시스템에서 특정 시간에 주기적으로 특정 프로그램을 자동 실행하기 위한 스케쥴러

## crontab 설치

mac의 경우에는 기본적으로 내재되어 있다. `crontab -e` 를 이용해 크론 설정 파일을 시작해서 사용한다.



리눅스의 경우에는 `apt-get`을 이용해 설치하여 사용한다. 

```bash
apt-get install crontab
```



## crontab 명령어

- `crontab -e`

  - 크론탭을 초기화겸, 설정 입력 파일을 연다. vim 에디터를 기반으로 작동한다.

- `crontab -l`

  - 현재 돌고 있는 크론탭의 내용을 확인한다.

- `crontab -d`

  - 현재 돌고 있는 크론탭을 전부 삭제한다.

  

 ## crontab 설정

```bash
*			*		*		*		*				~
(분0-59)	(시0-23)	일(1-31)	월(1-12)	요일(0-7)		실행할 명령어
```



## 예시

```bash
0 0 * * * curl --location --request GET 'http://127.0.0.1:8080/test'
```

매일 0시 0분에 `http://127.0.0.1:8080/test`에 요청을 하는 크론 생성


```bash
0 0 * * 0 curl --location --request GET 'http://127.0.0.1:8080/test'
```

매주 일요일 자정마다 요청을 생성하는 크론 생성

```bash
* * * * * curl --location --request GET 'http://127.0.0.1:8080/test'
```
매 1분마다 요청을 생성하는 크론 생성


```bash
0 9 * * 1-5 curl --location --request GET 'http://127.0.0.1:8080/test' >> ./cron_http.log 2>&1
```

평일 매 9시마다 `http://127.0.0.1:8080/test`에 요청을 하고, 실행 결과(정상 출력 + 에러 출력)을 ./cron_http.log 파일에 계속 추가해서 기록한다.