import sys
input = sys.stdin.readline

if __name__ == '__main__' :
    n,k = map(int, input().strip().split())

    dp = [[0] * (k+1) for _ in range(n+1)]

    for i in range(1,n+1) :
        dp[i][1] = 1
    for i in range(1,k+1) :
        dp[1][i] = i

    for i in range(2,k+1) :
        for j in range(2,n+1) :
            dp[j][i]=sum(dp[j-1][1:i+1])%1000000000

    print(dp[n][k])