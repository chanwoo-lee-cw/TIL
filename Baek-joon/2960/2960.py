import sys
input = sys.stdin.readline

if __name__=="__main__" :
    n,k= map(int, input().strip().split())

    dp = [True] * (n+1)
    delcount = 0
    while(True) :
        filter = dp[2:].index(True) + 2
        if(filter > n):
            break
        for i in range(1,n+1) :
            if(i*filter>n) :
                break
            if dp[i*filter]==True :
                dp[i*filter]=False
                delcount = delcount + 1
                if(delcount==k) :
                    print(i*filter)
                    exit()

    # for i in range(2,n+1):
    #     if(dp[i]==True) :
    #         print(i)