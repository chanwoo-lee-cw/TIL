# https://www.acmicpc.net/problem/1758
import sys
import heapq

input = sys.stdin.readline

"""
힙큐를 통해서 팁이 음수가 되는 경우는 최대한 줄인다.
getMaxPair(대기열의 사람, 힙 큐에 들어간 가격의 배열)
"""
def getMaxPair(n: int, que: list):
    allPay = 0      # 모든 페이의 합
    for i in range(1, n + 1):
        curr = -heapq.heappop(que)
        thisPay = curr - (i - 1)
        if thisPay > 0:
            allPay += thisPay
        else:
            break
    return allPay


if __name__ == "__main__":
    n: int = int(input().strip())
    que: list = []
    for _ in range(n):
        heapq.heappush(que, -int(input().strip()))
    answer = getMaxPair(n, que)
    print(answer)
