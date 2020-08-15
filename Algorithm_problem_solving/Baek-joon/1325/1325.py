import sys
from collections import deque

input = sys.stdin.readline


def bfs(pos):
    que = deque()
    que.append(pos)
    this_visited = []
    while que:
        pos = que.popleft()
        if pos in this_visited:
            continue
        elif visited[pos]:
            this_visited += child_list[pos]
            continue
        this_visited.append(pos)
        for next in link[pos]:
            if next in this_visited:
                continue
            que.append(next)
    return list(set(this_visited))


if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    link = [[] for _ in range(N+1)]
    for _ in range(M):
        A, B = map(int, input().strip().split())
        link[B].append(A)
    child_list = [[] for _ in range(N+1)]
    child_num = [0] * (N+1)
    visited = [False] * (N+1)
    for i in range(1, N+1):
        linked = bfs(i)
        visited[i] = True
        child_num[i] = len(linked)
        child_list[i] = linked
    maximum = max(child_num[1:])
    for i in range(1, N+1):
        if child_num[i] == maximum:
            print(i, end=' ')
