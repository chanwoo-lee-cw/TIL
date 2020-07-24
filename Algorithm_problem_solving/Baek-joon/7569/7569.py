import sys
from collections import deque
input = sys.stdin.readline
way = ((-1, 0, 0), (1, 0, 0), (0, -1, 0), (0, 1, 0), (0, 0, 1), (0, 0, -1))


if __name__ == "__main__":
    M, N, H = map(int, input().strip().split())
    board = [[[-1] * (M+1) for _ in range(N+1)] for _ in range(H+1)]
    alltomato = 0
    que = deque()

    # 입력 받은 후에 토마토의 갯수를 센다,
    for h in range(H):
        for i in range(N):
            temp = list(map(int, input().strip().split()))
            for j in range(M):
                board[h+1][i+1][j+1] = 0
                alltomato = alltomato + 1
                # 만약 익은 토마토라면 큐에 넣는다.
                if temp[j] == 1:
                    que.append([h+1, i+1, j+1, 0])
                # 토마토가 없다면 토마토의 갯수를 1뺀다
                elif temp[j] == -1:
                    board[h+1][i+1][j+1] = -1
                    alltomato = alltomato - 1

    while que:
        # 시작할때 모든 토마토가 익어 있다면 break
        if alltomato == 0:
            break
        h, y, x, date = que.popleft()
        # 현재 위치의 토마토가 익어 있거나 없으면 continue
        if board[h][y][x] != 0:
            continue
        # 방문한 장소이므로 토마토를 익었다고 표시하고 토마토의 갯수를 1 뺀다.
        board[h][y][x] = 1
        alltomato = alltomato-1
        # 4방향을 모투 탐색하고 return
        for next in way:
            next_x = x + next[0]
            next_y = y + next[1]
            next_h = h + next[2]
            if next_x <= 0 or next_x > M or next_y <= 0 or next_y > N or next_h <= 0 or next_h > H or board[next_h][next_y][next_x] != 0:
                continue
            que.append([next_h, next_y, next_x, date+1])

    # 모든 토마토가 익었다면 걸린 시간 출력, 아니라면 -1 출력
    if alltomato == 0:
        print(date)
    else:
        print(-1)
