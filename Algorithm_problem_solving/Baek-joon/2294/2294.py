import sys

input = sys.stdin.readline


def solution(n, k, coins):
    dp = [[float("inf")] * (k + 1) for _ in range(n + 1)]
    for j in range(1, k + 1):
        if j % coins[1] == 0:
            dp[1][j] = j // coins[1]
    for i in range(1, n + 1):
        dp[i][0] = 0
    for i in range(2, n + 1):
        for j in range(1, k + 1):
            if j < coins[i]:
                dp[i][j] = dp[i - 1][j]
            else:
                dp[i][j] = min(dp[i][j - coins[i]] + 1, dp[i - 1][j])
    return dp[n][k]


if __name__ == "__main__":
    n, k = map(int, input().strip().split())
    coins = [0]
    for i in range(n):
        coins.append(int(input().strip()))
    coins.sort()
    answer = solution(n, k, coins)
    print(answer if answer != float("inf") else -1)
