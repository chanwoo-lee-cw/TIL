import sys

input = sys.stdin.readline


def maxGetPoint(n: int, stair: list):
    dp = [[0] * 3 for _ in range(n + 1)]
    dp[1][1] = stair[1]
    for i in range(2, n + 1):
        dp[i][1] = max(dp[i - 2][1], dp[i - 2][2]) + stair[i]
        dp[i][2] = dp[i - 1][1] + stair[i]
    return max(dp[n][1], dp[n][2])


if __name__ == "__main__":
    n = int(input().strip())
    stairs = [0] * (n + 1)
    for i in range(n):
        stairs[i + 1] = int(input().strip())
    print(maxGetPoint(n, stairs))
