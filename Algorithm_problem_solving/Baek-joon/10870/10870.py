# https://www.acmicpc.net/problem/10870
import sys

input = sys.stdin.readline

N = int(input().strip())

dp = [-1] * (N+2)
dp[0] = 0
dp[1] = 1

def fibonachi(n):
    global dp
    if dp[n] != -1:
        return dp[n]
    else:
        dp[n] = fibonachi(n-1) + fibonachi(n-2)
        return dp[n]

print(fibonachi(N))