# https://www.acmicpc.net/problem/1937
from sys import stdin, setrecursionlimit

setrecursionlimit(1000000)
input = stdin.readline

way = ((-1, 0), (1, 0), (0, -1), (0, 1))


def getLongsetMove(y, x):
    if dp[y][x] != -1:
        return dp[y][x]
    dp[y][x] = 1
    for next in way:
        nextY = y + next[0]
        nextX = x + next[1]
        if nextY < 0 or nextX < 0 or nextY >= n or nextX >= n:
            continue
        elif forest[nextY][nextX] <= forest[y][x]:
            continue
        # else
        dp[y][x] = max(dp[y][x], getLongsetMove(nextY, nextX) + 1)
    return dp[y][x]


if __name__ == "__main__":
    n = int(input().strip())
    forest = []
    dp = [[-1] * n for _ in range(n)]
    for i in range(n):
        forest.append(list(map(int, input().strip().split())))

    maxValue = -1
    for i in range(n):
        for j in range(n):
            if dp[i][j] != 1:
                maxValue = max(maxValue, getLongsetMove(i, j))

    print(maxValue)
