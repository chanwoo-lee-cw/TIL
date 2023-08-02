import copy
from sys import stdin

input = stdin.readline


class SequenceReduction:
    def __init__(self, t):
        self.result = []
        self.t = t

    def dfs(self, arr):
        if len(arr) == 1:
            return arr[0] == t
        for idx, item in enumerate(arr[:-1]):
            self.result.append(idx + 1)
            temp = copy.deepcopy(arr)
            arr = arr[:idx] + [arr[idx] - arr[idx + 1]] + arr[idx + 2:]
            if self.dfs(arr):
                return True
            arr = copy.deepcopy(temp)
            self.result.pop()


if __name__ == "__main__":
    n, t = map(int, input().strip().split())
    arr: list = list(map(int, input().strip().split()))

    soultuin = SequenceReduction(t)

    soultuin.dfs(arr)
    for item in soultuin.result:
        print(item)
