# https://www.acmicpc.net/problem/1010
import sys
input = sys.stdin.readline


# 조합은 파스칼의 삼각형을 이용한 다이나믹 프로그램으로 푸는게 제일 빠르다.
def combination(n, r):
    if dp[n][r] != 0:
        return dp[n][r]
    if n == r or r == 0:
        dp[n][r] = 1
        return 1
    else:
        dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r)
        return dp[n][r]


# n개중에서 k개를 순서 상관없이 뽑는 방법
# 조합문제이다.
if __name__ == "__main__":
    n = int(input())
    dp = [[0 for i in range(31)] for i in range(31)]
    for i in range(n):
        n, m = map(int, input().split())
        print(combination(m, n))