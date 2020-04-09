import sys
input = sys.stdin.readline

dp = [[0 for i in range(31)] for i in range(31)]

def combination(n, r) :
    if dp[n][r]!=0 :
        return dp[n][r]
    if n == r or r == 0  :
        dp[n][r] = 1
        return 1;
    else :
        dp[n][r] = combination(n - 1, r - 1) + combination(n - 1, r)
        return dp[n][r]

n = int(input())

for i in range (n) :
    n,m =  map(int,input().split())

    print(combination(m,n))