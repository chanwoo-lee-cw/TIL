import sys

input = sys.stdin.readline


def recursive(a, b):
    # print(a, b)
    if dp[a][b] != float("inf"):
        return dp[a][b]
    elif a == b:
        return 0
    elif a+1 == b:
        dp[a][b] = files[a] + files[b]
        return dp[a][b]
    for i in range(a, b):
        temp_sum = recursive(a, i) + recursive(i+1, b)
        dp[a][b] = min(dp[a][b], temp_sum + sum[b] - sum[a-1])
    return dp[a][b]


if __name__ == "__main__":
    T = int(input().strip())
    for _ in range(T):
        K = int(input().strip())
        files = list(map(int, input().strip().split()))
        sum = [0]*(K+1)
        sum[0] = files[0]
        for i in range(1, K):
            sum[i] = sum[i-1] + files[i]
        dp = [[float("inf")]*(K+1) for _ in range(K+1)]
        print(recursive(0, K-1))
