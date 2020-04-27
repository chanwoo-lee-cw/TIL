# https://www.acmicpc.net/problem/1202
# 보석 도둑
import sys
import heapq

input = sys.stdin.readline

# n개의 가방에 k개의 물건을 1개씩 넣는 문제. DP문제

def knap(k, item, backpack) :
    total_value = 0
    capable_gem = []

    for _ in range(k):
        capacity = heapq.heappop(backpack)

        while item and capacity >= item[0][0]:
            [_, value] = heapq.heappop(item)
            heapq.heappush(capable_gem, -value)

        if capable_gem:
            total_value -= heapq.heappop(capable_gem)
        elif not item: 
            break

    return total_value

if __name__ == "__main__" :

    (n, k) = map(int, input().strip().split())

    item = []
    backpack = []

    for _ in range(n) :
        m, v = map(int,input().split())
        heapq.heappush(item,[m, v])

    for _ in range(k) :
        c = int(input())
        heapq.heappush(backpack,c)

    print(knap(k, item, backpack))