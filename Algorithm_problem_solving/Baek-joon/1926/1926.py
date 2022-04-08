import queue
from copy import deepcopy
from sys import stdin

input = stdin.readline

WAY = ((1, 0), (-1, 0), (0, 1), (0, -1))


def get_paint_size(n: int, m: int, paint: list) -> tuple:
    """
    Returns the number of pictures drawn on paper and the size of the largest picture drawn on paper.

    :param n: width of paint
    :param m: length of paint
    :param paint: shape of paint
    :return: (number of paintings, size of the largest painting)
    """
    cnt = 0
    max_size = 0

    paint_copy = deepcopy(paint)

    for i in range(n):
        for j in range(m):
            if paint_copy[i][j] == 1:
                cnt += 1
                max_size = max(max_size, dfs(i, j, paint_copy))

    return cnt, max_size


def dfs(y, x, paint):
    que = queue.Queue()
    que.put([y, x])
    paint[y][x] = 0
    cnt = 1
    while not que.empty():
        curr_y, curr_x = que.get()
        for dist_y, dist_x in WAY:
            move_y = curr_y + dist_y
            move_x = curr_x + dist_x
            if move_y < 0 or move_x < 0 or move_y >= n or move_x >= m:
                continue
            if paint[move_y][move_x] == 0:
                continue
            que.put([move_y, move_x])
            paint[move_y][move_x] = 0
            cnt += 1
    return cnt


if __name__ == "__main__":
    n, m = map(int, input().strip().split())
    paint = []
    for _ in range(n):
        paint.append(list(map(int, input().strip().split())))
    paint_cnt, max_paint_size = get_paint_size(n, m, paint)
    print(f'{paint_cnt}\n{max_paint_size}')
