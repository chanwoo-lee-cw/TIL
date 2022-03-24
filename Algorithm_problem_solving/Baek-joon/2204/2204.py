import sys
import heapq

input = sys.stdin.readline


class Voca:
    def __init__(self, voca):
        self.voca = voca

    def __lt__(self, other):
        return self.voca.lower() < other.voca.lower()


if __name__ == "__main__":
    while True:
        t = int(input().strip())
        if t == 0:
            break
        que = []
        for _ in range(t):
            voca = Voca(input().strip())
            heapq.heappush(que, voca)
        answer: Voca = heapq.heappop(que)
        print(answer.voca)
