import sys
from copy import deepcopy
from collections import deque

input = sys.stdin.readline

way = ((-1, 0), (1, 0), (0, -1), (0, 1))


def find_island(y, x, island_num):
    queue = deque()
    queue.append([y, x])
    while queue:
        y, x = queue.popleft()
        if not find_area[y][x]:
            continue
        find_area[y][x] = False
        island[(y, x)] = island_num
        for to_y, to_x in way:
            mv_y = y + to_y
            mv_x = x + to_x
            if mv_y < 0 or mv_x < 0 or mv_y >= N or mv_x >= N or find_area[y][x]:
                continue
            queue.append([mv_y, mv_x])


def link_bridge(y, x, island_num):
    queue = deque()
    for to_y, to_x in way:
        mv_y = y + to_y
        mv_x = x + to_x
        if mv_y < 0 or mv_x < 0 or mv_y >= N or mv_x >= N:
            continue
        if island.get((mv_y, mv_x)) == island_num:
            continue
        queue.append([mv_y, mv_x, 0])
    while queue:
        y, x, cnt = queue.popleft()
        if island.get((y, x)) != None and island.get((y, x)) != island_num:
            return cnt
        if visited[y][x]:
            continue
        visited[y][x] = True
        for to_y, to_x in way:
            mv_y = y + to_y
            mv_x = x + to_x
            if mv_y < 0 or mv_x < 0 or mv_y >= N or mv_x >= N:
                continue
            if island.get((mv_y, mv_x)) == island_num:
                continue
            queue.append([mv_y, mv_x, cnt+1])
    return float("inf")


if __name__ == "__main__":
    N = int(input().strip())
    area = [[0] * N for _ in range(N)]
    for i in range(N):
        in_line = list(map(int, input().strip().split()))
        for j in range(N):
            area[i][j] = True if in_line[j] == 1 else False

    island = {}
    island_num = 1

    find_area = deepcopy(area)
    for i in range(N):
        for j in range(N):
            if find_area[i][j]:
                find_island(i, j, island_num)
                island_num += 1
    del find_area
    min_num = float("inf")
    for y, x in island:
        visited = [[False] * N for _ in range(N)]
        min_num = min(min_num, link_bridge(y, x, island.get((y, x))))
    print(min_num)
