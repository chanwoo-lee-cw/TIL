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