# 컴퓨터 네트워크
## Ch04. UNIX Network Programming
### Elementary TCP Sockets
#### Socket functions for TCP client/server
![그림1](./그림1.png)

#### Socket Function
``` C
#include <sys/socket.h>
int socket(int family, int type, int protocol);
// Returns: non=negative descriptor if OK, -1 on error
```
![그림2](./그림2.png)

#### connect Function
```c
#include<sys/socket.h>
int connect(int sockfd, const struct sockaddr *servaddr, socklen_r addrlen);
// Return: 0 if OK, -1 on error
```
- sockfd는 소켓 함수에 의해 반환되는 소켓 설명자
- servaddr 과 addrlen은 IP 주소와 서버프로세스의 포트 넘버를 저장하고 있는 포인터이다.
- 필요한 경우 커널은 사용 후 삭제 포트와 src IP 추가자를 모두 선택한다.
- 함수는 오직 연결이 성공하였을때나 에러가 발생했을때만 반환한다.
  - ETIMEDOUT: SYN 세그먼트에 대한 응답 없음
  - ECONNREFUSED: 서버 프로세스가 없으므로(하드 오류) RST 세그먼트 수신
  - EHOSTUNREACH 또는 ENETUNREACH: ICMP "접근할 수 없는 대상" 수신 및 일정 시간 후 응답 없음
##### connect examples
  - local host server
    ``` Linux
    % daytimetcpcli 127.0.0.1
    Sun Jul 27 22:01:51 2003
    ```
  - differnt host server
    ``` Linux
    % daytimetcpcli 192.6.38.100
    Sun Jul 27 22:01:51 2003
    ```
  - no server host in local net
    - no ARP([^주소 결정 프로토콜]) reply and connect time out
    ``` Linux
    % daytimetcpcli 192.6.38.100
    connect error: Connection time out
    ```
  - no server process
    - return RST segment(리셋 세그먼트)
    ``` Linux
    % daytimetcpcli 192.6.38.100
    connect error: Connection refused
    ```
  - unreachable server host
    - ICMP "host unreachable error"
    ``` Linux
    % daytimetcpcli 192.6.38.100
    connect error: No route to host
    ```





----

[^주소 결정 프로토콜]: 네트워크 상에서 IP주소를 물리적 네트워크 주소도 bind시키기 위해 사용되는 프로토콜

