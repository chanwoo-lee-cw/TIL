import sys
input = sys.stdin.readline

if __name__ == "__main__" :

    t = int(input().strip())
    list = []

    for _ in range(t) :
        list.append(int(input().strip()))

    dp = [0,1,2,4]

    for i in range(4,max(list)+1) :
        dp.append(dp[i-1] + dp[i-2] + dp[i-3])

    for i in list :
        print(dp[i])