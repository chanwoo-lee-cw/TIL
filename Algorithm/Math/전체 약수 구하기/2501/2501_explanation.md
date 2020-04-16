## 백준 2501

https://www.acmicpc.net/problem/2501

### 문제

*어떤 자연수 p와 q가 있을 때, 만일 p를 q로 나누었을 때 나머지가 0이면 q는 p의 약수이다.* 

*6을 예로 들면*

- *6 ÷ 1 = 6 … 0*
- *6 ÷ 2 = 3 … 0*
- *6 ÷ 3 = 2 … 0*
- *6 ÷ 4 = 1 … 2*
- *6 ÷ 5 = 1 … 1*
- *6 ÷ 6 = 1 … 0*

*그래서 6의 약수는 1, 2, 3, 6, 총 네 개이다.*

*두 개의 자연수 N과 K가 주어졌을 때, N의 약수들 중 K번째로 작은 수를 출력하는 프로그램을 작성하시오.*



### 입력

*첫째 줄에 N과 K가 빈칸을 사이에 두고 주어진다. N은 1 이상 10,000 이하이다. K는 1 이상 N 이하이다.*

### 출력

*첫째 줄에 N의 약수들 중 K번째로 작은 수를 출력한다. 만일 N의 약수의 개수가 K개보다 적어서 K번째 약수가 존재하지 않을 경우에는 0을 출력하시오.*



***

### 풀이

 약수의 갯수를 구해서 그것이 몇 번째 약수가 무엇인지 알아내는 코드이다.

쉬운 문제지만, 여러가지로 이용될만한 코드이므로 풀어볼만 하다고 생각해서 풀게 되었다.

일단 출력해야 되는 범위 전부를 계산할 필요는 없다.

여러가지 고민해 보다가, 일단 절반만 계산하는 것이면 될 것 같아서

범위를 N의 절반만 구하면 된다.

`for i in range(1,int(n/2)+1) :`

그리고 만약 n이 i로 나누어 떨어지게 된다면

i의 값과 n/i의 값을 dp에 저장한다.

```python
    for i in range(1,int(n/2)+1) :
        if(n % i == 0) :
            dp.append(i)
            dp.append(int(n/i))
```

하지만 이런 경우에 만약 n이 1이 들어오게 된다면 문제가 생기게 되는데

1일 경우 반복문의 범위가 1~1의 범위를 구하게 됨으로써 약수가 하나도 들어가지 않게 된다.

그래서 사전에 예외 처리

```python
	if(n == 1) :
        dp.append(1)
```

그리고 마지막으로

```python
	dp.sort()
    if(len(dp)>=k) :
        print(dp[k-1])
    else :
        print(0)
```

들어간 문제를 sort해서 범위 안에 존재한다면 출력하고 아니라면, 0을 출력하면 될거 같지만. 문제가 생기게 된다.



예를들면

n=4, k=3일 경우 문제가 생기게 된다.

왜냐하면 i가 2일 경우 i=2, n/2=2, 즉 2가 중복해서 들어가게 되기 때문이다.

```python
 	dp = list(set(dp))
    dp.sort()
    if(len(dp)>=k) :
        print(dp[k-1])
    else :
        print(0)
```

그래서 중복 제거를 해준다.

물론 넣는 과정에서 같은 수가 나올 경우는 제곱 수일 경우를 체크해서 확인해서 넣어줘도 되긴 하는데, 이번 만큼은 중복 제거 코드를 사용해보고 싶어서 중복 제거를 사용해 주었다.






### 전체 코드

```python
import sys
input = sys.stdin.readline

if __name__=="__main__" :
    n,k = map(int,input().strip().split())
    count = 0
    dp=[]
    i=1
    while i<=int(n/2)+1 :
        if(n % i == 0) :
            dp.append(i)
            dp.append(int(n/i))
        i= i+1

    dp = list(set(dp))
    dp.sort()
    if(len(dp)>=k) :
        print(dp[k-1])
    else :
        print(0)
```



### 추가 코드

#### 약수의 갯수 구하기

```python
# 약수의 갯수만을 구하는 코드
import sys
input = sys.stdin.readline

N = int(input())
cnt = 0
i = 1
while i * i < N:
    if N % i == 0:
        cnt += 2
    i += 1
if N == i * i:
    cnt += 1


print(cnt)

# 출처 : https://cocoon1787.tistory.com/50
```



그리고 만약 약수의 갯수를 구하는 문제가 나왔다면, 이번엔 굳이 n/2까지 갈 필요도 없이 그냥 i*i에서 끊어주면 된다.

왜나하면 약수는 대칭성을 띄기 때문이다.