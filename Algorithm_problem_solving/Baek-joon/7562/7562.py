import sys
from copy import deepcopy
from collections import deque

input = sys.stdin.readline

way = ((2, -1), (2, 1), (1, 2), (-1, 2), (-2, 1), (-2, -1), (1, -2), (-1, -2))


def bfs(y, x):
    que = deque()
    que.append([y, x, 0])
    visited = [[False] * I for _ in range(I)]
    while que:
        y, x, mv = que.popleft()
        if y == dirt[0] and x == dirt[1]:
            return mv
        for to_y, to_x in way:
            mv_y = y + to_y
            mv_x = x + to_x
            if mv_y < 0 or mv_x < 0 or mv_y >= I or mv_x >= I or visited[mv_y][mv_x]:
                continue
            visited[mv_y][mv_x] = True
            que.append([mv_y, mv_x, mv + 1])
    return mv

if __name__ == "__main__":
    T = int(input().strip())
    for _ in range(T):
        I = int(input().strip())
        curr = list(map(int, input().strip().split()))
        dirt = list(map(int, input().strip().split()))
    
        print(bfs(curr[0],curr[1]))