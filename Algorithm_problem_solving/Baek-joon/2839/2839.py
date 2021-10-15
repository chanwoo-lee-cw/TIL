import sys

input = sys.stdin.readline


def getMinEnv(n):
    dp = [[float("inf")] * (n + 1) for _ in range(2)]
    for i in range(n + 1):
        if i % 3 == 0:
            dp[0][i] = i // 3
    for i in range(n + 1):
        dp[1][i] = dp[0][i]
        if i % 5 == 0:
            dp[1][i] = min(dp[1][i], i // 5)
        if i - 5 >= 0 and dp[1][i - 5] != float("inf"):
            dp[1][i] = min(dp[1][i], dp[1][i - 5] + 1)
    return dp[1][i] if dp[1][i] != float("inf") else -1


if __name__ == '__main__':
    n = int(input().strip())
    print(getMinEnv(n))
