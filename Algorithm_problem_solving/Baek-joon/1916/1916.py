from sys import stdin
import heapq

input = stdin.readline


def getShortestRoute(n, route, start, dest):
    que = []
    visited = [float("inf")] * (n + 1)
    heapq.heappush(que, (0, start))
    visited[start] = 0
    while que:
        curr = heapq.heappop(que)
        if curr[1] == dest:
            return curr[0]
        for b, r in route[curr[1]]:
            moveCost = curr[0] + r
            if moveCost >= visited[b]:
                continue
            heapq.heappush(que, (moveCost, b))
            visited[b] = moveCost
    return visited[dest]


if __name__ == '__main__':
    n = int(input().strip())
    m = int(input().strip())
    route = {}
    for i in range(1, n + 1):
        route[i] = []
    for i in range(m):
        a, b, r = map(int, input().strip().split())
        route[a].append((b, r))
    start, dest = map(int, input().strip().split())
    answer = getShortestRoute(n, route, start, dest)
    print(answer)
