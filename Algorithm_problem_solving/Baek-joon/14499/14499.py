# https://www.acmicpc.net/problem/14499
import sys

input = sys.stdin.readline


class DiceBoard:
    def __init__(self, n, m, x, y, board):
        self.n = n              # 판의 Y길이
        self.m = m              # 판의 X길이
        self.board = board      # 보드의 써진 수들
        self.dicePos = [y, x]   # 현재 주사위의 위치
        self.dice = [0] * 6     # 0: 아래, 1: 남, 2: 위, 3: 북, 4: 서, 5: 동

    def __copyNum(self):
        """
        움직인 곳의 바닥에 따라 숫자를 복사하는거 결정
        """
        if self.board[self.dicePos[0]][self.dicePos[1]] == 0:
            self.board[self.dicePos[0]][self.dicePos[1]] = self.dice[0]
        else:
            self.dice[0] = self.board[self.dicePos[0]][self.dicePos[1]]
            self.board[self.dicePos[0]][self.dicePos[1]] = 0

    def __moveDown(self):
        """
        주사위를 남쪽으로 굴린다.
        """
        self.dice[0], self.dice[1], self.dice[2], self.dice[3] \
            = self.dice[1], self.dice[2], self.dice[3], self.dice[0]

    def __moveUp(self):
        """
        주사위를 북쪽으로 굴린다.
        """
        self.dice[0], self.dice[1], self.dice[2], self.dice[3] \
            = self.dice[3], self.dice[0], self.dice[1], self.dice[2]

    def __moveLeft(self):
        """
        주사위를 서쪽으로 굴린다.
        """
        self.dice[0], self.dice[2], self.dice[4], self.dice[5] \
            = self.dice[4], self.dice[5], self.dice[2], self.dice[0]

    def __moveRight(self):
        """
        주사위를 동쪽으로 굴린다.
        """
        self.dice[0], self.dice[2], self.dice[4], self.dice[5] \
            = self.dice[5], self.dice[4], self.dice[0], self.dice[2]

    def moveDice(self, dirt):
        moveDirt = [(0, 1), (0, -1), (-1, 0), (1, 0)]   # 이동하는 방향 동쪽, 서쪽, 북쪽, 남쪽
        moveY = self.dicePos[0] + moveDirt[dirt - 1][0] # 움직힌 Y의 좌표
        moveX = self.dicePos[1] + moveDirt[dirt - 1][1] # 움직인 X의 좌표
        if moveX < 0 or moveY < 0 or moveX >= self.m or moveY >= self.n:
            # 만약 범위 밖으로 나간다면 그대로 retrun
            return
        if dirt == 1:
            self.__moveRight()
        elif dirt == 2:
            self.__moveLeft()
        elif dirt == 3:
            self.__moveUp()
        elif dirt == 4:
            self.__moveDown()
        self.dicePos[0] = moveY
        self.dicePos[1] = moveX
        self.__copyNum()
        print(self.dice[2])


if __name__ == "__main__":
    n, m, y, x, k = map(int, input().strip().split())
    arr = []
    for i in range(n):
        arr.append(list(map(int, input().strip().split())))
    dice = DiceBoard(n, m, x, y, arr)
    move = list(map(int, input().strip().split()))
    for item in move:
        dice.moveDice(item)
