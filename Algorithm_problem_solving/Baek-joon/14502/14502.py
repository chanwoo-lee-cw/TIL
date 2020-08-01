import sys
from collections import deque
import copy
import heapq
input = sys.stdin.readline

way = ((-1, 0), (0, -1), (1, 0), (0, 1))


def bfs(lab, safe, room):
    simul = copy.deepcopy(lab)
    que2 = copy.deepcopy(que)

    while que2:
        y, x, date = que2.popleft()
        cnt = 0
        if simul[y][x] != 0:
            continue
        simul[y][x] = 2
        safe -= 1
        for next in way:
            next_x = x + next[0]
            next_y = y + next[1]
            if next_x < 0 or next_x >= M or next_y < 0 or next_y >= N or simul[next_y][next_x] != 0:
                continue
            que2.append([next_y, next_x, date+1])
            cnt += 1
        if room:
            heapq.heappush(heap, (cnt, date, y, x))
    return safe-3

def dfs(pos, wall):
    global maxsafe
    global safe
    if wall == 0:
        maxsafe = max(maxsafe, bfs(lab ,safe, False))
    else:
        for i in range(pos, len(heap)):
            cnt, date, y, x = heap[i]
            if (y, x) in virus:
                continue
            lab[y][x] = 1
            wall -= 1
            dfs(i+1, wall)
            wall += 1
            lab[y][x] = 0

    
N, M = map(int, input().strip().split())
lab = [[0]*(M) for _ in range(N)]
que = deque()
safe = 0
wall = 3
virus = {}

for i in range(N):
    insert = list(map(int, input().strip().split()))
    for j in range(M):
        lab[i][j] = insert[j]
        if insert[j] == 2:
            lab[i][j] = 0
            virus[(i,j)] = True
            que.append([i, j, 0])
            safe += 1
        elif insert[j] == 0:
            safe += 1
heap = []
room = bfs(lab, safe, True)

maxsafe = 0
dfs(0, wall)

print(maxsafe)

