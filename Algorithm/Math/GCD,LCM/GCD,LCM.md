# 최대 공약수, 최소 공배수

- 공약수 : 수들이 공유하는 약수의 집합
  - 최대 공약수 : 공약수의 집합 중에 가장 큰 수
- 공배수 : 수들이 공유하는 배수들의 집합 -> 수는 무한히 있으니 집합은 무한하다.
  - 최소 공배수 : 공배수 집합 중에서 가장 작은 수



## 최대 공약수 계산법

### 소인수 분해

24와 18의 최대 공약수를 계산한다고 생각하면, 

180 = 2<sup>2</sup> * 3<sup>2</sup> * 5

72 = 2 <sup>3</sup> * 3<sup>2</sup>

둘의 공통된 소인 수 중에서 지수가 가장 큰 수를 찾아 곱한다.

이 경우에는 2<sup>2</sup> *3<sup>2</sup> = 36이 최대 공약수이다.

### 유클리드 호제법

정수 a와 b에서 a를 b로 나눈 나머지 r이라고 한다면, a,b의 최대 공약수는 b와 r의 최대 공약수와 같다. 이 성질에 따라, b를 r로 나눈 나머지 r`을 구하는 과정을 반복해서 나머지가 0이 되었을때가 a와 b의 최대 공약수 이다.

(a, b) = (b, r)

180과 72를 예시로 삼으면

(180,72) = (72, 36) = (180, 72) = (72,36) = (36, 0)

즉, 최소 공배수는 36이 된다.



## 최소 공배수 계신법

두 수의 소인수 분해를 이용하여 구하는 방법이 있다.

두 소인수 중에서 지수가 가장 큰 수를 찾아 곱하면 된다.

180과 72를  경우를 생각해 보면

180 = 2<sup>2</sup> * 3<sup>2</sup> * 5

72 = 2 <sup>3</sup> * 3<sup>2</sup>

저 둘에서 차수가 제일 큰 수를 찾아 서로 곱한다.

2<sup>3</sup> * 3<sup>2</sup> * 5

즉, 360이 최소 공배수이다.

하지만, 좀더 생각해 본다면

2<sup>3</sup> * 3<sup>2</sup> * 5 = 2<sup>2</sup> * 3<sup>2</sup> * 2 * 5 = 36 * 2 *5 = 36 * (72/36) * (180/36)

즉, 최소 공배수는 각각 수를 최대 공약수를 나눈다음에 그 수 2개와 최대 공약수를 곱하면 답이 나온다.



## 코드

```c++
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
```





## 출처

[위키피디아 - 최대 공약수](https://ko.wikipedia.org/wiki/최대공약수)

[위키피디아 - 최소 공배수](https://ko.wikipedia.org/wiki/최소공배수)