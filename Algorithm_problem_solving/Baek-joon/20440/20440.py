from sys import stdin
import heapq

input = stdin.readline

if __name__ == "__main__":
    n = int(input().strip())
    que = []

    for _ in range(n):
        num1, num2 = map(int, input().split())
        heapq.heappush(que, (num1, 1))
        heapq.heappush(que, (num2, -1))

    cnt = 0
    start = (0, 0)
    end = (0, 0)
    while que:
        curr = heapq.heappop(que)
        if curr[1] == 1:
            cnt += 1
            if cnt > start[1]:
                start = (curr[0], cnt)
        else:
            if cnt >= end[1]:
                end = (curr[0], cnt)
            cnt -= 1
    print(start[1])
    print(start[0], end[0])
