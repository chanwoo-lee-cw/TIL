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