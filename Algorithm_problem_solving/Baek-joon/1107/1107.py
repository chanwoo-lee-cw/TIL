# https://www.acmicpc.net/problem/1107
from sys import stdin

input = stdin.readline


def dfs(n: int, numList: list, nLen: int, output: list):
    answer: int = float("inf")
    if len(output) == nLen:
        temp: int = int(''.join(output))
        return abs(temp - int(n)) + len(str(temp))
    else:
        for item in numList:
            output.append(item)
            answer = min(answer, dfs(n, numList, nLen, output))
            output.pop()
    return answer


def solution(n: int, outOrder: list):
    numList: list = []
    nLen: int = len(n)
    answer = abs(int(n) - 100)
    output: list = []
    for num in range(10):
        if num in outOrder:
            continue
        numList.append(str(num))
    for i in range(nLen - 1):
        output.append('0')
        answer = min(answer, dfs(n, numList, nLen, output))
    output = []
    answer = min(answer, dfs(n, numList, nLen, output))
    return answer


if __name__ == '__main__':
    N: str = input().strip()
    M: int = int(input().strip())
    outOrder: list = list(map(int, input().strip().split()))
    print(solution(N, outOrder))
