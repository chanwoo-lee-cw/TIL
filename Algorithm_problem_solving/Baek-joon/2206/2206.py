# https://www.acmicpc.net/problem/2206
import sys
from collections import deque
input = sys.stdin.readline

way = (-1, 0), (1, 0), (0, -1), (0, 1)

def BFS():
    # initial setting
    que = deque()
    que.append([1, 1, 1, False])
    visited = {}

    # Repeat until que is empty
    while que:
        y, x, weight, broken = que.popleft()
        if visited.get((y, x, broken)):
            continue
        if board[y][x] == 1:
            if not broken:
                broken=True
            else:
                continue
        if x == M and y == N:
            print(weight)
            return
        visited[(y, x, broken)] = True
        for next in way:
            next_x = x + next[0]
            next_y = y + next[1]
            if next_x <= 0 or next_x > M or next_y <= 0 or next_y > N:
                continue
            que.append([next_y, next_x, weight+1, broken])
    print(-1)

if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    board = [[0]*(M+1) for i in range(N+1)]
    for i in range(N):
        temp = input().strip()
        for j in range(M):
            board[i+1][j+1] = int(temp[j])
    BFS()