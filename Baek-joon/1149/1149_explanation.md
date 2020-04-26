## 백준 1149번 풀이

https://www.acmicpc.net/problem/1149

### 문제

*RGB거리에 사는 사람들은 집을 빨강, 초록, 파랑중에 하나로 칠하려고 한다. 또한, 그들은 모든 이웃은 같은 색으로 칠할 수 없다는 규칙도 정했다. 집 i의 이웃은 집 i-1과 집 i+1이고, 첫 집과 마지막 집은 이웃이 아니다.*

*각 집을 빨강으로 칠할 때 드는 비용, 초록으로 칠할 때 드는 비용, 파랑으로 드는 비용이 주어질 때, 모든 집을 칠하는 비용의 최솟값을 구하는 프로그램을 작성하시오.*



### 입력

*첫째 줄에 집의 수 N이 주어진다. N은 1,000보다 작거나 같다. 둘째 줄부터 N개의 줄에 각 집을 빨강으로, 초록으로, 파랑으로 칠하는 비용이 주어진다. 비용은 1,000보다 작거나 같은 자연수이다.*



### 출력

*첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.*

***



### 풀이

다이나믹 프로그래밍 문제이다.

일단 딱 보자마자 첫줄에서 3개중 하나를 정하고, 딱 남은 색 2가지를 보고 다시 구하고 그 다음 2가지 색을 정하고 하는 방식으로 하면 될거 같다는 생각이 들었다. 

2개로 나눠서 반복해서 그중 최소값을 반납하는 문제. 즉, 재귀로 풀면 된다고 생각했다.



일단 처음은 2개가 아닌 3개이다. 즉 3개를 예외처리, 재귀의 시작점으로 정하면 된다고 생각했다.

```python
min(memo(color, 0, 0, n), memo(color, 0, 1, n), memo(color, 0, 2, n))
```

그럼 이제 각각의 경우의 재귀를 구하면 된다.

점화식을 생각해 보자.

경우의 수는 4가지이다.

1. 집 배열의 끝에 도달했을 때, 즉 재귀의 끝에 도달했을 때
2. 만약 전에 색칠한 집이 빨강일 때
3. 만약 전에 색칠한 집이 초록일 때
4. 만약 전에 색칠한 집이 파랑일 때



일단 1번의 경우이다. 점화식을 고민할 필요가 없다.

```python
if(pos == n-1) :
	return color[pos][coloring]
```

다음은 2번, 3번, 4번 생각은 단순히 생각했다.

각 포지션을 각각 생각하는 것도 의미가 있지만, 어짜피 if 문은 딱 한번 사용될 것이고 뒤의 조건은 더이상 시간을 늘릴 필요가 없는데다, 경우의 수도 3가지 밖에 안되니 그냥 직접 나눠주었다.

```python
if(coloring==0) :
	dp[pos][coloring] = min(memo(color, pos + 1, 1, n), 
                                memo(color, pos + 1, 2, n)) + color[pos][coloring]
    return dp[pos][coloring]
elif (coloring==1) :
    dp[pos][coloring] = min(memo(color, pos + 1, 0, n), 
                                memo(color, pos + 1, 2, n)) + color[pos][coloring]
    return dp[pos][coloring]
else :
    dp[pos][coloring] = min(memo(color, pos + 1, 0, n), 
                                memo(color, pos + 1, 1, n)) + color[pos][coloring]
    return dp[pos][coloring]
```

이렇게 직접 해본 결과는 런타임 에러

이유는 단순하게, 그냥 스택 오버플로우였다. 파이선은 스택 깊이 한계치가 짧으니까.

```python
sys.setrecursionlimit(1000000000)
```

그리고 다음에 낸 것은 이번엔 시간 초과

문제를 다시 보고나니 아차 싶었다.

제한 시간이 0.5초였다.



즉, 이건 재귀 + 다이나믹 프로그래밍 문제였다.

처음에는 봤을때 각각 3방향에서 들어가니 다이나믹 프로그래밍이 성립하지 않을거라고 생각했지만,  끝 부분만 잘라서 생각해 봤을때

49 60 57
13 89 99

49의 색을 칠할 수 있는 경우는 딱 2가지중 한가지이다 89나 99애서 색을 칠했을때 둘 중 한가지이다. 어짜피 49의 위치에서 색의 취소값은 두 경우에서 더 작은 경우를 더하는 경우. 즉, 몇번을 반복해도 동일한 값이 반환된다.



고로, 메모제이션을 추가해주었다.

```python
dp = []
def memo(color, pos, coloring, n) :
    # ...
    if(dp[pos][coloring]!=0) :
        return dp[pos][coloring]
    # ...

if __name__ == "__main__" :
   	# ...
    dp = [[0 for _ in range(3)]for _ in range(n)]
    # ...
```



추가 한 후에 무사히 완료 되었다.




### 전체 코드

```python
import sys
input = sys.stdin.readline
sys.setrecursionlimit(1000000000)

dp = []

def memo(color, pos, coloring, n) :
    if(pos == n-1) :
        return color[pos][coloring]
    if(dp[pos][coloring]!=0) :
        return dp[pos][coloring]
    if(coloring==0) :
        dp[pos][coloring] = min(memo(color, pos + 1, 1, n), 
                                memo(color, pos + 1, 2, n)) + color[pos][coloring]
        return dp[pos][coloring]
    elif (coloring==1) :
        dp[pos][coloring] = min(memo(color, pos + 1, 0, n), 
                                memo(color, pos + 1, 2, n)) + color[pos][coloring]
        return dp[pos][coloring]
    else :
        dp[pos][coloring] = min(memo(color, pos + 1, 0, n), 
                                memo(color, pos + 1, 1, n)) + color[pos][coloring]
        return dp[pos][coloring]

if __name__ == "__main__" :
    n = int(input().strip())
    color = []
    for _ in range(n) :
        temp = input().strip().split()
        for i in range(3) :
            temp[i] = int(temp[i])
        color.append(temp)
    dp = [[0 for _ in range(3)]for _ in range(n)]

    print(min(memo(color, 0, 0, n), memo(color, 0, 1, n), memo(color, 0, 2, n)))
```

