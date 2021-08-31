# https://www.acmicpc.net/problem/22871
import sys

input = sys.stdin.readline


def find_min_k(n, stone):
    dp = [float("inf")] * n
    # basecase
    dp[0] = 0
    # 점화식
    for i in range(1, n):
        for j in range(1, n):
            if i - j < 0 or j >= dp[i]:
                # 만약 dp[i]보다 길이가 더 멀거나, i-j의 위치가 0보다 작다면
                break
            getMaxJump = max(dp[i - j], j * (1 + abs(stone[i] - stone[i - j])))  # 현재 위치까지 오는데 필요한 최대 K
            dp[i] = min(dp[i], getMaxJump)
    return dp[n - 1]


if __name__ == "__main__":
    N = int(input().strip())
    stone = list(map(int, input().strip().split()))

    print(find_min_k(N, stone))
