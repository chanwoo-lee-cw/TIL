import sys
from collections import deque
from copy import deepcopy

input = sys.stdin.readline
way = ((-1, 0), (1, 0), (0, -1), (0, 1))


def bfs(y, x, deep):
    queue = deque()
    queue.append((y, x))
    while queue:
        y, x = queue.popleft()
        if copy_yard[y][x] <= deep:
            continue
        copy_yard[y][x] = -1
        for to_y, to_x in way:
            move_y = y + to_y
            move_x = x + to_x
            if move_x < 0 or move_y < 0 or move_x >= N or move_y >= N or copy_yard[move_y][move_x] <= deep:
                continue
            queue.append((move_y, move_x))


if __name__ == "__main__":
    N = int(input().strip())
    yard = [[0]*N for _ in range(N)]
    max_safe = -1
    height_set = set()
    height_set.add(0)

    for i in range(N):
        in_height = list(map(int, input().strip().split()))
        for j in range(N):
            yard[i][j] = in_height[j]
            height_set.add(in_height[j])

    for deep in height_set:
        copy_yard = deepcopy(yard)
        safe = 0
        for i in range(N):
            for j in range(N):
                if copy_yard[i][j] > deep:
                    bfs(i, j, deep)
                    safe += 1
        max_safe = max(max_safe, safe)

    print(max_safe)

    # for item in yard:
    #     print(item)
