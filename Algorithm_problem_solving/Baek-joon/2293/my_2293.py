import sys
import heapq
input = sys.stdin.readline


def coin(k, coinList):
    answer = 0
    # 각 동전 별로 세는 경우를 알아내기 위한 dp 선언
    dp = [[0] * (k+1) for i in range(len(coinList))]

    # basecase
    # 0인 경우는 그냥 1로 초기화 한다.
    for i in range(len(coinList)):
        dp[i][0] = 1
    # 첫번째 코인의 경우 나누어 떨어지는 경우 채운다.
    for change in range(1, k + 1):
        if (change % coinList[1] == 0):
            dp[1][change] = 1

    # ignition formula
    for coin in range(2, len(coinList)):
        for change in range(1, k + 1):
            # 만약 현재 동전을 쓸 수 있는 위치에서의 점화식
            # dp[i][j] = dp[i-1][j] + dp[i][j-coin[i]]
            if change >= coinList[coin]:
                dp[coin][change] = dp[coin - 1][change] + \
                    dp[coin][change - coinList[coin]]
            else:
                dp[coin][change] = dp[coin - 1][change]

    return dp[len(coinList)-1][k]


if __name__ == "__main__":
    n, k = map(int, input().strip().split())
    coinList = [0]
    for _ in range(n):
        heapq.heappush(coinList, int(input().strip()))

    print(coin(k, coinList))
