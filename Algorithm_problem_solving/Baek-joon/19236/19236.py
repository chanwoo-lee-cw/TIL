import copy
from sys import stdin

input = stdin.readline

DIRCTION = ((-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1))


class Fish:
    def __init__(self, a, b):
        self.num = a  # 물고기의 번호
        self.direction = b - 1  # 물고기가 움직이는 경로

    """
    물고기가 움직이는 방향을 (y,x)의 형태로 반환한다.
    """
    def getDirection(self):
        return DIRCTION[self.direction]

    """
    물고기가 움직이는 방향을 새로 설정한다.
    """
    def setDirection(self, direction):
        self.direction = direction

    """
    만약 움직일 수 없는 방향일 경우 물고기의 방량을 45도 회전시킨다.
    """
    def changeDirction(self, board, y, x):
        changCnt = 0
        while True:
            if changCnt == 8:
                break
            nextY = y + self.getDirection()[0]
            nextX = x + self.getDirection()[1]
            if not (nextY < 0 or nextY >= n or nextX < 0 or nextX >= n or type(board[nextY][nextX]) == Shark):
                return (nextY, nextX)
            self.direction = (self.direction + 1) % 8
            changCnt += 1
        return None


class Shark(Fish):
    def __init__(self, a, b):
        super().__init__(0, b + 1)
        self.eat = a  # 상어가 먹은 물고기의 비용

    def setDirection(self, direction):
        super().setDirection(direction)  # 상어의 방향을 새로 설정한다.


class Arium:
    def __init__(self, n, board):
        self.board = board
        self.shark = None

    """
    (0,0)의 위치에 상어를 넣는다.
    """
    def addShark(self):
        self.shark = Shark(self.board[0][0].num, self.board[0][0].direction)
        self.board[0][0].alive = False
        self.board[0][0] = self.shark

    """
    상어를 제외한 모든 물고기를 이동 시킨다.
    """
    def allMoveFish(self):
        for num in range(1, 17):
            self.moveFish(num)

    """
    moveFish(num) - num번 물고기를 이동시긴다.
    """
    def moveFish(self, num):
        for i in range(n):
            for j in range(n):
                if self.board[i][j] and self.board[i][j].num == num:
                    next = self.board[i][j].changeDirction(self.board, i, j)
                    if not next:
                        continue
                    if self.board[next[0]][next[1]]:
                        self.board[next[0]][next[1]], self.board[i][j] = \
                            self.board[i][j], self.board[next[0]][next[1]]
                    else:
                        self.board[next[0]][next[1]] = self.board[i][j]
                        self.board[i][j] = None
                    return

    """
    현재 상어가 있는 위치는 (y,x)의 형태로 반환
    """
    def findShark(self):
        for i in range(n):
            for j in range(n):
                if type(self.board[i][j]) == Shark:
                    return i, j

    """
    백트래킹으로 상어가 가장 많이 먹을 수 있는 경우를 반환한다.
    """
    def dfs(self):
        answer = self.shark.eat
        self.allMoveFish()
        thisFishes = copy.deepcopy(self.board)
        thisShark = copy.deepcopy(self.shark)
        y, x = self.findShark()
        temp = (y, x)
        while True:
            self.board = copy.deepcopy(thisFishes)
            self.shark = copy.deepcopy(thisShark)
            nextY = temp[0] + self.shark.getDirection()[0]
            nextX = temp[1] + self.shark.getDirection()[1]
            temp = (nextY, nextX)
            if nextY < 0 or nextY >= n or nextX < 0 or nextX >= n:
                break
            if not self.board[nextY][nextX]:
                continue
            self.shark.eat += self.board[nextY][nextX].num
            self.shark.setDirection(self.board[nextY][nextX].direction)
            self.board[y][x] = None
            self.board[nextY][nextX] = self.shark
            answer = max(answer, self.dfs())
        return answer


if __name__ == "__main__":
    n = 4
    board = [[None] * n for _ in range(n)]
    for i in range(n):
        inLine = list(map(int, input().strip().split()))
        for j in range(n):
            a = inLine.pop(0)
            b = inLine.pop(0)
            board[i][j] = Fish(a, b)
    arium = Arium(n, board)
    arium.addShark()
    answer = arium.dfs()
    print(answer)
