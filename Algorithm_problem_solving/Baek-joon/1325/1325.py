import sys
from collections import deque

input = sys.stdin.readline


def bfs(pos):
    que = deque()
    que.append(pos)
    this_visited = [False] * (N+1)
    while que:
        pos = que.popleft()
        if this_visited[pos]:
            continue
        this_visited[pos] = True
        for next in link[pos]:
            if this_visited[next]:
                continue
            que.append(next)
    return this_visited.count(True)


if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    link = [[] for _ in range(N+1)]
    for _ in range(M):
        A, B = map(int, input().strip().split())
        link[B].append(A)
    child_num = [0] * (N+1)
    for i in range(1, N+1):
        linked = bfs(i)
        child_num[i] = linked
    maximum = max(child_num[1:])
    for i in range(1, N+1):
        if child_num[i] == maximum:
            print(i, end=' ')
