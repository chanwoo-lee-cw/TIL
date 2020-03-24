import sys
input = sys.stdin.readline

if __name__=="__main__" :
    n,k = map(int,input().strip().split())
    count = 0
    dp=[]
    if(n<=2) :
        if(n == 1) :
            dp.append(1)
        else :
            dp.append(1)
            dp.append(2)
    for i in range(1,int(n/2)+1) :
        if(n % i == 0) :
            dp.append(i)
            dp.append(int(n/i))

    dp = list(set(dp))
    dp.sort()
    if(len(dp)>=k) :
        print(dp[k-1])
    else :
        print(0)