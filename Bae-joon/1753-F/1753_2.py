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
