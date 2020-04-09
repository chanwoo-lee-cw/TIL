## 백준 1351번 풀이

https://www.acmicpc.net/problem/1351



### 문제

*무한 수열 A는 다음과 같다.*

- *A<sub>0</sub> = 1*
- *A<sub>i </sub>= A<sub>⌊i/P⌋ </sub>+ A<sub>⌊i/Q⌋ </sub>(i ≥ 1)*

*N, P와 Q가 주어질 때, AN을 구하는 프로그램을 작성하시오.*

### 입력

*첫째 줄에 3개의 정수 N, P, Q가 주어진다.*

### 출력

*첫째 줄에 AN을 출력한다.*

### 제한

- *0 ≤ N ≤ 10<sup>12</sup>*
- *2 ≤ P, Q ≤ 10<sup>9</sup>*

***

### 풀이

다이나믹 프로그래밍 문제로, 처음부터

베이스 케이스와 점화식이 주어져 있는 문제이다.



하지만 입력이 

0 ≤ N ≤ 10<sup>12</sup>

2 ≤ P, Q ≤ 10<sup>9</sup>

으로 범위가 굉장히 넓다.

이 범위의 값을 전체를 메모리에 저장하게 된다면 에러가 뜨게 되는데 그 메모리 오버를 나지 않게 해결하는 문제이다.



그렇기 때문에 전체가 아니라 일부분을 저장하는 방식으로 선택했는데

그렇다면 경우의 수를 나눠야 했다.

1. 범위 안의 값인 경우
   1. A<sub>0</sub>인 경우
   2. dp안에 값이 없는 경우
   3. dp안에 값이 있는 경우
2. 범위 밖의 값인 경우



일단

1-1 경우이다

```python
    if(i==0) :
        return 1;
```



그리고 1-2인 경우

```python
    if(i<=MAX) :
        dp[i] = temp1 + temp2
        return dp[i]
```

1-3인 경우를 A<sub>⌊i/P⌋ </sub>, A<sub>⌊i/Q⌋ </sub>

각각 2 케이스에서 리턴 받도록 했다.

```python
    pos1 = int(i / p)
    pos2 = int(i / q)
    if(pos1 <=MAX) :
        if(dp[pos1] != 0) :
            temp1 = dp[pos1]
        else :
            temp1 = memo(pos1)
    else :
        temp1 = memo(pos1)

    if (pos2 <= MAX):
        if(dp[pos2] != 0) :
            temp2 = dp[pos2]
        else :
            temp2 = memo(pos2)
    else :
        temp2 = memo(pos2)
```



2번인 경우

```
    else :
        return temp1 + temp2
```



범위 값을 세세하게 조정했어야 하는데 처음에 500 부터 시작했는데 시간 초과

결국 500000까지 가서야 시간 초과가 나지 않았다.



### 전체 코드

```python
import sys
input = sys.stdin.readline

MAX = 500000

n,p,q = map(int,input().strip().split())

def memo(i) :
    if(i==0) :
        return 1;
    pos1 = int(i / p)
    pos2 = int(i / q)
    if(pos1 <=MAX) :
        if(dp[pos1] != 0) :
            temp1 = dp[pos1]
        else :
            temp1 = memo(pos1)
    else :
        temp1 = memo(pos1)

    if (pos2 <= MAX):
        if(dp[pos2] != 0) :
            temp2 = dp[pos2]
        else :
            temp2 = memo(pos2)
    else :
        temp2 = memo(pos2)
    if(i<=MAX) :
        dp[i] = temp1 + temp2
        return dp[i]
    else :
        return temp1 + temp2

if __name__ == "__main__" :
    dp = [0]*(MAX+1)
    print(memo(n))
```



