import sys
input = sys.stdin.readline
sys.setrecursionlimit(1000000000)


dp = []

def meno(color, pos, coloring,n) :
    if(pos == n-1) :
        return color[pos][coloring]
    if(dp[pos][coloring]!=0) :
        return dp[pos][coloring]
    if(coloring==0) :
        dp[pos][coloring] = min(meno(color, pos + 1, 1, n), meno(color, pos + 1, 2, n)) + color[pos][coloring]
        return dp[pos][coloring]
    elif (coloring==1) :
        dp[pos][coloring] = min(meno(color, pos + 1, 0, n), meno(color, pos + 1, 2, n)) + color[pos][coloring]
        return dp[pos][coloring]
    else :
        dp[pos][coloring] = min(meno(color, pos + 1, 0, n), meno(color, pos + 1, 1, n)) + color[pos][coloring]
        return dp[pos][coloring]


if __name__ == "__main__" :
    n = int(input().strip())
    color = []
    for _ in range(n) :
        temp = input().strip().split()
        for i in range(3) :
            temp[i] = int(temp[i])
        color.append(temp)
    dp = [[0 for _ in range(3)]for _ in range(n)]

    print(min(meno(color, 0, 0, n), meno(color, 0, 1, n), meno(color, 0, 2, n)))