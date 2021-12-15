import sys

input = sys.stdin.readline


class Matrix:
    def __init__(self, n, m, arr):
        self.n = n      # 배열의 세로 높이
        self.m = m      # 배열의 가로 높이
        self.arr = arr  # 배열

    def rotate(self):
        """
        배열을 1만큼 반시계 방향으로 회전 시킨다.
        """
        new_arr = [[0] * self.m for _ in range(self.n)]
        for i in range(self.n // 2):
            # 가로 부분 회전
            for j in range(i, self.m - 1 - i):
                new_arr[i][j] = self.arr[i][j + 1]
                new_arr[-(i + 1)][-(j + 1)] = self.arr[-(i + 1)][-(j + 2)]
        for j in range(self.m // 2):
            # 세로 부분 회전
            for i in range(j, self.n - 1 - j):
                new_arr[-(i + 1)][j] = self.arr[-(i + 2)][j]
                new_arr[i][-(j + 1)] = self.arr[i + 1][-(j + 1)]
        self.arr = new_arr

    def printMatrix(self):
        """
        배열을 출력한다.
        """
        for i in range(n):
            print(' '.join(str(item) for item in self.arr[i]))


if __name__ == "__main__":
    n, m, r = map(int, input().strip().split())
    arr = []
    for i in range(n):
        arr.append(list(map(int, input().strip().split())))
    matrix = Matrix(n, m, arr)
    for i in range(r):
        matrix.rotate()
    matrix.printMatrix()
