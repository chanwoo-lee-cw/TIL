import sys
from collections import deque

input = sys.stdin.readline


def dfs():
    que = deque()
    visited = [False] * (n+1)
    que.append([find_a, 0])

    while que:
        curr, chon = que.popleft()
        if visited[curr]:
            continue
        elif curr == find_b:
            return chon
        visited[curr] = True
        for nxt in link[curr]:
            if visited[nxt]:
                continue
            que.append([nxt, chon+1])
    return -1

if __name__ == "__main__":
    n = int(input().strip())
    find_a, find_b = map(int, input().strip().split())
    m = int(input().strip())
    link = [[] for _ in range(n+1)]

    for _ in range(m):
        a, b = map(int, input().strip().split())
        link[a].append(b)
        link[b].append(a)

    print(dfs())