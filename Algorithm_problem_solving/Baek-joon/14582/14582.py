from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/14582
if __name__ == "__main__":
    score = [[0] * 10 for _ in range(2)]  # 각 회마다 얻을 점수
    scoreSum = [[0] * 10 for _ in range(2)]  # 각 회차마다 총 점수의 합계

    for i in range(2):
        inputLine = list(map(int, input().strip().split()))
        for j in range(9):
            score[i][j + 1] = inputLine[j]

    weWin = False  # 현재 이기고 있는지 여부
    answer = False  # 역전당했는지의 여부
    # 전반부에 역전을 했다가 말에 다시 역전 당할 수 있다. 이런 경우도 생각해 줘야한다.
    for j in range(1, 10):
        scoreSum[0][j] = score[0][j] + scoreSum[0][j - 1]
        if scoreSum[0][j] > scoreSum[1][j - 1]:
            weWin = True
            answer = False
        scoreSum[1][j] = score[1][j] + scoreSum[1][j - 1]
        if scoreSum[0][j] < scoreSum[1][j]:
            if weWin:
                weWin = False
                answer = True

    print("Yes" if answer else "No")
