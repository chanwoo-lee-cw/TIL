from sys import stdin

input = stdin.readline


def findMaxSquare(n, m, square):
    dp = [[0] * (m + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if square[i][j]:
                dp[i][j] = 1
                if dp[i - 1][j - 1] != 0:
                    dp[i][j] = dp[i - 1][j - 1] + 1
                    for k in range(1, dp[i - 1][j - 1] + 1):
                        if not (square[i - k][j] and square[i][j - k]):
                            dp[i][j] = k
                            break

    answer = 0
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if square[i][j]:
                answer = max(dp[i][j], answer)
    return answer ** 2


if __name__ == "__main__":
    n, m = map(int, input().strip().split())
    square = [[False] * (m + 1) for _ in range(n + 1)]

    for i in range(1, n + 1):
        inputLine = input().strip()
        for j in range(1, m + 1):
            square[i][j] = True if int(inputLine[j - 1]) == 1 else False
    print(findMaxSquare(n, m, square))
