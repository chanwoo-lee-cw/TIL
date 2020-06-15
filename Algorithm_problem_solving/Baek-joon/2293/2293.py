import sys
import heapq
input = sys.stdin.readline


def coin(k, coinList):
    dp = [0]*(k+1)
    # Basecase
    # 같은 배열을 재활용 하므로 0만 채워주면 된다.
    dp[0] = 1

    # 모든 코인의 경우를 다 확인 할때까지 반복
    for i in range(1, n+1):
        # 같은 배열을 재활용 하므로 현재 코인을 사용할 수 있는 경우부터 확인하면 된다.
        for j in range(coinList[i], k+1):
            # 점화식 : dp[i][j] = dp[i-1][j] + dp[i][j-coin[i]]
            # 같은 배열 재활용 하므로 그냥 이전의 배열의 경우를 그대로 더해주면 된다.
            dp[j] = dp[j] + dp[j-coinList[i]]

    return dp[k]


if __name__ == "__main__":
    n, k = map(int, input().strip().split())
    coinList = [0]
    for _ in range(n):
        heapq.heappush(coinList, int(input().strip()))

    print(coin(k, coinList))
