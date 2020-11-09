# docker 명령어들

## `docker ps`

현재 실행 중인 컨테이너 목록을 출력

```bash
docker ps
# 현재 실행 중인 도커 컨테이너를 모두 출력
docker ps -a
# stop 되어 있는 docker container을 포함해 모두 출력한다.
docker ps -l
# 가장 마지막에 생성된 docker의 정보를 출력
docker ps -n 3
# 가장 마지막에 생성된 3개의 docker 컨테이너 정보를 출력
docker ps -q
# docker container의 ID만 출력한다.
docker ps -s
# docker container의 size도 표기해 준다.
docker ps --no-trunc
# docker conatiner의 생략된 부분도 모두 표기해준다.
# 예를 들면, 전체 ID 값, ... 등으로 생략된 명령어 전체 표기 등을 알려준다.
```

### `docker ps -—filter`

- docker container를 필터링해서 검색할 수 있게 한다.

  ```bash
  docker ps -f "name=example_app"
  # example_app이라는 이름을 가진 docker container 리스트를 출력한다.
  docker ps -f "label=latest"
  # latest라는 라벨을 가진 docker container을 출력한다.
  ```

  `docker ps --filter`

  | Command         | Mean                                                         |
  | --------------- | ------------------------------------------------------------ |
  | id              | 컨테이너 ID                                                  |
  | name            | 컨테이너 이름                                                |
  | label           | Run 등에서 붙힌 라벨                                         |
  | exited          | 종료된 컨테이너의 종료 코드 -f "exited=0" 식으로 표현        |
  | status          | 컨테이너의 상태 created, restarting, running 등              |
  | ancestor        | 이미지를 공유받은 컨테이너를 출력한다.<br />-f "ancestor=strapi/strapi" <image-name>[:<tag>], <image id> 또는 <image@digest> 로 표현 |
  | before or since | 설정 컨테이너를 기준으로 전에 / 이후에 만들어진 컨테이너 확인이 가능하다. <br />`docker ps -a -f "before=fervent_feynman"`<br />`docker ps -a -f "since=fervent_feynman"` |

  

## `docker images`

현재 가지고 있는 docker image list를 출력한다.

```bash
docker images
# docker images 들을 출력
docker images -a
# 모든 docker image를 출력한다. 
# docker image의 소속되어 있는 하위 이미지도 같이 출력
docker images -q
# docker image의 ID 값만 출력
docker images -f
# docker image 검색의 필터
docker images -f "dangling=true"
# dangling 이미지를 출력한다.
# 즉, <none>:<none> 으로 이루어져 있는 이미지를 말한다
```

