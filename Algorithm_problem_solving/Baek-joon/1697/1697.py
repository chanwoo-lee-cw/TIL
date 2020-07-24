# https://www.acmicpc.net/problem/1697
import sys
from collections import deque
input = sys.stdin.readline

def BFS(N, K):
    # DP_list to avoid revisiting the place you visited
    dp = [float("inf")]*100101
    # Queue to find the nearest in chronological order.
    que = deque()
    # 현재 위치를 시작점으로 넣는다.
    que.append([N, 0])
    while que:
        pos, time = que.popleft()
        # If visited place, continue.
        if dp[pos] <= time:
            continue
        # Leave a sign
        dp[pos]=time
        # If you arrive where you want, the shortest route because you visited BFS.
        if pos == K:
            break
        if 2*pos <= 100100:
            que.append([2*pos, time+1])
        if pos+1 <= 100000:
            que.append([pos + 1, time+1])
        if pos-1 >= 0:
            que.append([pos - 1, time+1])
    print(time)

if __name__ == "__main__":
    N, K = map(int, input().strip().split())

    BFS(N, K)