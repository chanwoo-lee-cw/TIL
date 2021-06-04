# https://www.acmicpc.net/problem/17069

from sys import stdin

input = stdin.readline

"""
파이프를 (n,n)까지 옮기는 경우의 수를 리턴
"""
def movePipe(n, wall):
    # dp[i][j][k] : i는 세로, j는 가로, k는 파이프의 방향 0 - 가로, 1 - 세로 - 대각성
    dp = [[[0] * (n + 1) for _ in range(n + 1)] for _ in range(3)]
    dp[0][1][2] = 1
    # base case
    for i in range(3, n + 1):
        if wall[1][i] != 1:
            dp[0][1][i] = dp[0][1][i - 1]
    # 점화식
    for i in range(2, n + 1):
        for j in range(1, n + 1):
            # 파이프를 가로로 밀 때
            if wall[i][j] != 1:
                dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1]
            # 파이프를 세로로 밀 때
            if wall[i][j] != 1:
                dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j]
            # 파이프를 대각선으로 밀 때
            if wall[i][j] != 1 and wall[i][j - 1] != 1 and wall[i - 1][j] != 1:
                dp[2][i][j] = dp[0][i - 1][j - 1] + \
                    dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1]

    return dp[0][i][j] + dp[1][i][j] + dp[2][i][j]


if __name__ == "__main__":
    N = int(input().strip())    # 크기
    wall = [[0] * (N + 1) for _ in range(N + 1)]    # 벽의 형태
    for i in range(1, N + 1):
        inputLine = list(map(int, input().strip().split()))
        for j in range(1, N + 1):
            wall[i][j] = inputLine[j - 1]
    print(movePipe(N, wall))
