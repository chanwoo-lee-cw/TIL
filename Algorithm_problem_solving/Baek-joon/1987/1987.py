import sys
input = sys.stdin.readline

way = ((0, 1), (1, 0), (0, -1), (-1, 0))


def dfs(y, x):
    cnt = 0
    global move
    global max_move
    for to_y, to_x in way:
        move_y = y + to_y
        move_x = x + to_x
        if move_x < 0 or move_y < 0 or move_x >= C or move_y >= R or visited[board[move_y][move_x]]:
            continue
        cnt += 1
        visited[board[move_y][move_x]] = True
        move += 1
        dfs(move_y, move_x)
        move -= 1
        visited[board[move_y][move_x]] = False
    if cnt == 0:
        max_move = max(max_move, move)
    if max_move == 26:
        return


if __name__ == "__main__":
    R, C = map(int, input().strip().split())
    board = [[0] * C for _ in range(R)]
    visited = [False] * 27
    for i in range(R):
        in_line = input().strip()
        for j in range(C):
            board[i][j] = ord(in_line[j])-ord('A')
    max_move = 0
    move = 1
    visited[board[0][0]] = True
    dfs(0, 0)
    print(max_move)
