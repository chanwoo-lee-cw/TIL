import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
# 상하좌우를 저장한다.
way = ((-1, 0), (1, 0), (0, -1), (0, 1))

# 깊이 우선 탐색
def dfs(y, x):
    # 너비를 저장하기 위한 변수
    global area_size
    # 만약 갈 수 없는 방향이면 리턴
    if paper[y][x]:
        return
    # 이미 방문한 곳이라는 의미로 True로 바꿔준다.
    paper[y][x] = True
    area_size += 1
    # 상하좌우를 탐색하고 깊이 우성 탐색으로 찾는다.
    for to_y, to_x in way:
        move_y = y + to_y
        move_x = x + to_x
        if move_y < 0 or move_x < 0 or move_x >= N or move_y >= M or paper[move_y][move_x]:
            continue
        dfs(move_y, move_x)


if __name__ == "__main__":
    M, N, K = map(int, input().strip().split())
    paper = [[False] * (M+2) for _ in range(N+2)]
    area = []

    for _ in range(K):
        L_top_x, L_top_y, R_bottom_x, R_bottom_y = \
            map(int, input().strip().split())
        # 입력받은 모눈 종이를 채운다.
        for i in range(L_top_y, R_bottom_y):
            for j in range(L_top_x, R_bottom_x):
                paper[i][j] = True

    # 비어 있는 공간이 있다면 탐색
    for i in range(M):
        for j in range(N):
            if not paper[i][j]:
                area_size = 0
                dfs(i, j)
                area.append(area_size)
    # 출력
    area.sort()
    print(len(area))
    if area:
        for item in area:
            print(item, end=' ')
    else:
        print(0)
