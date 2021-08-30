# https://www.acmicpc.net/problem/22869
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    N, K = map(int, input().strip().split())
    stone = list(map(int, input().strip().split()))
    dp = [False] * N
    dp[0] = True
    for i in range(1, N):
        for j in range(i - K if i - K > 0 else 0, i):
            if dp[j] and (i - j) * (1 + abs(stone[i] - stone[j])) <= K:
                dp[i] = True

    print("YES" if dp[N - 1] else "NO")
