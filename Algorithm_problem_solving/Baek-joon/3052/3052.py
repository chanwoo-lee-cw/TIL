# https://www.acmicpc.net/problem/3052
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    inputList = [int(input().strip()) for _ in range(10)]
    dividedSet = set()
    for item in inputList:
        dividedSet.add(item % 42)
    print(len(dividedSet))
