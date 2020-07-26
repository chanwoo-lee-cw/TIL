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
        # 벽은 부순적이 있느ㄴ지 없는지 저장하는 변수
        y, x, weight, broken = que.popleft()
        if visited.get((y, x, broken)):
            continue
        # 벽을 만났을때, 지금까지 벽을 부순 적이 없다면 벽을 부순다.
        if board[y][x] == 1:
            if not broken:
                broken=True
            else:
                continue
        # 목적지에 도달하였다면 시간을 출력하고 return
        if x == M and y == N:
            print(weight)
            return
        # 벽을 부수고 방문한 경우와 암 부수고 그 장소에 방문한 경우는 다르므로 broken변수도 같이 저장
        visited[(y, x, broken)] = True
        for next in way:
            next_x = x + next[0]
            next_y = y + next[1]
            if next_x <= 0 or next_x > M or next_y <= 0 or next_y > N:
                continue
            que.append([next_y, next_x, weight+1, broken])
    # 모든 반복문이 끝났는데 목적지에 도달하지 못했다면 -1 출력
    print(-1)

if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    board = [[0]*(M+1) for i in range(N+1)]
    for i in range(N):
        temp = input().strip()
        for j in range(M):
            board[i+1][j+1] = int(temp[j])
    BFS()