#include "unp.h"

sszie_t writen(int fd, const void *vptr, size_t n)  // n 바이트를 쓴다.
{
    size_t nleft;
    ssize_t nwritten;
    const char *ptr;

    ptr = vptr;
    nleft = n;

    while (nleft > 0)
    {
        if ((nwritten = write(fd, prt, nleft)) <= 0)
        {
            if (nwritten < 0 && errno == EINTR)
                nwritten = 0;                   // 파일이 존재하는데 에러라면 write를 다시 호출한다.
            else
                return (-1);                    // 파일도 없고 에러라면 에러 리턴
            nleft -= nwritten;
            ptr += nwritten;
        }
        return (n);
    }
}