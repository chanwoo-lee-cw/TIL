from sys import stdin

input = stdin.readline


def makeDp(N, board):
    dp = [[0] * N for _ in range(N)]
    dp[0][0] = 1
    for i in range(N):
        for j in range(N):
            if i == N - 1 and j == N - 1:
                break
            if i + board[i][j] < N:
                dp[i + board[i][j]][j] += dp[i][j]
            if j + board[i][j] < N:
                dp[i][j + board[i][j]] += dp[i][j]
    return dp[N - 1][N - 1]


if __name__ == "__main__":
    N = int(input().strip())
    # board = [[0] * N for _ in range(N)]
    board = []

    for i in range(N):
        line = list(map(int, input().strip().split()))
        board.append(line)
    print(makeDp(N, board))

