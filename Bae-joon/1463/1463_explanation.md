## 백준 1463번 풀이

https://www.acmicpc.net/problem/1463

### 문제

*정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.*

1. *X가 3으로 나누어 떨어지면, 3으로 나눈다.*
2. *X가 2로 나누어 떨어지면, 2로 나눈다.*
3. *1을 뺀다.*

*정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.*

### 입력

*첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.*

### 출력

*첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.*

***

### 풀이

다이나믹 프로그래밍 문제이다

일단, 처음에는 베이스 케이스로 모든 경우의 수의 기준이 되어줄 1,2,3 의 경우를 각자 1로 채워 넣는다.

```python
dp[1] = 0
dp[2] = 1
dp[3] = 1
```



그 다음의 경우에는 제곱수의 경우는 무조건 최소 계산의 횟수는 그 수의 제곱희 횟수이다.

그래서 n이하의 수를 전부 채워줬다.

```python
thrialsqrt = 3
doublesqrt = 2

while(True) :
	temp_2 = doublesqrt*2
	temp_3 = thrialsqrt * 3
	if temp_2 >= n :
		break
    if temp_3 <= n :
        dp[temp_3] = dp[thrialsqrt] + 1
        thrialsqrt = temp_3
    dp[temp_2] = dp[doublesqrt] + 1
    doublesqrt= temp_2
```

각 제곱수를 그 전의 제곱수의 계산 회수의 1을 더한 후에 넣어준다.



그 다음의 점화식을 

각자 3가지의 경우로 2의 배수인 경우 3의 배수인 경우 그리고 그전 값의 +1인 경우를 생각했다.

2와 3의 공통 배수인 6의 배수인 경우도 생각해 봤지만, 2와 3인 둘 중 어느 값으로 나눠도 되길래 6의 배수인 경우는 그냥 2의 배수인 경우로 셌다.

```python
    for i in range(5,n+1) :
        if dp[i]!=0 :
            continue
        if (i%2) == 0 :

            dp[i] = dp[int(i/2)]+1
        elif (i%3) ==0 :
            dp[i] = dp[int(i/3)] +1
        else :
            dp[i] = dp[i-1] + 1
```

결과는 실패

예를 들면 10의 경우가 있다.

이 코드로 하면 4가 나오는데 

즉, 5에서 2배로 올라간 경우로 센다.

하지만 10의 최소는 9에서 +1이 된 3이 최소 연산 수가 된다.



그럼 이제 3가지 경우를 모두 비교해야 한다.

```python
for i in range(5,n+1) :
	if dp[i]!=0 :
		continue
	half = dp[int(i/2)]+1 if i%2 == 0 else float('inf')
	triple = dp[int(i/3)]+1 if i%3 == 0 else float('inf')
	dp[i] = int(min(half,triple,dp[i-1]+1))
```

일단 2로 나눴을 때와 3으로 나눴을때의 경우를 만약 나누어 떨어진다면 그 dp값을 가져오고 만약 나누어 떨어지지 않는다면 시스템상 무조건 최대 값이 나오는 수를 넣어주었다.



그리고 3가지 값을 모두 비교



하지만, 이렇게 하는 경우 컴파일 에러가 떴다.



이유는 아마 배열 선언에서 스택 오버플로우가 일어나는 것 같다.

`dp = [0] * (n+1)` 이런식으로 배열을 선언하는 부분을 `dp = [0] * 1000001`로 초기화 시켜주는 것으로 통과가 되었다.



### 전체 코드

```python
import sys
input = sys.stdin.readline

if __name__ == "__main__" :

    n = int(input().strip())

    dp = [0] * 1000001

    thrialsqrt = 3
    doublesqrt = 2
    dp[1] = 0
    dp[2] = 1
    dp[3] = 1
    while(True) :
        temp_2 = doublesqrt*2
        temp_3 = thrialsqrt * 3
        if temp_2 >= n :
            break
        if temp_3 <= n :
            dp[temp_3] = dp[thrialsqrt] + 1
            thrialsqrt = temp_3
        dp[temp_2] = dp[doublesqrt] + 1
        doublesqrt= temp_2

    for i in range(5,n+1) :
        if dp[i]!=0 :
            continue
        half = dp[int(i/2)]+1 if i%2 == 0 else float('inf')
        triple = dp[int(i/3)]+1 if i%3 == 0 else float('inf')
        dp[i] = int(min(half,triple,dp[i-1]+1))

    print(dp[n])
```





### 알아둬야 할 점

- 파이썬에서 무조건 최대값으로 측정되는 값 : float('inf')

