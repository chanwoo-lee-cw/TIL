# https://www.acmicpc.net/problem/15685
import sys

input = sys.stdin.readline

DIRT = ((0, 1), (-1, 0), (0, -1), (1, 0))   # 움직이는 방향을 저장한다.
pointSet = set()    # 드래곤 커브가 방문만 모서리를 체크


class DragonCurve:
    def __init__(self, x, y, d, g):
        self.x, self.y = x, y   # 시작 x,y좌표
        self.d = d      # 드래곤 커브의 방향
        self.g = g      # 드래곤 커브의 길이
        pointSet.add((x, y))

    def __next_move(self, y, x, d):
        """
        선이 이동하는 다음 위치를 저장한다.
        Args:
            y,x (int): 현재 y,x의 좌표
            d (int): 움직이는 방향
        Returns:
            (int, int): 
        """
        y = y + DIRT[d][0]
        x = x + DIRT[d][1]
        pointSet.add((x, y))
        return y, x

    def draw(self):
        """
        드래곤 커브를 그린다.
        """
        dirList = []
        d = self.d
        temp = [d]
        y, x = self.__next_move(self.y, self.x, d)
        dirList = dirList + temp
        for i in range(1, self.g + 1):
            temp = []
            for j in range(len(dirList)):
                d = (dirList[-(j + 1)] + 1) % 4
                temp.append(d)
                y, x = self.__next_move(y, x, d)
            dirList = dirList + temp


if __name__ == "__main__":
    n = int(input().strip())
    curves = []
    for _ in range(n):
        x, y, d, g = map(int, input().strip().split())
        new_curve = DragonCurve(x, y, d, g)
        curves.append(new_curve)
        new_curve.draw()

    answer = 0
    for item in pointSet:
        x0, x1 = item[0], item[0] + 1
        y0, y1 = item[1], item[1] + 1
        if (x0, y1) in pointSet and (x1, y0) in pointSet and (x1, y1) in pointSet:
            answer += 1
    print(answer)
