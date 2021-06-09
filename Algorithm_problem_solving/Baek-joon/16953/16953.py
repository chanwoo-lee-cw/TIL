# https://www.acmicpc.net/problem/16953

from sys import stdin
import heapq

input = stdin.readline

"""
a가 b가 되기 위한 최소 변경 횟수를 구한다.
"""
def minDiverse(a, b):
    que = []        # 가장 작은 수를 구하기 위한 힙 선언
    visited = set()     # 같은 숫자의 재탐색을 막기 위한 set
    answer = -1         # 리턴할 값
    heapq.heappush(que, (a, 1))
    visited.add(a)
    while que:
        curr = heapq.heappop(que)
        if curr[0] == b:
            answer = curr[1]
            break
        elif curr[0] > b:
            break
        tempNum = curr[0] * 2
        if tempNum not in visited:
            heapq.heappush(que, (tempNum, curr[1] + 1))
            visited.add(tempNum)
        tempNum = curr[0] * 10 + 1
        if tempNum not in visited:
            heapq.heappush(que, (tempNum, curr[1] + 1))
            visited.add(tempNum)
    return answer


if __name__ == "__main__":
    A, B = map(int, input().strip().split())

    print(minDiverse(A, B))
