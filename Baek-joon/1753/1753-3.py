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
