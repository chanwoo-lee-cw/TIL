import sys
input = sys.stdin.readline

if __name__ == "__main__" :

    n = int(input().strip())

    dp = [0] * 1000001

    thrialsqrt = 3
    doublesqrt = 2
    dp[1] = 0
    dp[2] = 1
    dp[3] = 1
    while(True) :

        temp_2 = doublesqrt*2
        temp_3 = thrialsqrt * 3
        if temp_2 >= n :
            break
        if temp_3 <= n :
            dp[temp_3] = dp[thrialsqrt] + 1
            thrialsqrt = temp_3
        dp[temp_2] = dp[doublesqrt] + 1
        doublesqrt= temp_2

    for i in range(5,n+1) :
        if dp[i]!=0 :
            continue
        half = dp[int(i/2)]+1 if i%2 == 0 else float('inf')
        triple = dp[int(i/3)]+1 if i%3 == 0 else float('inf')
        dp[i] = int(min(half,triple,dp[i-1]+1))

    print(dp[n])