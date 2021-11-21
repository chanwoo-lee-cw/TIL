# https://www.acmicpc.net/problem/1917
import sys

input = sys.stdin.readline

WAY = ((-1, 0), (1, 0), (0, -1), (0, 1))


class Dice:
    def __init__(self):
        self.dice = [0] * 6  # 0: 아래, 1: 남, 2: 위, 3: 북, 4: 서, 5: 동

    def copyNum(self, board, y, x):
        """
        현재 주사위가 올라가 있는 칸의 숫자를 지우고 주사위에 복사한다.
        Args:
            board (list[][]): 전개도
            y, x ((int, int)): 현재 주사위가 올라가 있는 칸의 좌표
        """
        self.dice[0] = board[y][x]
        board[y][x] = 0

    def moveDown(self):
        """
        주사위를 아래로 굴린다.
        """
        self.dice[0], self.dice[1], self.dice[2], self.dice[3] \
            = self.dice[1], self.dice[2], self.dice[3], self.dice[0]

    def moveUp(self):
        """
        주사위를 위로 굴린다.
        """
        self.dice[0], self.dice[1], self.dice[2], self.dice[3] \
            = self.dice[3], self.dice[0], self.dice[1], self.dice[2]

    def moveLeft(self):
        """
        주사위를 좌로 굴린다.
        """
        self.dice[0], self.dice[2], self.dice[4], self.dice[5] \
            = self.dice[4], self.dice[5], self.dice[2], self.dice[0]

    def moveRight(self):
        """
        주사위를 우로 굴린다.
        """
        self.dice[0], self.dice[2], self.dice[4], self.dice[5] \
            = self.dice[5], self.dice[4], self.dice[0], self.dice[2]


def dfs(dice, board, y, x):
    """
    DFS방식으로 전개도 위에서 주사위를 굴려서 완성할 수 있는지 확인한다.
    만약 주사위 밑면과 만나는 지점은 숫자를 잘라내서 주사위에 붙히는 방식
    Args:
        dice (Dice): 주사위 객체
        board (list[][]): 주사위 전개도가 그려진 보드
        y, x (int, int): 주사위가 놓여진 위치
    """
    dice.copyNum(board, y, x)
    for idx, next in enumerate(WAY):
        nextY = next[0] + y
        nextX = next[1] + x
        if nextX < 0 or nextY < 0 or nextX >= 6 or nextY >= 6:
            continue
        elif board[nextY][nextX] == 0:
            continue
        if idx == 0:
            dice.moveUp()
            dfs(dice, board, nextY, nextX)
            dice.moveDown()
        elif idx == 1:
            dice.moveDown()
            dfs(dice, board, nextY, nextX)
            dice.moveUp()
        elif idx == 2:
            dice.moveLeft()
            dfs(dice, board, nextY, nextX)
            dice.moveRight()
        elif idx == 3:
            dice.moveRight()
            dfs(dice, board, nextY, nextX)
            dice.moveLeft()


def testCase():
    board = []
    dice = Dice()
    for i in range(6):
        board.append(list(map(int, input().strip().split())))
    for i in range(6):
        for j in range(6):
            if board[i][j] == 1:
                dfs(dice, board, i, j)
                return dice


if __name__ == "__main__":
    t = 3
    for _ in range(t):
        dice = testCase()
        print("yes" if 0 not in dice.dice else "no")
