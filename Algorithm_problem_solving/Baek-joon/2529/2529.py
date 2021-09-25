import sys

sys.setrecursionlimit(10 ** 5)
input = sys.stdin.readline


class MakeNum:
    def __init__(self, k, sign):
        self.k = k
        self.sign = sign
        self.maxNum = -1
        self.minNum = "9" * (k + 1)

    def fillNum(self):
        numList = []
        numUsed = [False] * 10
        for i in range(10):
            numList.append(i)
            numUsed[i] = True
            self.dfs(numList, 0, numUsed)
            numUsed[i] = False
            numList.pop()

    def dfs(self, numList: list, pos: int, numUsed: list):
        if pos >= self.k:
            num = ''.join(str(i) for i in numList)
            self.maxNum = num if int(self.maxNum) < int(num) else self.maxNum
            self.minNum = num if int(self.minNum) > int(num) else self.minNum
        else:
            for i in range(10):
                if sign[pos] == '<' and not numUsed[i] and numList[pos] < i:
                    numList.append(i)
                    numUsed[i] = True
                    self.dfs(numList, pos + 1, numUsed)
                    numUsed[i] = False
                    numList.pop()
                elif sign[pos] == '>' and not numUsed[i] and numList[pos] > i:
                    numList.append(i)
                    numUsed[i] = True
                    self.dfs(numList, pos + 1, numUsed)
                    numUsed[i] = False
                    numList.pop()


if __name__ == "__main__":
    k = int(input().strip())
    sign = input().strip().split()
    numList = []
    makeNum = MakeNum(k, sign)
    makeNum.fillNum()
    print(makeNum.maxNum)
    print(makeNum.minNum)
