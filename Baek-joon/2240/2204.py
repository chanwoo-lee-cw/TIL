import sys
input = sys.stdin.readline

if __name__ == "__main__" :

    t,w = input().strip().split()
    t = int(t)
    w = int(w)

    dp = [[0 for _ in range(t+1)]for _ in range(w+1)]

    plum =[0]

    for i in range(1,t+1) :
        plum.append(int(input().strip()))

    for i in range(1,t+1) :
        if plum[i]==1 :
            dp[0][i] = dp[0][i-1] + 1
        else :
            dp[0][i] = dp[0][i-1]

    for i in range(1,w+1) :
        if i%2 == 1 :
            pos = 2
        else :
            pos = 1
        for j in range(1,t+1) :
            temp = dp[i-1][j-1] if dp[i-1][j-1]>dp[i][j-1] else dp[i][j-1]
            if plum[j]==pos :
                dp[i][j] = temp + 1
            else :
                dp[i][j] = temp

    # for i in range(w+1) :
    #     print(dp[i]);

    max = 0
    for i in range(w+1) :
        max = dp[i][t] if dp[i][t]>max else max

    print(max)