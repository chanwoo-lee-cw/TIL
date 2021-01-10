## 백준 1699번 풀이

https://www.acmicpc.net/problem/1699

### 문제

*어떤 자연수 N은 그보다 작거나 같은 제곱수들의 합으로 나타낼 수 있다. 예를 들어 11=3<sup>2</sup>+1<sup>2</sup>+1<sup>2</sup>(3개 항)이다. 이런 표현방법은 여러 가지가 될 수 있는데, 11의 경우 11=2<sup>2</sup>+2<sup>2</sup>+1<sup>2</sup>+1<sup>2</sup>+1<sup>2</sup>(5개 항)도 가능하다. 이 경우, 수학자 숌크라테스는 “11은 3개 항의 제곱수 합으로 표현할 수 있다.”라고 말한다. 또한 11은 그보다 적은 항의 제곱수 합으로 표현할 수 없으므로, 11을 그 합으로써 표현할 수 있는 제곱수 항의 최소 개수는 3이다.*

*주어진 자연수 N을 이렇게 제곱수들의 합으로 표현할 때에 그 항의 최소개수를 구하는 프로그램을 작성하시오.*

### 입력

*첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 100,000)*

### 출력

*주어진 자연수를 제곱수의 합으로 나타낼 때에 그 제곱수 항의 최소 개수를 출력한다.*

***

### 풀이

처음에는 간단하게 생각했다.

```python
dp = [0] * (n+1)
# 배열을 원하는 갯수만 목표한 숫자만큼 할당한다.

dp[1] = 1
dp[2] = 2
dp[3] = 3

for i in range(4,n+1) :
    temp = int(i**0.5)
    temp = temp**2
    if(temp == i) :
    	dp[i] = 1
    else :
        dp[i] = dp[temp] + dp[i - temp]

print(dp[n])
```

만약, i이 제곱이라면 바로 dp[i]에 1에 넣는다.

그렇지 않다면 dp[i]에서 가장 가까운 제곱수를 뺀 수의 dp값을 가져온다.

예를 들면

```
i = 5
dp[5] = dp[4] + dp[1]
```

하지만 틀린 방식의 푸는 방법이였다.

반례로는 32가 있는데

왜냐하면

이 방식으로 하면

```
i=32
dp[32] = dp[25] + dp[7] 
	   = dp[25] + dp[4] + dp[3]
```

즉 32 = 5<sup>2</sup> + 4<sup>2</sup> + 1<sup>2</sup> + 1<sup>2</sup> +1<sup>2</sup>

로 dp[32] = 5가 들어가게 되는데

32를 제곱수로 나타내는 가장 작은 경우의 수는

32 = 16 + 16 = 4<sup>2</sup> + 4<sup>2</sup>

dp[32] = 2가 들어가야 한다.



즉, 1에서 부터 하나하나 다 세가야 한다.



```python
import sys

N=int(sys.stdin.readline())

case=[0]*(N+1)
for i in range(1, N+1):
    j=1
    case[i]=i
    while j*j<=i:
        if case[i]>1+case[i-j*j]:
            case[i]=min(case[i], 1+case[i-j*j])
        j=j+1
print(case[N])
```

그럼 이제 i보다 작은 수들을 1부터 하나하나 찾아본다.

하지만 i 까지 전부 찾으면 지나치게 시간이 걸리게 된다.

고로, j*j가 i 이하인 경우만 계속하고,

만약 1+case[i-j*j]인 경우면 1을 더하고 집어 넣는다.

예를 들면

7인 경우라면

7 = 4 + 3 즉 dp[3]에서 1을 더하는 경우까지 계산한다.



### 전체 코드

```python
import sys

N=int(sys.stdin.readline())

case=[0]*(N+1)
for i in range(1, N+1):
    j=1
    case[i]=i
    while j*j<=i:
        if case[i]>1+case[i-j*j]:
            case[i]=min(case[i], 1+case[i-j*j])
        j=j+1
print(case[N])
```



