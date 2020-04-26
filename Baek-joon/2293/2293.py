import sys
import heapq
input = sys.stdin.readline

if __name__ == "__main__" :
    n, k = map(int, input().strip().split())
    coinList = [0]
    for _ in range(n) :
        heapq.heappush(coinList,int(input().strip()))

    dp = [0]*(k+1)
    dp[0] = 1

    for i in range(1,n+1) :
        for j in range(coinList[i],k+1) :
            dp[j] = dp[j] + dp[j-coinList[i]]

    print(dp[k])