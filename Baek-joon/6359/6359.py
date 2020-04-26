import sys
input = sys.stdin.readline

if __name__ == "__main__" :

    t = int(input().strip())

    dp = [0]
    list = []

    for _ in range(t) :
        list.append(int(input().strip()))

    maximum= max(list)

    for i in range(1,maximum+1) :
        temp = i**0.5
        if(temp==int(temp)) :
            dp.append(dp[i-1] + 1)
        else :
            dp.append(dp[i - 1])

    for i in list :
        print(dp[i])

