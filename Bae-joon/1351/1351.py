import sys
input = sys.stdin.readline

MAX = 500000

n,p,q = map(int,input().strip().split())

def memo(i) :
    if(i==0) :
        return 1;
    pos1 = int(i / p)
    pos2 = int(i / q)
    if(pos1 <=MAX) :
        if(dp[pos1] != 0) :
            temp1 = dp[pos1]
        else :
            temp1 = memo(pos1)
    else :
        temp1 = memo(pos1)

    if (pos2 <= MAX):
        if(dp[pos2] != 0) :
            temp2 = dp[pos2]
        else :
            temp2 = memo(pos2)
    else :
        temp2 = memo(pos2)
    if(i<=MAX) :
        dp[i] = temp1 + temp2
        return dp[i]
    else :
        return temp1 + temp2

if __name__ == "__main__" :
    dp = [0]*(MAX+1)
    print(memo(n))