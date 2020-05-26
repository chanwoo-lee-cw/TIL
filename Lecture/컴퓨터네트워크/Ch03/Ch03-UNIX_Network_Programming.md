# 컴퓨터 네트워크
## Ch03. UNIX Network Programming
### Socket Address Structure
#### IPv4 socket address structure

```c
struct in_addr {
    in_addr_t   s_addr;             //32비트 IPv4의 주소
                                    //네트워크 바이트 순서
};

struct sockaddr_in {
    uint8_t         sin_len;        // 구조체의 길이(16)
    sa_family_t     sin_family;     // AF_INET
    in_port_t       sin_port;       // 16bit TCP or UDP 포트 번호
                                    // 네트워크 바이트 순서
    struct in_addr sin_addr;        // 32bit IPv4의 주소
                                    // 네트워크 바이트 순서
    char            sin_zero[8];    // 미사용
};
```
![그림1](./그림1.png)

#### IPv6 socket address structure
```c
struct in6_addr {
    unit8_t   s6_addr[16];          //128비트 IPv6의 주소
                                    //네트워크 바이트 순서
};

#define SIN6_LEN                    // 컴파일 시간 테스트를 위해서

struct sockaddr_in {
    uint8_t         sin6_len;       // 구조체의 길이(16)
    sa_family_t     sin6_family;    // AF_INET
    in_port_t       sin6_port;      // 16bit TCP or UDP 포트 번호
                                    // 네트워크 바이트 순서
    unit32_t        sin6_flowinfo   // 정의되지 않은 흐름 정보
    struct in6_addr sin6_addr;      // 32bit IPv4의 주소
                                    // 네트워크 바이트 순서
    unit32_t        sin6_scope_id;  // 미사용
};
```

#### 일반 소켓 구조
- 지원되는 모든 프로토콜을 처리하기 위해
```c
struct sockaddr {
    unit8_t     sa_len;
    sa_family   sa_family;      // address family : AF xxx 값
    char        sa_data[14];    // 프로토콜 확인 주소
};
```
- 예. int bind(int, struct sockaddr *, socklen_t);
    - bind(sockfd, (struct sockaddr *) &serv, sizeof(serv));

#### 소켓 주소 구조 비교
![그림2](./그림2.png)

###