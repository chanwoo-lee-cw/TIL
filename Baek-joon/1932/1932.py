import sys
input = sys.stdin.readline

if __name__ == "__main__" :

    n = int(input().strip())

    matrix = []

    for i in range(n) :
        temp = list(map(int,input().strip().split()))
        matrix.append(temp)

    dp = [[0]*n for _ in range(n)]

    dp[0][0] = matrix[0][0]

    for i in range(n) :
        for j in range(i+1) :
            if j==0 :
                dp[i][j] = matrix[i][j] + dp[i-1][j]
            elif j == i :
                dp[i][j] = matrix[i][j] + dp[i-1][j-1]
            else :
                dp[i][j] = matrix[i][j] + max(dp[i-1][j],dp[i-1][j-1])

    print(max(dp[n-1]))