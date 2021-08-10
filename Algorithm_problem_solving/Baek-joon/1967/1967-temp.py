# https://www.acmicpc.net/problem/1967
from sys import stdin, setrecursionlimit

setrecursionlimit(100000000)
input = stdin.readline


class Node:
    def __init__(self):
        self.parent = 0
        self.child = []
        self.legs = []

    def AddParent(self):
        self.parent += 1

    def AddChild(self, child, weight):
        self.child.append((child, weight))

    def getLongestLeg(self):
        if not self.child:
            return 0
        for item in self.child:
            self.legs.append(tree[item[0]].getLongestLeg() + item[1])
        self.legs.sort(reverse=True)
        global maxdiameter
        if len(self.legs) >= 2:
            maxdiameter = max(maxdiameter, self.legs[0] + self.legs[1])
        return self.legs[0]


if __name__ == "__main__":
    n = int(input())
    tree = [Node() for _ in range(n + 1)]

    for i in range(n - 1):
        p, c, w = map(int, input().strip().split())
        tree[p].AddChild(c, w)
        tree[c].AddParent()

    maxdiameter = 0
    for i in range(1, n + 1):
        if tree[i].parent == 0:
            head = i
    tree[head].getLongestLeg()
    print(maxdiameter)
