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

    """
    재귀를 통해 해당 노드에서 가장 긴 다리의 길이를 리턴한다.
    """
    def getLongestLeg(self):
        if not self.child:
            # 만약 단말 노드인 경우 
            return 0
        global maxdiameter      # 가장 긴 지름의 길이를 저장할 변수
        for item in self.child:
            self.legs.append(tree[item[0]].getLongestLeg() + item[1])
        
        self.legs.sort(reverse=True)
        if len(self.legs) >= 2:
            # 다리가 여러개인 경우 지름을 구한다.
            maxdiameter = max(maxdiameter, self.legs[0] + self.legs[1])
        else:
            # 다리가 한개인 경우
            maxdiameter = max(maxdiameter, self.legs[0])
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
