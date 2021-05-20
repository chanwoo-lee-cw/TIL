from sys import stdin

input = stdin.readline

'''
왼쪽 최 상단에 칠해진 색에 따른 다시 칠하는 것을 최소로 하는 수를 리턴한다.
argument
    chess : 현재 체스 판에 칠해진 색
    m : 세로 길이
    n : 가로 길이
    topColor : 왼쪽 최 상단에 칠해진 색 
'''
def minRecolorBlockByTop(chess, m, n, topColor):
    dp = [[0] * (m + 1) for _ in range(n + 1)]
    # 만약 i + j 가 짝수이면 topColor와 같아야 한다.
    # 만약 i + j가 홀수이면 topColor와 달라야 한다.
    answer = float('inf')
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1]
            if (i + j) % 2 == 1:
                if chess[i][j] == topColor:
                    dp[i][j] += 1
            else:
                if chess[i][j] != topColor:
                    dp[i][j] += 1
    for i in range(8, n + 1):
        for j in range(8, m + 1):
            # 각각 8*8 공간에서 바꿔야 되는 수의 갯수를 구해서, 가장 작은 수가 나올 때까지 반복
            answer = min(answer, dp[i][j] - dp[i - 8][j] - dp[i][j - 8] + dp[i - 8][j - 8])
    return answer


if __name__ == "__main__":
    m, n = map(int, input().strip().split())
    chess = [[0] * (n + 1) for _ in range(m + 1)]
    for i in range(m):
        line = input().strip()
        for j in range(n):
            chess[i + 1][j + 1] = line[j]
    answer = min(minRecolorBlockByTop(chess, n, m, 'W'), minRecolorBlockByTop(chess, n, m, 'B'))
    print(answer)
