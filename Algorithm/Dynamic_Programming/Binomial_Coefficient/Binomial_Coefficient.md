# Dynamic Programing

## Binomial Coefficient

### 이항 계수란?

이항 계수는 주어진 크기의 (순서 없는) 조합의 가짓수이다.



### 표현

공식은 흔히 보는 <sub>n</sub>C<sub>k</sub> 즉, 조합이다.

자연수 n와 정수 k가 주어졌을때 이항 계수는 ![\textstyle {\binom  nk}](https://wikimedia.org/api/rest_v1/media/math/render/svg/20897631d805059d3e86b791c9d6b96c0f20abf4)는 다음과 같다.

팩토리얼 구하는 것처럼

 <sub>n</sub>C<sub>k</sub> = n!/k!*(n-k)!로 구하는 방법도 있지만, 이 공식은 시간이 지나치게 걸린다.

![{\binom  nk}={\begin{cases}n!/\left(k!(n-k)!\right)&0\leq k\leq n\\0&k<0\\0&k>n\end{cases}}](https://wikimedia.org/api/rest_v1/media/math/render/svg/af575850eec19a2efa2ac71d38c3a4c7af88bf5d)

구하는 공식은 보통 이런 점화식을 사용하게 되는데,



즉, 파스칼의 삼각형을 이용해 구하는 방식이다.

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Pascal%27s_triangle_5.svg/220px-Pascal%27s_triangle_5.svg.png)



### c ++ 코드

https://github.com/chanwoo-lee-cw/TIL/tree/master/Algorithm_problem_solving/Baek-joon/11050

```cpp
#include<iostream>
#include<string.h>

using namespace std;

// 함수 선언
int binomial_coefficient(int n, int k);
int dp[11][11];

int main() {
    int n, k;

    cin >> n >> k;
    // 배열 초기화, memset을 사용 하기 위해서 #include<string.h> 선언 필수
    memset(dp, 0, sizeof(dp));

    cout << binomial_coefficient(n, k) << endl;

    return 0;
}

int binomial_coefficient(int n, int k) {
    if (dp[n][k] != 0)
    // 만약 한번 계산 된 것이라면 결과값 리턴
        return dp[n][k];
    if (n == k || k == 0)
    // 만약 nCk에서 경우의 수가 1개만 있는 경우라면 1 리턴
        dp[n][k] = 1;
    else
    // 이항 계수 공식 nCk = n-1Ck-1 + n-1Ck
        dp[n][k] = (binomial_coefficient(n - 1, k - 1) + binomial_coefficient(n - 1, k));

    return dp[n][k];
}
```



### Python 코드

https://github.com/chanwoo-lee-cw/TIL/tree/master/Algorithm_problem_solving/Baek-joon/11051

```python
import sys
# input 시간을 줄이기 위해 오버라이딩
input = sys.stdin.readline()
# 재귀의 한계를 풀어서 stackoverflow 에러 방지
sys.setrecursionlimit(1000000000)

# 배열을 필요한 만큼 선언하는 동시 0으로 초기화한다.
dp = [[0]*1001 for i in range(1001)]

def binomial_coefficient(n,k) :
    if dp[n][k] != 0 :
        # 이미 한번 계산한 값이라면 그냥 리턴
        return dp[n][k]
    elif k==n or k==0 :
        # 만약 n과 k가 같거나 k가 0이면 1 리턴
        return 1
    else :
        # 나머지의 경우에는 이항 계수 공식으로 빠르게 계산한다.
        dp[n][k] = (binomial_coefficient(n-1,k-1) + binomial_coefficient(n-1,k))%10007
        return dp[n][k]

if __name__ == "__main__":
    n,k = input.split()

    n= int(n)
    k =int (k)

    print(binomial_coefficient(n,k));
```



