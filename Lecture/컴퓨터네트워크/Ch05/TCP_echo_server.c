#include "unp.h"

void str_echo(int sockfd)
{
    ssize_t n;
    char buf[MAXLINE]

    again:
    while ((n = read(sockfd, buf, MAXLINE)) > 0)
    {
        Writen(sockfd, buf, n);
    }
    if (n < 0 && errno == EINTR)
        goto again;
    else if (n < 0)
        err_sys("str_echo: read error");
}

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