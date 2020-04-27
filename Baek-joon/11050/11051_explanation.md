## 백준 11050번 문제 풀이

https://www.acmicpc.net/problem/11050

### 문제

*자연수 N과 정수 K가 주어졌을 때 이항 계수 <img src="./그림1.png" alt="그림1" style="zoom:25%;" />를 구하는 프로그램을 작성하시오.*

### 입력

*첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 10, 0 ≤ K ≤ N)*



### 출력

 *<img src="./그림1.png" alt="그림1" style="zoom:25%;" />를 출력한다.*





### 풀이

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Pascal%27s_triangle_5.svg/1280px-Pascal%27s_triangle_5.svg.png" alt="그림" style="zoom:15%;" />



이항 계수는 이렇게 생겼다.

이항 계수 점화식은

![{\binom  nk}={\begin{cases}n!/\left(k!(n-k)!\right)&0\leq k\leq n\\0&k<0\\0&k>n\end{cases}}](https://wikimedia.org/api/rest_v1/media/math/render/svg/af575850eec19a2efa2ac71d38c3a4c7af88bf5d)

이지만, 위 표를 보면 알 수 있듯이 n==k와 같으면 1이고 k가 0 이 되면 1이다.

![{\binom  nk}+{\binom  n{k+1}}={\binom  {n+1}{k+1}}](https://wikimedia.org/api/rest_v1/media/math/render/svg/3027846f5d02235fd0759030edbeae293b76d2e7)

이런 공식이 있다.

즉 이것을 점화식으로 만들면

```cpp
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
```

공식은 만약 0이 아니라면 이미 한번 계산된 결과이기 때문에 그 값을 리턴한다.

n==k인 경우와 k==0인 경우의 수는 1개 뿐이니 그대로 1을 리턴

나머지 경우에는 그냥 점화식 그대로 리턴한다.



그리고 memset같은 경우는

`#include<string.h>`을 선언 해야 사용할 수 있다.

### 전체 코드

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

