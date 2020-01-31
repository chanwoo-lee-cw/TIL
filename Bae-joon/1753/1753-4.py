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
