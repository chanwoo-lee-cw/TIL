# https://www.acmicpc.net/problem/2292
from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    N = int(input().strip())
    needRoute = 1
    honeycomb = 1
    while honeycomb < N:
        honeycomb += 6 * needRoute
        needRoute += 1
    print(needRoute)
