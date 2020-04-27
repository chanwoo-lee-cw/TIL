import sys
input = sys.stdin.readline

if __name__ == "__main__" :
    (n,k) = map(int,input().strip().split())

    item = [[0,0]]

    for _ in range(n) :
        (m, v) = map(int,input().strip().split())
        item.append([m, v])

    dp = [[0] * (k+1) for _ in range(n+1)]

    for i in range(1,n + 1) :
        for j in range(1,k + 1):
            if j >= item[i][0]:
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - item[i][0]] + item[i][1])
            else:
                dp[i][j] = dp[i - 1][j]

    print(dp[n][k])