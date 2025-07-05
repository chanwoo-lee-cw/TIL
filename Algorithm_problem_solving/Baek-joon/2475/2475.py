# https://www.acmicpc.net/problem/2475
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    inputList = input().strip().split()
    sumByInput = 0
    for item in inputList:
        item = int(item)
        sumByInput += item ** 2
    print(sumByInput % 10)
