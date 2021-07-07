# https://www.acmicpc.net/problem/1495
from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    N, S, M = map(int, input().strip().split())
    volumns = list(map(int, input().strip().split()))
    dp = [[False] * (M + 1) for _ in range(N + 1)]
    answer = -1

    dp[0][S] = True

    for i in range(N):
        for j in range(M + 1):
            if dp[i][j] and 0 <= j + volumns[i] <= M:
                dp[i + 1][j + volumns[i]] = True
            if dp[i][j] and 0 <= j - volumns[i] <= M:
                dp[i + 1][j - volumns[i]] = True

    for i in range(M + 1):
        if dp[N][i]:
            answer = i

    print(answer)
