import copy
import sys

input = sys.stdin.readline


class Matrix:
    def __init__(self, n, m, arr):
        self.n = n      # 배열의 세로 길이
        self.m = m      # 배열의 가로 길이
        self.arr = arr  # 저장되어 있는 배열

    def first_calculation(self):
        """
        1번 연산 - 배열을 상하 반전시키는 연산이다.
        """
        self.arr.reverse()

    def second_calculation(self):
        """
        2번 연산 - 배열을 좌우 반전시키는 연산이다.
        """
        for i in range(self.m // 2):
            for j in range(self.n):
                self.arr[j][i], self.arr[j][-(i + 1)] = self.arr[j][-(i + 1)], self.arr[j][i]

    def third_calculation(self):
        """
        3번 연산 - 오른쪽으로 90도 회전시키는 연산이다.
        """
        new_arr = [[0] * self.n for _ in range(self.m)]
        for i in range(self.n):
            for j in range(self.m):
                new_arr[j][(self.n - 1) - i] = self.arr[i][j]
        self.arr = new_arr
        self.n, self.m = self.m, self.n

    def fourth_calculation(self):
        """
        4번 연산 - 왼쪽으로 90도 회전시키는 연산이다.
        """
        new_arr = [[0] * self.n for _ in range(self.m)]
        for i in range(self.n):
            for j in range(self.m):
                new_arr[(self.m - 1) - j][i] = self.arr[i][j]
        self.arr = new_arr
        self.n, self.m = self.m, self.n

    def fifth_calculation(self):
        """
        5번 연산 - 배열을 4부분으로 나눠서 오른쪽으로 90도 회전
        """
        new_arr = [[0] * self.m for _ in range(self.n)]
        start = [(0, 0), (0, self.m // 2), (self.n // 2, self.m // 2), (self.n // 2, 0)]    # 회전을 시작할때 각 왼쪽 위의 좌표
        move = [(0, self.m // 2), (self.n // 2, 0), (0, -self.m // 2), (-self.n // 2, 0)]   # 회전할 방향
        for k in range(4):
            for i in range(self.n // 2):
                for j in range(self.m // 2):
                    y = start[k][0] + i
                    x = start[k][1] + j
                    new_arr[y + move[k][0]][x + move[k][1]] = self.arr[y][x]
        self.arr = new_arr

    def sixth_calculation(self):
        """
        6번 연산 - 배열을 4부분으로 나눠서 왼쪽으로 90도 회전
        """
        new_arr = [[0] * self.m for _ in range(self.n)]
        start = [(0, 0), (0, self.m // 2), (self.n // 2, self.m // 2), (self.n // 2, 0)]    # 회전을 시작할때 각 왼쪽 위의 좌표
        move = [(self.n // 2, 0), (0, -self.m // 2), (-self.n // 2, 0), (0, self.m // 2)]   # 회전할 방향
        for k in range(4):
            for i in range(self.n // 2):
                for j in range(self.m // 2):
                    y = start[k][0] + i
                    x = start[k][1] + j
                    new_arr[y + move[k][0]][x + move[k][1]] = self.arr[y][x]
        self.arr = new_arr

    def print_arr(self):
        """
        현재 저장되어 있는 배열을 출력
        """
        for i in range(self.n):
            print(' '.join(str(i) for i in self.arr[i]))


if __name__ == "__main__":
    n, m, r = map(int, input().strip().split())
    arr = []
    for i in range(n):
        arr.append(list(map(int, input().strip().split())))
    matrix = Matrix(n, m, arr)
    cal = list(map(int, input().strip().split()))
    for item in cal:
        if item == 1:
            matrix.first_calculation()
        elif item == 2:
            matrix.second_calculation()
        elif item == 3:
            matrix.third_calculation()
        elif item == 4:
            matrix.fourth_calculation()
        elif item == 5:
            matrix.fifth_calculation()
        elif item == 6:
            matrix.sixth_calculation()
    matrix.print_arr()
