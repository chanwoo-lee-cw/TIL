import sys
input = sys.stdin.readline

if __name__ == '__main__':
    n, m = map(int,input().strip().split())
    matrix = [[0] * (m+1) for _ in range(n+1)]

    for i in range(n) :
        temp = list(map(int,input().strip().split()))
        for j in range(m) :
            matrix[i+1][j+1] = temp[j]

    dp = [[0] * (m+1) for _ in range(n+1)]
    for i in range(1, n+1) :
        for j in range(1, m+1) :
            dp[i][j] = dp[i-1][j] + dp[i][j-1] + matrix[i][j] - dp[i-1][j-1]

    k = int(input().strip())

    for count in range(k) :
        i,j,x,y = map(int,input().strip().split())
        print(dp[x][y]-dp[i-1][y]-dp[x][j-1]+dp[i-1][j-1])