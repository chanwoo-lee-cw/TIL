# https://www.acmicpc.net/problem/1012
import sys
sys.setrecursionlimit(10**8)

input = sys.stdin.readline

T = int(input().strip())
way = (
    (-1, 0),
    (0, -1),
    (1, 0),
    (0, 1),
)


def dfs(y, x):
    # print(y,x)
    if field[y][x] != 1:
        return
    else:
        field[y][x] = -1
    result = 1
    for ymove, xmove in way:
        xpos = x + xmove
        ypos = y + ymove
        if xpos < 0 or xpos >= M \
                or ypos < 0 or ypos >= N:
            continue
        dfs(ypos, xpos)


for i in range(T):
    M, N, K = map(int, input().strip().split())
    field = [[0]*M for _ in range(N)]
    cnt = 0
    for _ in range(K):
        x, y = map(int, input().strip().split())
        field[y][x] = 1

    for i in range(N):
        for j in range(M):
            if field[i][j] == 1:
                dfs(i, j)
                cnt += 1
    print(cnt)
