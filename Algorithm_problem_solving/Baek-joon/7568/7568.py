from sys import stdin

input = stdin.readline


class BigOne:
    def __init__(self, number: int, weight: int, height: int):
        self.numer = number
        self.weight = weight
        self.height = height
        self.more_bigger = 0

    def am_i_smaller(self, other) -> bool:
        return self.weight < other.weight and self.height < other.height

    def __lt__(self, other):
        return self.more_bigger < other.more_bigger


if __name__ == "__main__":
    n: int = int(input())
    children = []
    for i in range(n):
        weight, height = map(int, input().strip().split())
        children.append(BigOne(i + 1, weight, height))

    for i in range(n):
        for j in range(n):
            if children[i].am_i_smaller(children[j]):
                children[i].more_bigger += 1

    children.sort()
    answer = [' '] * (n + 1)
    now = (0, -1)
    for i in range(n):
        child = children[i]
        if now[1] == child.more_bigger:
            answer[child.numer] = str(now[0])
        else:
            now = (i + 1, child.more_bigger)
            answer[child.numer] = str(now[0])
    print(' '.join(answer[1:]))
