#include<iostream>
#include <algorithm>

using namespace std;

// 유클리드 호제법.
int gcd (int a, int b)
{
    // 유클리드 호제법은 큰 수에서 작은 수를 나눈다음에 나머지를 대입해서
    // 계산하는 것을 반복한다.
    int divisor = 0;
    while (b)
    {
        divisor = a % b;
        if (divisor == 0) 
        {
            divisor = b;
            break;
        }
        a = b;
        b = divisor;
        
    }
    return divisor;
}

// 최소 공배수는 서로의 공동된 약수 * 서로의 공통되지 않은 약수이다.
int lcm(int a, int b)
{
    int divisor = gcd(a,b);

    // int multiple = (a/divisor) * (b/divisor) * divisor
    int multiple = a/divisor * b;

    return multiple;
}

int main () {
    int num1, num2;
    int divisor = 0 , multiple= 0;

    // 일단 num1이 더 클 수 있도록 서로를 바꾼다.
    cin >> num1 >> num2;
    if (num1 < num2) swap(num1 , num2);

    divisor = gcd(num1,num2);
    multiple = lcm(num1,num2);

    cout << divisor << endl;
    cout << multiple << endl;

    return 0;
}