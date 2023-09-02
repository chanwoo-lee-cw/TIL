from sys import stdin

# https://www.acmicpc.net/problem/2615

input = stdin.readline

WAY = ((1, 0), (0, 1), (1, 1), (-1, 1))
SIZE = 19


class Omock:
    def __init__(self, matrix: list):
        self.matrix = matrix

    def solution(self) -> tuple:
        """
        오목판 전체를 탐색해서 승자가 있는지 판멸하는 메소드
        :return: 첫 항목은 승자, 없으면 0, 두번째 항목은 처음으로 나타나는 위치
        """
        for i in range(SIZE):
            for j in range(SIZE):
                if matrix[i][j] != 0:
                    for next in WAY:
                        if self.check_result(next, i, j, 1, matrix[i][j]):
                            return matrix[i][j], (i + 1, j + 1)
        return 0, (0, 0)

    def check_result(self, next, y, x, count, win):
        """
        같은 방향으로 찾으며 이긴 위치가 있는지 찾는다
        :param next: 탐색할 방향
        :param y: 이전 y 위치
        :param x: 이전 x 위치
        :param count: 현재까지 연속된 갯수
        :param win: 현재 판별중인 승자
        :return: 이겼다면 True, 아니라면 False
        """
        next_y = next[0] + y
        next_x = next[1] + x
        if next_y >= SIZE or next_x >= SIZE or next_x < 0 or next_y < 0:
            return False
        elif self.matrix[next_y][next_x] != win:
            return False
        else:
            count += 1
            if count >= 5:
                return True
            else:
                return self.check_result(next, next_y, next_x, count, win)


if __name__ == "__main__":
    matrix = []
    for item in range(SIZE):
        matrix.append(list(map(int, input().strip().split())))
    omock = Omock(matrix)
    win, position = omock.solution()
    if win == 0:
        print(0)
    else:
        print(f"{win}\n{position[0]} {position[1]}")
