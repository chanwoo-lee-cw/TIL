from sys import stdin
import heapq

input = stdin.readline
PAPER_SIZE = 11


def minNeedPaper(paper):
    """
    1*1, 2*2, ..., 5*5 까지 1을 모두 가리는데 필요한 종이의 갯수
    """
    needPaperSize = [[0] * PAPER_SIZE for _ in range(PAPER_SIZE)]
    paperSizeList = [5] * 6
    que = []
    for i in range(1, PAPER_SIZE):
        for j in range(1, PAPER_SIZE):
            if paper[i][j] == 1:
                needPaperSize[i][j] = 1
                if 0 < needPaperSize[i - 1][j - 1] < 5:
                    needPaperSize[i][j] = needPaperSize[i - 1][j - 1] + 1
                    for a in range(1, needPaperSize[i - 1][j - 1] + 1):
                        if paper[i - a][j] == 0 or paper[i][j - a] == 0:
                            needPaperSize[i][j] = min(needPaperSize[i - 1][j - 1] + 1, a)
                            break
                heapq.heappush(que, (-needPaperSize[i][j], -i, -j))
    return 0


# https://www.acmicpc.net/problem/17136
if __name__ == "__main__":
    paper = [[0] * PAPER_SIZE for _ in range(PAPER_SIZE)]
    for i in range(1, PAPER_SIZE):
        inputLine = list(map(int, input().strip().split()))
        for j in range(1, PAPER_SIZE):
            paper[i][j] = inputLine[j - 1]
    inputLine = None
    answer = minNeedPaper(paper)
    print(answer)
