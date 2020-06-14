#include "unp.h"

ssize_t readn(int fd, void *vptr, size_t n)     // n 바이트를 읽어온다.
{
    size_t nleft;
    ssize_t nread;
    char *ptr;

    ptr = vptr;
    nleft = n;
    while (nleft > 0)
    {
        if (nread = read(fd, ptr, nleft) < 0)
        {
            if(errno == EINTR)
                nread =0;       // 만약 에러가 반환 된다면 read를 다시 호출한다.
            else
                return (-1);
        }
        else if (nread == 0)
            break;              // 파일의 끝에 도달한다혐 끝에 도달했다고 호출한다.
        
        nleft -= nread;
        ptr += nread;
    }
    return (n - nleft);         // 0이상을 리턴하게 된다.
}