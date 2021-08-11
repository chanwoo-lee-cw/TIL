# https://www.acmicpc.net/problem/2133
from sys import stdin

input = stdin.readline

"""
dp[n] - 3*n을 1*2의 벽돌로 채우는 경우의 수
재귀를 통해 dp배열을 채운다.
"""
def need_n_tile(dp, n):
    if dp[n] != 0:
        return dp[n]
    elif n % 2 != 0:
        return 0
    elif n == 0:
        dp[n] = 1
    elif n == 2:
        dp[n] = 3
    else:
        dp[n] = need_n_tile(dp, n - 2) * 3
        for i in range(4, n + 1, 2):
            dp[n] += need_n_tile(dp, n - i) * 2
    return dp[n]


if __name__ == "__main__":
    N = int(input().strip())
    dp = [0] * (N + 1)  # dp[n] : n개의 줄
    print(need_n_tile(dp, N))
