## 백준 11060번 문제 풀이

https://www.acmicpc.net/problem/11051

### 문제

*자연수 N과 정수 K가 주어졌을 때 이항 계수 <img src="./그림1.png" alt="그림1" style="zoom:25%;" />를 10,007로 나눈 나머지를 구하는 프로그램을 작성하시오.*

### 입력

*첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ K ≤ N)*



### 출력

<img src="./그림1.png" alt="그림1" style="zoom:25%;" />를 10,007로 나눈 나머지를 출력한다.*





### 풀이

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Pascal%27s_triangle_5.svg/1280px-Pascal%27s_triangle_5.svg.png" alt="그림" style="zoom:15%;" />



이항 계수는 이렇게 생겼다.

이항 계수 점화식은

![{\binom  nk}={\begin{cases}n!/\left(k!(n-k)!\right)&0\leq k\leq n\\0&k<0\\0&k>n\end{cases}}](https://wikimedia.org/api/rest_v1/media/math/render/svg/af575850eec19a2efa2ac71d38c3a4c7af88bf5d)

이지만, 위 표를 보면 알 수 있듯이 n==k와 같으면 1이고 k가 0 이 되면 1이다.

![{\binom  nk}+{\binom  n{k+1}}={\binom  {n+1}{k+1}}](https://wikimedia.org/api/rest_v1/media/math/render/svg/3027846f5d02235fd0759030edbeae293b76d2e7)

이런 공식이 있다.

즉 이것을 점화식으로 만들면

```pseudocode
binomial_coefficient(n,k) :
	if k==n AND k==1 
		return 1
	else
		binomial_coefficient(n-1,k-1) + binomial_coefficient(n-1,k)
```



그리고 파이썬에서

``` python
if __name__ == "__main__":
```

태그를 넣으면 main 으로 실행 되었을때만 실행 된다는 말을 듣고 코드로 넣어보았다.



### 전체 코드

```python
import sys
input = sys.stdin.readline()
dp = [[0]*1000 for i in range(1000)]

def binomial_coefficient(n,k) :
    if k==n or k==0 :
        return 1
    elif dp[n][k] != 0 :
        return dp[n][k]
    else :
        dp[n][k] = (binomial_coefficient(n-1,k-1) + 
                    binomial_coefficient(n-1,k))%10007
        return dp[n][k]

if __name__ == "__main__":
    n,k = input.split()

    n= int(n)
    k =int (k)

    print(binomial_coefficient(n,k));
```

