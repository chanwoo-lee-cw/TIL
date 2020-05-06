## 백준 2110 풀이

#https://www.acmicpc.net/problem/1904

### 문제

*지원이에게 2진 수열을 가르쳐 주기 위해, 지원이 아버지는 그에게 타일들을 선물해주셨다. 그리고 이 각각의 타일들은 0 또는 1이 쓰여 있는 낱장의 타일들이다.*

*어느 날 짓궂은 동주가 지원이의 공부를 방해하기 위해 0이 쓰여진 낱장의 타일들을 붙여서 한 쌍으로 이루어진 00 타일들을 만들었다. 결국 현재 1 하나만으로 이루어진 타일 또는 0타일을 두 개 붙인 한 쌍의 00타일들만이 남게 되었다.*

*그러므로 지원이는 타일로 더 이상 크기가 N인 모든 2진 수열을 만들 수 없게 되었다. 예를 들어, N=1일 때 1만 만들 수 있고, N=2일 때는 00, 11을 만들 수 있다. (01, 10은 만들 수 없게 되었다.) 또한 N=4일 때는 0011, 0000, 1001, 1100, 1111 등 총 5개의 2진 수열을 만들 수 있다.*

*우리의 목표는 N이 주어졌을 때 지원이가 만들 수 있는 모든 가짓수를 세는 것이다. 단 타일들은 무한히 많은 것으로 가정하자.*

### 입력

*첫 번째 줄에 자연수 N이 주어진다.(N ≤ 1,000,000)*

### 출력

*첫 번째 줄에 지원이가 만들 수 있는 길이가 N인 모든 2진 수열의 개수를 15746으로 나눈 나머지를 출력한다.*

***



### 풀이

다이나믹 프로그래밍 문제이다.



풀이는 간단한데

예를 들면

1 개 : 1

2개 : 11, 00

3개 : 100, 001, 111

4개 : 1100, 0000, 0011, 1001



이런식 으로 나열 되는데 1개를 붙힐 수 있는 경우는

5개를 만들 수 있는 경우는

4개에서 붙힐 수 있는 경우는 1을 붙히는 경우

즉 : 1100|1, 0000|1, 0011|1, 1001|1

이 있다.



그리고 00이 붙어 있으니 이제 00 을 붙히는 경우의 수도 있다.

그렇다면 이제 3개에서 00을 붙히는 경우도 있다.

100|00 , 001|00, 111|00



즉, n을 만드는 점화식은

dp[n] = dp [n-1] + dp[n-2]

이 되고, 

베이스 케이스는 

dp[1] = 1 

dp[2] = 2

가 된다.



그래서 처음에 코드는 이렇게 짰다.

```python
import sys

input = sys.stdin.readline

if __name__ =="__main__":
    n = int(input().strip())

    dp = [0,1,2]

    for i in range(3,n+1) :
        dp.append((dp[i-1] + dp[i-2])%15746)

    print(dp[n])
```

하지만, 메모리 초과

메모리를 아주 적게 준 문제이다.



그래서 메모리를 줄이기 위해 코드를 고쳤다.



```python
import sys

input = sys.stdin.readline

if __name__ =="__main__":
    n = int(input().strip())

    dp_twostep = 1
    dp_onestep = 2

    for i in range(3,n+1) :
        dp = (dp_onestep + dp_twostep)%15746
        result = dp
        dp_twostep = dp_onestep
        dp_onestep = dp

    print(result)
```







### 전체 코드

```java
import sys

input = sys.stdin.readline

if __name__ =="__main__":
    n = int(input().strip())

    dp_twostep = 1
    dp_onestep = 2

    for i in range(3,n+1) :
        dp = (dp_onestep + dp_twostep)%15746
        result = dp
        dp_twostep = dp_onestep
        dp_onestep = dp

    print(result)
```


