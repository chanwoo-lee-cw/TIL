import sys
input = sys.stdin.readline

N,M,K = map(int,input().strip().split())

if __name__ == "__main__" :
    dp = [[0] * (M+1) for _ in range(N+1)]
    widWayPoint = K%M+1;
    hegWayPoint = int(K/M)+1;
    dp[0][0] = 1
    dp[0][1] = 1
    if (K!=0 and K % M == 0) :
        widWayPoint = M + 1
        hegWayPoint = hegWayPoint - 1
    if(K==0) :
        widWayPoint=2
    for i in range(1,widWayPoint) :
        dp[1][i] = 1

    for i in range(1,hegWayPoint+1) :
        dp[i][1] = 1

    for i in range(2,hegWayPoint+1) :
        for j in range(2,widWayPoint) :
            dp[i][j] = dp[i][j-1] + dp[i-1][j]
    #
    for i in range(hegWayPoint,N+1) :
        for j in range(widWayPoint-1,M+1) :
            dp[i][j] = dp[i][j-1] + dp[i-1][j]

    # for i in range(N+1):
    #     print(dp[i])
    print(dp[N][M])