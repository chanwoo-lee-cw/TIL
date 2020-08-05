import sys
from copy import deepcopy
from collections import deque

input = sys.stdin.readline

way = ((-1, 0), (1, 0), (0, -1), (0, 1))

# contiue 할지 확인하는 함수
def do_continue(view_type, matrix, color, y, x):
    # 평범할 때
    if view_type:
        if matrix[y][x] != color:
            return True
    # 색약일 때
    else:
        # 파란색일때
        if color == 3:
            if matrix[y][x] != color:
                return True
        # 빨강, 녹색일때
        else:
            if matrix[y][x] == 3 or matrix[y][x] == 0:
                return True
    return False


def bfs(y, x, color, view_type):
    queue = deque()
    queue.append([y, x])
    # 색약인지 평범인지에 따라 BFS배열 지정
    if view_type:
        matrix = nomal_area
    else:
        matrix = blind_area
    while queue:
        y, x = queue.popleft()
        # 색약인지에 따라 continue 여부
        if do_continue(view_type, matrix, color, y, x):
            continue
        # 이미 탐색한 곳이라는 표시로 0
        matrix[y][x] = 0
        for to_y, to_x in way:
            mv_y = y + to_y
            mv_x = x + to_x
            # 배열 밖이면 continue
            if mv_y < 0 or mv_x < 0 or mv_y >= N or mv_x >= N:
                continue
            # 색약인지에 따라 continue 여부
            elif do_continue(view_type, matrix, color, mv_y, mv_x):
                continue
            queue.append([mv_y, mv_x])


if __name__ == "__main__":
    N = int(input().strip())
    area = [[0] * N for _ in range(N)]
    # 빠른 탐색을 위한 문자 대신 int형으로 저장
    for i in range(N):
        in_line = input().strip()
        for j in range(N):
            if in_line[j] == 'R':
                area[i][j] = 1
            elif in_line[j] == 'G':
                area[i][j] = 2
            elif in_line[j] == 'B':
                area[i][j] = 3
    # 평범할 때, 영역의 수 탐색
    nomal = 0
    blind = 0
    nomal_area = deepcopy(area)
    blind_area = deepcopy(area)

    for i in range(N):
        for j in range(N):
            # 보통일 때
            if nomal_area[i][j] != 0:
                bfs(i, j, area[i][j], True)
                nomal += 1
            # 적록 색맹일 때
            if blind_area[i][j] != 0:
                bfs(i, j, area[i][j], False)
                blind += 1
    del nomal_area
    del blind_area

    print(nomal, blind)
