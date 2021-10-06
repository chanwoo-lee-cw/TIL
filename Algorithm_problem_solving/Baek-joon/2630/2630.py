# https://www.acmicpc.net/problem/2630
import sys

input = sys.stdin.readline


class PaperCut:
    def __init__(self, n, paper):
        self.n = n
        self.paper = paper
        self.blueCnt = 0
        self.whiteCnt = 0

    def dfs(self, ys: int, ye: int, xs: int, xe: int):
        if ye - ys <= 1:
            return self.paper[ys][xs]
        thisArea = []
        ym = (ye + ys) // 2
        xm = (xe + xs) // 2
        thisArea.append(self.dfs(ys, ym, xs, xm))
        thisArea.append(self.dfs(ym, ye, xs, xm))
        thisArea.append(self.dfs(ys, ym, xm, xe))
        thisArea.append(self.dfs(ym, ye, xm, xe))

        if thisArea[0] != -1 and thisArea.count(thisArea[0]) == 4:
            return thisArea[0]
        else:
            for item in thisArea:
                if item == 1:
                    self.blueCnt += 1
                elif item == 0:
                    self.whiteCnt += 1
            return -1

    def getPaperCnt(self):
        color = self.dfs(0, n, 0, n)
        if color == 0:
            self.whiteCnt += 1
        elif color == 1:
            self.blueCnt += 1
        return (self.whiteCnt, self.blueCnt)


if __name__ == '__main__':
    n = int(input().strip())
    paper = []
    for _ in range(n):
        paper.append(list(map(int, input().strip().split())))
    paperCut = PaperCut(n, paper)
    answer = paperCut.getPaperCnt()
    print(f'{answer[0]}\n{answer[1]}')
