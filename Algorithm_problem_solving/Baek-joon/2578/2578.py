from sys import stdin

input = stdin.readline


class Bingo:
    def __init__(self, n, numPosition):
        self.n = n
        self.numPosition = numPosition
        self.board = [[False] * n for _ in range(n)]
        self.bingoCnt = 0

    def checkNum(self, num):
        pos = self.numPosition[num]
        self.board[pos[0]][pos[1]] = True
        self.checkBingo(pos[0], pos[1])
        if self.bingoCnt >= 3:
            return True
        return False

    def checkBingo(self, y, x):
        rowBingo = True
        for i in range(self.n):
            if not self.board[i][x]:
                rowBingo = False
                break
        if rowBingo:
            self.bingoCnt += 1
        colBingo = True
        for j in range(self.n):
            if not self.board[y][j]:
                colBingo = False
                break
        if colBingo:
            self.bingoCnt += 1
        if y == x:
            crossBingo = True
            for i in range(self.n):
                if not self.board[i][i]:
                    crossBingo = False
                    break
            if crossBingo:
                self.bingoCnt += 1
        if y + x == self.n - 1:
            crossBingo = True
            for i in range(self.n):
                if not self.board[i][self.n - 1 - i]:
                    crossBingo = False
                    break
            if crossBingo:
                self.bingoCnt += 1


if __name__ == "__main__":
    numPosition = {}
    n = 5
    for i in range(n):
        numList = list(map(int, input().strip().split()))
        for j in range(n):
            numPosition[numList[j]] = (i, j)

    bingo = Bingo(n, numPosition)

    cnt = 0
    flag = True
    for i in range(n):
        numList = list(map(int, input().strip().split()))
        for j in range(n):
            cnt += 1
            if bingo.checkNum(numList[j]):
                flag = False
                break
        if not flag:
            break
    print(cnt)