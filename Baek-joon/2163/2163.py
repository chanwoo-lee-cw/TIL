import sys
input = sys.stdin.readline
sys.setrecursionlimit(1000000000)

dp = {};

def cutting(n,m) :
    if n==1 and m ==1 :
        return 0
    elif (n==1 and m==2) or (m==1 and n==2) :
        return 1
    elif (n,m) in dp :
        return dp[(n,m)]
    else :
        if(m>n) :
            mid = int(m/2)
            dp[(n,m)] = cutting(n,mid) + cutting(n,m-mid) +1
            return dp[(n,m)]
        else :
            mid = int(n/2)
            dp[(n, m)] = cutting(mid, m) + cutting(n-mid, m) + 1
            return dp[(n, m)]

if __name__ == "__main__" :

    n,m = input().strip().split()
    n = int(n)
    m = int(m)

    print(cutting(n,m))