#include "unp.h"

static int read_cnt;
static char *read_ptr;
static char read_buf[MAXLINE];

static ssize_t my_read(int fd, char *ptr)
{
    if (read_cnt <= 0)
    {
    again:
        if ((read_cnt = read(fd, read_buff, sizeof(read_buf))) < 0)
        {
            if (errno == EINTR)
                goto again;
            return (-1);
        }
        else if (read_cnt == 0)
            return 0;
        read_ptr = read_buf;
    }

    read_cnt--;
    *ptr = *read_ptr++;
    return 1
}

ssize_t readline(int fd, void *vptr, size_t maxlen)
{
    ssize_t n, rc;
    char c, *ptr;

    ptr = vptr;
    for (n = 1; n < maxlen; n++)
    {
        if ((rc = read(fd, &c, 1)) == 1)
        {
            *ptr++ = c;
            if (c == '\n')
                break; // 새로운 줄을 저장한다. fget같이
        }
        else if (rc == 0)
        {
            *ptr = 0;
            return (n - 1); // EOF, n - 1 바이트를 읽는다.
        }
        else
        {
            return (-1); // error, 에러넘버가 read()에 의해 세팅된다.
        }
    }
    *ptr = 0; // fget과 같은 방식으로 null이 종료 시킨다.
    return (n);
}