import sys
from collections import deque
input = sys.stdin.readline
way = ((-1, 0), (1, 0), (0, -1), (0, 1))


if __name__ == "__main__":
    M, N = map(int, input().strip().split())
    board = [[-1] * (M+1) for _ in range(N+1)]
    alltomato = 0
    que = deque()

    for i in range(N):
        temp = list(map(int, input().strip().split()))
        for j in range(M):
            board[i+1][j+1] = 0
            alltomato = alltomato + 1
            if temp[j] == 1:
                que.append([i+1, j+1, 0])
            elif temp[j] == -1:
                board[i+1][j+1] = -1
                alltomato = alltomato - 1

    while que:
        if alltomato == 0:
            break
        y, x, date = que.popleft()
        if board[y][x] != 0:
            continue
        board[y][x] = 1
        alltomato = alltomato-1
        for next in way:
            next_x = x + next[0]
            next_y = y + next[1]
            if next_x <= 0 or next_x > M or next_y <= 0 or next_y > N or board[next_y][next_x] != 0:
                continue
            que.append([next_y, next_x, date+1])

    if alltomato == 0:
        print(date)
    else:
        print(-1)
