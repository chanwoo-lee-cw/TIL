from sys import stdin

input = stdin.readline


class MergeArr:
    n = 0
    m = 0
    a = []
    b = []

    def __init__(self, n, m, a, b):
        self.n = n
        self.m = m
        self.a = a
        self.b = b

    def merge(self):
        self.n = len(self.a)
        self.m = len(self.b)
        merged = []

        aPos, bPos = 0, 0
        while aPos < self.n and bPos < self.m:
            if self.a[aPos] < self.b[bPos]:
                merged.append(self.a[aPos])
                aPos += 1
            else:
                merged.append(self.b[bPos])
                bPos += 1

        if aPos < self.n:
            merged += self.a[aPos:]
        else:
            merged += self.b[bPos:]

        return merged


if __name__ == "__main__":
    n, m = map(int, input().strip().split())
    a = list(map(int, input().strip().split()))
    b = list(map(int, input().strip().split()))

    mergeArr = MergeArr(n, m, a, b)
    answer = mergeArr.merge()
    print(' '.join(str(item) for item in answer))
