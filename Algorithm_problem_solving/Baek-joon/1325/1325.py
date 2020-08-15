import sys
from collections import deque
sys.setrecursionlimit(10**6)
input = sys.stdin.readline


def dfs(pos):
    if child_num[pos] != 0:
        return child_num[pos]
    elif visited[pos]:
        return 1
    visited[pos] = True
    connects = 1
    for item in link[pos]:
        connects += dfs(item)
    child_num[pos] = connects
    return child_num[pos]


if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    link = [[] for _ in range(N+1)]
    for _ in range(M):
        A, B = map(int, input().strip().split())
        link[B].append(A)
    visited = [False] * (N+1)
    child_num = [0]*(N+1)
    for i in range(1, N+1):
        child_num[i] = dfs(i)
    print(child_num)
        # child_num[i] = len(child_set[i])
    # maximum = max(child_num)
    # for i in range(1, N+1):
    #     if child_num[i] == maximum:
    #         print(i, end=' ')
