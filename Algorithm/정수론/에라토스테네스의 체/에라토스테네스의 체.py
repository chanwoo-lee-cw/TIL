import sys
input = sys.stdin.readline

if __name__=="__main__" :
    n= int(input().strip())

    dp = [True] * (n+1)
    count = 0
    delcount = 0
    while(True) :
        filter = dp[2:].index(True) + 2 + count
        if(filter*filter > n):
            break
        for i in range(2,n+1) :
            if(i*filter>n) :
                count = count + 1
                break
            dp[i*filter]=False

    for i in range(2,n+1):
        if(dp[i]==True) :
            print(i)