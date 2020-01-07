## 백준 4485번 풀이

https://www.acmicpc.net/problem/4485

### 문제

*젤다의 전설 게임에서 화폐의 단위는 루피(rupee)다. 그런데 간혹 '도둑루피'라 불리는 검정색 루피도 존재하는데, 이걸 획득하면 오히려 소지한 루피가 감소하게 된다!*

*젤다의 전설 시리즈의 주인공, 링크는 지금 도둑루피만 가득한 N x N 크기의 동굴의 제일 왼쪽 위에 있다. [0][0]번 칸이기도 하다. 왜 이런 곳에 들어왔냐고 묻는다면 밖에서 사람들이 자꾸 "젤다의 전설에 나오는 녹색 애가 젤다지?"라고 물어봤기 때문이다. 링크가 녹색 옷을 입은 주인공이고 젤다는 그냥 잡혀있는 공주인데, 게임 타이틀에 젤다가 나와있다고 자꾸 사람들이 이렇게 착각하니까 정신병에 걸릴 위기에 놓인 것이다.*

*하여튼 젤다...아니 링크는 이 동굴의 반대편 출구, 제일 오른쪽 아래 칸인 \[N-1][N-1]까지 이동해야 한다. 동굴의 각 칸마다 도둑루피가 있는데, 이 칸을 지나면 해당 도둑루피의 크기만큼 소지금을 잃게 된다. 링크는 잃는 금액을 최소로 하여 동굴 건너편까지 이동해야 하며, 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.*

*링크가 잃을 수밖에 없는 최소 금액은 얼마일까?*

### 입력

*입력은 여러 개의 테스트 케이스로 이루어져 있다.*

*각 테스트 케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다. (2 ≤ N ≤ 125) N = 0인 입력이 주어지면 전체 입력이 종료된다.*

*이어서 N개의 줄에 걸쳐 동굴의 각 칸에 있는 도둑루피의 크기가 공백으로 구분되어 차례대로 주어진다. 도둑루피의 크기가 k면 이 칸을 지나면 k루피를 잃는다는 뜻이다. 여기서 주어지는 모든 정수는 0 이상 9 이하인 한 자리 수다.*

### 출력

*각 테스트 케이스마다 한 줄에 걸쳐 정답을 형식에 맞춰서 출력한다. 형식은 예제 출력을 참고하시오.*

***



### 풀이

처음에는 매트릭스, 최소값이라는 문제만 보고 다이나믹 프로그래밍 문제인줄 알았다.

하지만, 다이나믹 프로그래밍으로 하니 4방향을 모두 움직일 수 있어서, 즉 위로 가면 이미 한 부분이 바뀔 수도 있으니까. 다이나믹 프로그래밍으로 하는 것은 무리가 있었다.



그래서 이제 최소거리를 찾는 다른 문제, 그래프를 사용해서 해보려고 했다.

일단, 기억하고 있는 최소거리 알고리즘인 다익스트라 알고리즘으로 풀어보려고 했지만, 각각 점으로 이동하는 벡터를 전부 파악해 보려고 했지만, 진짜 너무 많아서 무리라고 생각했다.



그래서 다익스트라 알고리즘을 배열로 삼아서 이차원 배열을 일렬로 편다고 생각하고 4방향으로 탐색해 가는 코드를 작성해 봤지만, 코드가 번번히 터졌다.



그래서 3일간 고민해본 결과 안 풀리길래 인터넷 코드를 찾아 보았다.



그래서 그 해설



```python
from sys import stdin
import heapq

def dijkstra(arr, dist, n):
    ...
    
input = stdin.readline
c = 0
while True:
    c += 1
    n = int(input().rstrip())
    MAXNUM = 9 * n * n
    if not n:
        break
    arr = []
    for _ in range(n):
        arr.append([int(x) for x in input().rstrip().split()])
    dist = [[MAXNUM for _ in range(n)] for __ in range(n)]
    print('Problem {}: {}'.format(c, dijkstra(arr, dist, n)))
```



일단, 입력 시간을 줄이기 위해 `input`을 `stdin.readline`으로 오버라이딩 했다.

그리고 우선순위 힙을 사용하기 위해서 `import heapq` 으로 힙을 import 했다.

c는 문제의 번호를 세는 `count`

`input().rstrip()`인 이유는 문자열의 공백, 개행 문자 등을 없애기 위해서

`MAXNUM = 9 * n * n`인 이유는 각 도둑 루피의 가격의 최대값이 9 이므로 모든 칸이 9 이고 모든 칸을 다 밟았을때의 값이 9 이므로 그 이상의 값은 될 수 없기 때문이다.

`dist = [[MAXNUM for _ in range(n)] for __ in range(n)]`

일단 이것의 의미는 이 시점에선 이해 할 수가 없었기에 일단 다익스트라 알고리즘 부분에서 설명한다.

`for _ in range(n):`

의 _(언더 라인)의 의미는 반복문의 index를 신경 쓰지 않을때 사용한다. 말하자면 iterator 같은건데 예를 들면 arr[i]같이 i가 필요 없이 그냥 반복문을 돌릴 때 사용한다.



다음 dijkstra 알고리즘 부분

```python
def dijkstra(arr, dist, n):
    pq = []
    heapq.heappush(pq, (arr[0][0], 0, 0))
    while pq:
        v, i, j = heapq.heappop(pq)
        if dist[i][j] < v:
            continue
        if i - 1 >= 0:
            if dist[i - 1][j] > v + arr[i - 1][j]:
                dist[i - 1][j] = v + arr[i - 1][j]
                heapq.heappush(pq, (dist[i - 1][j], i - 1, j))
        if i + 1 < n:
            if dist[i + 1][j] > v + arr[i + 1][j]:
                dist[i + 1][j] = v + arr[i + 1][j]
                heapq.heappush(pq, (dist[i + 1][j], i + 1, j))
        if j - 1 >= 0:
            if dist[i][j - 1] > v + arr[i][j - 1]:
                dist[i][j - 1] = v + arr[i][j - 1]
                heapq.heappush(pq, (dist[i][j - 1], i, j - 1))
        if j + 1 < n:
            if dist[i][j + 1] > v + arr[i][j + 1]:
                dist[i][j + 1] = v + arr[i][j + 1]
                heapq.heappush(pq, (dist[i][j + 1], i, j + 1))
    return dist[n - 1][n - 1]
```

여기서 내가 뭘 잘못 생각했는지 드러났는데

난 다익스트라 알고리즘을 풀 때 각 간선을 파악하는게 중요하다고 생각했다.

하지만, 이 코드를 분석해 보며 깨달은 건데, 중요한건 정점이지, 간선은 어디와 이어져 있는지 파악하기 위해서 쓰는거지, 모든 점과 이어져 있다면 굳이 간선을 고려하지 않아도 되는 것이였다.

그래서 dist를 n*n만큼 즉, 정점의 갯수만큼 가중치의 위치를 정했고, 간선이 의미가 없는 코드니 힙인 pq에 간선의 시작점과 끝나는 점이 아닌 현 정점의 위치인 i,j를 저장했다.

그리고 간선 대신 i,j를 기반으로 다익스트라 알고리즘을 돌리면 답이 나온다.



### 전체 코드

```java
from sys import stdin
import heapq

def dijkstra(arr, dist, n):
    pq = []
    heapq.heappush(pq, (arr[0][0], 0, 0))
    while pq:
        v, i, j = heapq.heappop(pq)
        if dist[i][j] < v:
            continue
        if i - 1 >= 0:
            if dist[i - 1][j] > v + arr[i - 1][j]:
                dist[i - 1][j] = v + arr[i - 1][j]
                heapq.heappush(pq, (dist[i - 1][j], i - 1, j))
        if i + 1 < n:
            if dist[i + 1][j] > v + arr[i + 1][j]:
                dist[i + 1][j] = v + arr[i + 1][j]
                heapq.heappush(pq, (dist[i + 1][j], i + 1, j))
        if j - 1 >= 0:
            if dist[i][j - 1] > v + arr[i][j - 1]:
                dist[i][j - 1] = v + arr[i][j - 1]
                heapq.heappush(pq, (dist[i][j - 1], i, j - 1))
        if j + 1 < n:
            if dist[i][j + 1] > v + arr[i][j + 1]:
                dist[i][j + 1] = v + arr[i][j + 1]
                heapq.heappush(pq, (dist[i][j + 1], i, j + 1))
    return dist[n - 1][n - 1]


input = stdin.readline
c = 0
while True:
    c += 1
    n = int(input().rstrip())
    MAXNUM = 9 * n * n
    if not n:
        break
    arr = []
    for _ in range(n):
        arr.append([int(x) for x in input().rstrip().split()])
    dist = [[MAXNUM for _ in range(n)] for __ in range(n)]
    print('Problem {}: {}'.format(c, dijkstra(arr, dist, n)))
```





### 의의

- 다익스트라 알고리즘에서 중요한 것은 정점이다. 간선이 아니다. 간선은 위치를 파악하기 위한 용도
- `rstrip()`으로 오른쪽의 개행 문자등을 지우는 역활
- `for _ in range(n)` index를 파악할 필요가 없을 떄는 i 대신 _로 대체할 수 있다.