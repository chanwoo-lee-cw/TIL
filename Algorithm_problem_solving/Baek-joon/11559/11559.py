import sys
from collections import deque

input = sys.stdin.readline
way = [(-1, 0), (1, 0), (0, -1), (0, 1)]

# 너비 우선 탐색으로 제거할 뿌요를 찾아내는 함수
def bfs(y, x, color):
    que = deque()
    que.append((y, x))
    boom = {}
    while que:
        y, x = que.popleft()
        if visited[y][x]:
            continue
        visited[y][x] = True
        boom[(y, x)] = True
        for to_y, to_x in way:
            mv_y = y + to_y
            mv_x = x + to_x
            if mv_x < 0 or mv_y < 0 or mv_x >= 12 or mv_y >= 6 or \
                    visited[mv_y][mv_x] or board[mv_y][mv_x] != color:
                continue
            que.append((mv_y, mv_x))
    return boom

# 터진 뿌요를 제거하고 앞으로 당기는 함수
def puyo(flag, repeat):
    repeat += 1
    for i in range(6):
        check = False
        j = -1
        while j < 11:
            j += 1
            if not check:
                if board[i][j] == '-':
                    start = j
                    check = True
            else:
                if board[i][j] != '-':
                    end = j
                    check = False
                    board[i] = board[i][:start] + \
                        board[i][end:] + ['.'] * (end-start)
                    j = -1
    return repeat


if __name__ == "__main__":
    board = [['.']*12 for _ in range(6)]
    for i in range(12):
        in_line = input().strip()
        for j in range(6):
            board[j][11-i] = in_line[j]
    del in_line
    repeat = 0
    flag = True
    while flag:
        flag = False
        visited = [[False]*12 for _ in range(6)]
        for i in range(6):
            for j in range(12):
                if not visited[i][j] and board[i][j] != '.' and board[i][j] != '-':
                    boom = bfs(i, j, board[i][j])
                    # 찾아낸 뿌요가 4개 이상이면 터트린다.
                    if len(boom.keys()) >= 4:
                        flag = True
                        for y, x in boom:
                            board[y][x] = '-'
        del visited
        if flag:
            repeat = puyo(flag, repeat)

    print(repeat)
