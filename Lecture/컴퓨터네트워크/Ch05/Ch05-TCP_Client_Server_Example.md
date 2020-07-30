# 컴퓨터 네트워크
## Ch05. TCP Client/Server Example
### Introduction
#### Simple echo client and server
- 클라이언트는 표준 입력에서 텍스트 줄을 읽고 서버에 쓰기
- 서버는 네트워크 입력을 읽고 다시 클라이언트로 반환한다.
- 클라이언트는 표준 출력에 대해 반환된 것을 읽고, 표준 출력한다.
![그림1](./그림1.png)

### TCP Echo Server
```C
#include "tcp.h"

int main(int srgc, char **argv)
{
    int listenfd, connfd;
    pid_t childpid;
    socklen_t childlen;
    struct sockaddr_in cliaddr, servaddr;

    listenfd = Socket(AF_INEF, SOCK_STREAM, 0);

    bzero(&servaddr, sizeof(servaddr));
    servaddr.sin_familly = AF_INET;
    servaddr.sin_addr.s_adrr = htonl(INADDR_ANY);

    Bind(listenfd, LISTENQ);

    for(;;) {
        clilen = sizeof(cliaddr);
        connfd = Accept(listenfd, (SA *) &cliaddr, &cliaddr);

        if( (childpid = Fork()) == 0)   // child process
        {
            Close(listenfd);    // 리스팅 소켓을 닫는다.
            str_echo(connfd);   // 리퀘스트를 진행한다.
            exit();
        }
        Close(connfd);  // 부모의 연결 소켓을 닫는다.
    }
}
```

### TCP Echo Client
```c
#inlcude "unp.h"

#define MAXLINE 1000000

void str_cli(FILE *fp, int sockfd)
{
    char sendline[MAXLINE], recvline[MAXLINE];

    while ( fgets(sendline, MAXLINE, fp) != NULL)
    {
        Writen(sockfd, sendline, strlen (sendline));

        if(Readline(sockfd, recvline, MAXLINE) == 0)
            err_quit("str_cli; server terminateed permaturely");
        Fputs(recvline, stdout)
    }
    
}

int main(int argc, char **argv)
{
    int sockfd;
    struct sockaddr_in servaddr;

    if (argc != 2)
        err_quit("usage: tcpli <IPaddress>");
    
    sockfd = Socket(AF_INET, SOCK_STREAM, 0);

    bzero(&servaddr, sizeof(servaddr));
    servaddr.sin_family = AF_INET;
    servaddr.sin_port = htons(SERV_PORT);
    inet_pton(AF_INET, argv[1], &servaddr.sin_addr);

    Connect(sockfd, (SA *) &servaddr, sizeof(servaddr));

    str_cli(stdin, sockfd);
    exit(0);
}
```
### Normal Startup
- before starting client in a server
    ![그림2](./그림2.png)

- after starting the client in a server
    ![그림3](./그림3.png)

### Normal Termination
- closing client in a server
    ![그림4](./그림4.png)

- 상위 서버 프로세스가 하위 서버 프로세스를 종료한 후 SIGCHLD 신호를 처리하지 않을 경우 하위 서버 프로세스는 좀비 상태로 이동한다.
    ![그림5](./그림5.png)

### POSIX Signal Handling
- signal은 이벤트가 발생한 프로세스에 대한 알림
  - 비동기식 소프트웨어 인터럽트
  - signal들은 프로세스나 커널에 의해 보내진다.
- signal handler
  - 신호가 도착하면 호출되는 함수
  - sigaction()은 signal handler 와 signal를 매핑한다.
  - 그러나 편의를 위해, 이 책은 그림 5.6에 설명된 신호를 사용한다.
    - Sigfunc *signal(into, Sigfunc *func);
    - void sig_child(in signo);
    - 예: signal handler(SIGCHLD, sig_child);

### Handling SIGCHLD Signals
- Zombie state
  - 좀비 프로세스는 PID, 종료 상태, 리소스 활용률에 대한 정보를 유지한다.
  - 좀비 프로세스는 커널에서 공간을 차지하며 부모 프로프를 종료한 후 삭제될 것이다.
- 좀비 프로세스를 청소하기 전에 부모 프로세스를 wait()나 waitpid()를 호출해 정리해야한다.
- SIGCHLD signal handler ver.1 and output
    ![그림6](./그림6.png)
- handling Interrupted System Call in server
  - 느린 시스템콜은 영원히 차단할 수 있는 시스템함수이다.
    - accept(), read(), ...
  - 프로세스가 signal을 감지하면, 느린 시스템 콜로 EINTR 오류를 반환할 수 있다.
  - 그래서, 우리는 이 문제를 해결해야 한다.
    ![그림7](./그림7.png)
  
### wait and waitpid Functions
- child 의 좀비 process를 정리한다.
  ```C
  #include<sys/wait.h>
  pid_t wait(int *statioc);
  pid_t waitpid(pid_t pid, int *statioc, int options);
  // Both return:process ID if OK, 0 or -1 on error
  ```
    - **wait()** 는 존재하는 children 이 종료될때까지 block 한다.
    - **waitpid()** gives us more control
      - 어떤 프로세스를 wait시킬 것인가(pid; -1은 첫번째 child)
      - 옵션의 WNOHANG: 종료된 하위 항목이 없는 경우 차단하지 않음
- 5개의 연결이 있는 TCP 클라이언트
  ```c
  for(i=0; i<5; i++) {
      sockfd[i] = Socket(AF_INET, SOCK_STREAM, 0);
      
      bzero(&servaddr, sizeof(servaddr));
      servaddr.sin_family = AF_INET;
      servaddr.sin_port = htons(SERV_PORT);
      Inet_pton(AF_INET, argv[1], &servaddr.sin_addr);

      Connect(sockfd[i], (SA *) &servaddr, sizeof(servaddr));
  }

  str_cli(stdin, sockfd[0]);    // do it all

  exit(0);
  ```

- exit()가 호출되고 클라이언트가 종료되는 경우, 5개의 연결을 모두 닫음, 5명의 children을 모두 종료
![그림8](./그림8.png)

- 새로운 클라이언트의 실행 예
![그림9](./그림9.png)

- ps 결과 - 4개의 좀비 프로세스가 남아 있다.
![그림10](./그림10.png)

- reason of