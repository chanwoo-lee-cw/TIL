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
        if que and que[0][0] == curr[0] and que[0][1] != curr[1]:
            # mos come in, out at same time
            curr = heapq.heappop(que)
            continue
        if curr[1] == 1:
            # in case, mos come in room
            cnt += 1
            if cnt > start[1]:
                start = (curr[0], cnt)
        else:
            # in case, mos come out room
            if cnt > end[1]:
                end = (curr[0], cnt)
            cnt -= 1
    print(f"{start[1]}\n{start[0]} {end[0]}")
