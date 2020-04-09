from sys import stdin
import heapq


def dijkstra(arr, dist, n):
    pq = []
    # 각 위치의 rol값과, col 값을 힙에 저장한다.
    heapq.heappush(pq, (arr[0][0], 0, 0))
    
    while pq:
        # i,j 위치의 가중치를 저장 즉 arr[i][j] 위치의 가중치를 저장한다.
        v, i, j = heapq.heappop(pq)
        # 각자 4방향의 가중치를 고려햐본다.
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
    # 언더라인, 굳이 i,j같은 값을 표현할 필요가 없이 단순 반복문을 사용할때 사용한다.
    for _ in range(n):
        arr.append([int(x) for x in input().rstrip().split()])

    dist = [[MAXNUM for _ in range(n)] for __ in range(n)]
    print('Problem {}: {}'.format(c, dijkstra(arr, dist, n)))