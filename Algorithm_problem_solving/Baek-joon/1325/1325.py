import sys
from collections import deque

input = sys.stdin.readline


def bfs(pos):
    que = deque()
    que.append(pos)
    visited = [False] * (N+1)
    while que:
        curr = que.popleft()
        if visited[curr]:
            continue
        elif curr < pos:
            child_set[pos] = child_set[pos] | child_set[curr]
            for item in child_set[curr]:
                visited[item]
            continue
        child_set[pos].add(curr)
        visited[curr] = True
        for next in link[curr]:
            if visited[next]:
                continue
            que.append(next)


if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    link = [[] for _ in range(N+1)]
    for _ in range(M):
        A, B = map(int, input().strip().split())
        link[B].append(A)
    child_set = [set() for _ in range(N+1)]
    child_num = [0]*(N+1)
    for i in range(1, N+1):
        bfs(i)
        child_num[i] = len(child_set[i])
    maximum = max(child_num)
    for i in range(1, N+1):
        if child_num[i] == maximum:
            print(i, end=' ')