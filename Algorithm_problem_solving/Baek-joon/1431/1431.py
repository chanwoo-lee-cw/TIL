from sys import stdin
import heapq

input = stdin.readline


class Serial:
    def __init__(self, serial):
        self.serial = serial  # 시리얼번호
        self.length = len(serial)  # 길이
        self.numSum = 0  # 숫자인 부분의 합
        for item in serial:
            if item.isdigit():
                self.numSum += int(item)

    def __lt__(self, other):
        # A와 B의 길이가 다르면, 짧은 것이 먼저 온다.
        if self.length != other.length:
            return self.length < other.length
        # 만약 서로 길이가 같다면, A의 모든 자리수의 합과 B의 모든 자리수의 합을 비교해서 작은 합을 가지는 것이 먼저온다. (숫자인 것만 더한다)
        elif self.numSum != other.numSum:
            return self.numSum < other.numSum
        # 만약 1,2번 둘 조건으로도 비교할 수 없으면, 사전순으로 비교한다. 숫자가 알파벳보다 사전순으로 작다.
        return self.serial < other.serial


def solution():
    n = int(input().strip())
    serials = []
    for _ in range(n):
        serial = Serial(input().strip())
        heapq.heappush(serials, serial)

    while serials:
        temp = heapq.heappop(serials)
        print(temp.serial)


if __name__ == "__main__":
    solution()
