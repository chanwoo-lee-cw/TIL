#include "unp.h"

// 매우 느린 버전 -- 오직 예시로 작성되었다.
ssize_t readline(int fd, void *vptr, size_t maxlen)
{
    ssize_t n, rc;
    char c, *ptr;

    ptr = vptr;
    for (n = 1; n < maxlen; n++)
    {
    again:
        if ((rc = read(fd, &c, 1)) == 1)
        {
            *ptr++ = c;
            if (c == '\n')
                break;          // 새로운 줄을 저장한다. fget같이
        }
        else if (rc == 0)
        {
            *ptr = 0;
            return (n - 1);     // EOF, n - 1 바이트를 읽는다.
        }
        else
        {
            if (errno == EINTR)
                goto again;
            return (-1);        // error, 에러넘버가 read()에 의해 세팅된다.
        }
    }
    *ptr = 0;                   // fget과 같은 방식으로 null이 종료 시킨다.
    return (n);
}