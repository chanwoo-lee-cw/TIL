# Dijkstra Algorithm

- 그래프 상의 최단 거리를 구하고 싶을 때 사용하는 알고리즘
- 한 정점에서 다른 모든 정점까지의 최단 거리를 구하는 알고리즘이다.



## 복잡도

- 시간복잡도는 최악의 경우 모든 노드를 방문하는데 V, 방문 할 때마다 d를 업데이트하는데 V의 시간이 소요되므로 O(V^2) 입니다.
- 공간복잡도는 인접 행렬 그래프를 표현하는데 O(V^2)가 사용됩니다.





## 코드

```python
import sys


def dijkstra(K, V, graph):
    INF = sys.maxsize
    # s는 해당 노드를 방문 했는지 여부를 저장하는 변수이다
    s = [False] * V
    # d는 memoization을 위한 array이다. d[i]는 정점 K에서 i까지 가는 최소한의 거리가 저장되어 있다.
    d = [INF] * V
    d[K - 1] = 0

    while True:
        m = INF
        N = -1

        # 방문하지 않은 노드 중 d값이 가장 작은 값을 선택해 그 노드의 번호를 N에 저장한다.
        # 즉, 방문하지 않은 노드 중 K 정점과 가장 가까운 노드를 선택한다.
        for j in range(V):
            if not s[j] and m > d[j]:
                m = d[j]
                N = j

        # 방문하지 않은 노드 중 현재 K 정점과 가장 가까운 노드와의 거리가 INF 라는 뜻은
        # 방문하지 않은 남아있는 모든 노드가 A에서 도달할 수 없는 노드라는 의미이므로 반복문을 빠져나간다.
        if m == INF:
            break

        # N번 노드를 '방문'한다.
        # '방문'한다는 의미는 모든 노드를 탐색하며 N번 노드를 통해서 가면 더 빨리 갈 수 있는 노드가 있는지 확인하고,
        # 더 빨리 갈 수 있다면 해당 노드(노드의 번호 j라고 하자)의 d[j]를 업데이트 해준다.
        s[N] = True

        for j in range(V):
            if s[j]: continue
            via = d[N] + graph[N][j]
            if d[j] > via:
                d[j] = via

    return d

if __name__ == "__main__":
    V, E = map(int, input().split())
    K = int(input())
    INF = sys.maxsize
    graph = [[INF]*V for _ in range(V)]

    for _ in range(E):
        u, v, w = map(int, input().split())
        graph[u-1][v-1] = w

    for d in dijkstra(K, V, graph):
        print(d if d != INF else "INF")
   
#출처 https://wkdtjsgur100.github.io/python-dijkstra/
```

```python
class Dijkstra:
    def __init__(self, v, e):
        self.v = v
        self.e = e
        self.way = {}

    def addLink(self, u, v, w):
        if u in self.way:
            self.way.get(u).append((v, w))
        else:
            self.way[u] = [(v, w)]

    def runDijkstra(self, k):
        dist = [float('inf')] * (self.v + 1)
        dist[k] = 0

        que = []
        heapq.heappush(que, (0, k))

        while que:
            curr = heapq.heappop(que)
            cost = curr[0]
            curr = curr[1]

            if dist[curr] < cost:
                continue
            elif curr not in self.way:
                continue
            for next in self.way.get(curr):
                nextCost = next[1] + cost
                nextMv = next[0]

                if dist[nextMv] > nextCost:
                    dist[nextMv] = nextCost
                    heapq.heappush(que, (nextCost, nextMv))

        return dist

```



### 출처

https://hsp1116.tistory.com/42