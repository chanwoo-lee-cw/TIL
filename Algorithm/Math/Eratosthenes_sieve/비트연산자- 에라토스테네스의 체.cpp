#include <iostream>
#include <cstdio>
#include <bitset>
 
#define MAX_N 100
 
using namespace std;
 
int n;
 
unsigned char sieve[(MAX_N + 7) / 8];
 
// K가 소수인지 확인한다.
inline bool isPrime(int k)
{
    return sieve[k >> 3] & (1 << (k & 7));
}
 
// k가 소수가 아니라고 표시한다.
inline void setComposite(int k)
{
    sieve[k >> 3] &= ~(1 << (k & 7));
}
 
// 비트 마스크를 사용하는 에라토스테네스의 체의 구현
// 이 함수를 수행하고 난 뒤, isPrime()을 이용해 각 수가 소수인지 알 수 있다.
void eratosthenes()
{
    memset(sieve, 255, sizeof(sieve));
    setComposite(0);
    setComposite(1);
 
    int sqrtn = int(sqrt(n));
    for (int i = 2; i <= sqrtn; ++i)
        // 이 수가 아직 지워지지 않았다면
        if (isPrime(i))
            // i의 배수 j들에 대해 isPrime[i] = false로 둔다.
            // i*i 미만의 배수는 이미 지워졌으므로 신경 쓰지 않는다.
            for (int j = i*i; j <= n; j += i)
                setComposite(j);
}
 
int main()
{
    n = 100;
    eratosthenes();
    bitset<8> bt;
    
    printf("\nbitset으로 나타낸 소수 확인 방식\n");
    for (int i = 0; i < ((MAX_N + 7) / 8); i++)
    {
        bt = sieve[i];
 
        cout << bt << endl;
    }
 
    printf("\n비트 연산으로 나타낸 소수 확인 방식\n");
    for (int i = 0; i < (MAX_N + 7) / 8; i++)
    {
        for (int j = 0; j < 8; j++)
        {
            if (sieve[i] & (1 << j))
                printf("1");
            else
                printf("0");
        }
 
        cout << endl;
    }
 
    printf("\nisPrime을 이용하여 나타낸 소수 확인 방식\n");
    int cnt = 0;
    for (int i = 0; i < MAX_N; i++)
    {
        isPrime(i) ? printf("1") : printf("0");
 
        cnt++;
        if (cnt % 8 == 0)
            printf("\n");
    }
    
    return 0;
}


#출처: https://www.crocus.co.kr/593 [Crocus]