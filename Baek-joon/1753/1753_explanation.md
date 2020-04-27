## 백준 1753번 풀이

https://www.acmicpc.net/problem/1753

### 문제

*방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 단, 모든 간선의 가중치는 10 이하의 자연수이다.*

### 입력

*첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1≤V≤20,000, 1≤E≤300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다. 둘째 줄에는 시작 정점의 번호 K(1≤K≤V)가 주어진다. 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다. 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.*



### 출력

*첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.*



***



### 풀이



한 점에서 다른 모든 점 까지의 최단거리를 구하는 문제,

다익스트라 알고리즘 문제이다.



그래서 다 익스트라 알고리즘의 알고리즘으로 코드를 만들었다.

```python
import sys
import heapq

input = sys.stdin.readline
INF = sys.maxsize

# 메모리 초과

def Dijkstra(vertex, edge, start, edgeList) :
    dij = [INF]*(vertex+1)
    dij[start] = 0
    que = []

    heapq.heappush(que,(0,start));

    while que :
        (weight, position) = heapq.heappop(que);
        if dij[position] < weight :
            continue

        for i in range(1, vertex+1) :
            nextWeight = weight + graph[position][i]

            if(dij[i]>nextWeight) :
                dij[i] = nextWeight
                heapq.heappush(que, (nextWeight, i));

    return dij


if __name__ =="__main__":
    (vertex, edge) = map(int,input().strip().split())

    start = int(input().strip())

    graph = [[INF] * (vertex+1) for _ in range(vertex+1)]

    for _ in range(edge) :
        (u, v, w) = map(int,input().strip().split())
        graph[u][v] = w;

    dij=Dijkstra(vertex, edge, start, graph)

    for i in range(1,vertex+1) :
        print("INF" if dij[i] == INF else dij[i])
```

입력 받은 모든 값을 시작좌표 도착 좌표를 2차원 배열 그래프에 넣은다음에 그 위치를 가중치를 각 그래프에 넣었다.

그리고 다익스트라 알고리즘을 구하는 방식은  우선순위 큐 알고리즘으로 만들었다.



하지만, 결과는 시간 초과



```python
# https://www.acmicpc.net/problem/1753
# 시간 초과
import sys
import heapq

input = sys.stdin.readline
INF = sys.maxsize

def Dijkstra(vertex, start, edgeList) :
    dij = [INF]*(vertex+1)
    visited = [False] * (vertex+1)

    dij[start] = 0

    que = []

    heapq.heappush(que,(0,start, start))
    print("--------------------")
    while que :
        (weight, stPos, desPos) = heapq.heappop(que)
        print(desPos)
        if (visited[desPos]):
            continue
        for i in edgeList :
            if(i[1] == desPos) :
                heapq.heappush(que,i)
                if (dij[i[2]] > dij[i[1]] + i[0]):
                    dij[i[2]] = dij[i[1]] + i[0]
        visited[desPos] = True

    print("--------------------")
    return dij


if __name__ =="__main__":
    (vertex, edge) = map(int,input().strip().split())

    start = int(input().strip())

    edgeList = []

    for _ in range(edge) :
        (u, v, w) = map(int,input().strip().split())
        edgeList.append((w,u,v))

    dij=Dijkstra(vertex, start, edgeList)

    for i in range(1,vertex+1) :
        print("INF" if dij[i] == INF else dij[i])
```

이번엔 배열을 그대로 힙으로 구하는 것으로 해봤다.

이건 시간 초과



그래서 다음 코드는 1번 코드에서 메모리 절약을 위해서 그래프대신 해쉬맵, 파이썬 해서의 딕셔너리 기능을 사용해서 정렬했다. (u,v)를 키 값으로 해서 가중치를 저장했다.

```python
# https://www.acmicpc.net/problem/1753
# 딕셔너리를 이용한 메모리 절약.
import sys
import heapq

input = sys.stdin.readline
INF = sys.maxsize

def Dijkstra(vertex, start, edgeDic) :
    dij = [INF] * (vertex + 1)
    dij[start] = 0
    que = []

    heapq.heappush(que, (0, start));

    while que:
        (weight, position) = heapq.heappop(que);
        if dij[position] < weight:
            continue

        for i in range(1, vertex + 1):
            if (position,i) in edgeDic :
                nextWeight = weight + edgeDic[(position,i)]

                if (dij[i] > nextWeight):
                    dij[i] = nextWeight
                    heapq.heappush(que, (nextWeight, i));

    return dij


if __name__ =="__main__":
    (vertex, edge) = map(int,input().strip().split())

    start = int(input().strip())

    edgeDic = {}

    for _ in range(edge) :
        (u, v, w) = map(int,input().strip().split())
        edgeDic[(u,v)] = w

    dij=Dijkstra(vertex, start, edgeDic)

    for i in range(1,vertex+1) :
        print("INF" if dij[i] == INF else dij[i])

```



하지만 이번엔 시간 초과, 아마 모든 이중 반복문 즉, 모든 정점 사이의 선을 검색하는데 시간을 다 잡아 먹는것 같았다.





```python
# https://www.acmicpc.net/problem/1753
# 시간을 줄이기 위해 연결된 목올을 저장한 리스트
import sys
import heapq

input = sys.stdin.readline
INF = sys.maxsize

def Dijkstra(vertex, start, edgeDic, edgeList) :
    dij = [INF] * (vertex + 1)
    dij[start] = 0
    que = []

    heapq.heappush(que, (0, start));

    while que:
        (weight, position) = heapq.heappop(que);
        if dij[position] < weight:
            continue

        for i in edgeList[position]:
            if (position,i) in edgeDic :
                nextWeight = weight + edgeDic[(position,i)]

                if (dij[i] > nextWeight):
                    dij[i] = nextWeight
                    heapq.heappush(que, (nextWeight, i));
    return dij


if __name__ =="__main__":
    (vertex, edge) = map(int,input().strip().split())

    start = int(input().strip())

    edgeDic = {}
    edgeList = [[] for _ in range(vertex+1)]
    for _ in range(edge) :
        (u, v, w) = map(int,input().strip().split())
        edgeDic[(u,v)] = w
        edgeList[u].append(v)

    dij=Dijkstra(vertex, start, edgeDic, edgeList)

    for i in range(1,vertex+1) :
        print("INF" if dij[i] == INF else dij[i])
```

공백 배열을 vertex갯수만큼 선언하고 각 공백 배열마다 시작점이 같은 그래프를 각자 위치에 박아줘서 연결된 부분만 찾는 방식으로 시간도 절약했다.





### 전체 코드

```python
# https://www.acmicpc.net/problem/1753
# 시간을 줄이기 위해 연결된 목올을 저장한 리스트
import sys
import heapq

input = sys.stdin.readline
INF = sys.maxsize

def Dijkstra(vertex, start, edgeDic, edgeList) :
    dij = [INF] * (vertex + 1)
    dij[start] = 0
    que = []

    heapq.heappush(que, (0, start));

    while que:
        (weight, position) = heapq.heappop(que);
        if dij[position] < weight:
            continue

        for i in edgeList[position]:
            if (position,i) in edgeDic :
                nextWeight = weight + edgeDic[(position,i)]

                if (dij[i] > nextWeight):
                    dij[i] = nextWeight
                    heapq.heappush(que, (nextWeight, i));
    return dij


if __name__ =="__main__":
    (vertex, edge) = map(int,input().strip().split())

    start = int(input().strip())

    edgeDic = {}
    edgeList = [[] for _ in range(vertex+1)]
    for _ in range(edge) :
        (u, v, w) = map(int,input().strip().split())
        edgeDic[(u,v)] = w
        edgeList[u].append(v)

    dij=Dijkstra(vertex, start, edgeDic, edgeList)

    for i in range(1,vertex+1) :
        print("INF" if dij[i] == INF else dij[i])

```

