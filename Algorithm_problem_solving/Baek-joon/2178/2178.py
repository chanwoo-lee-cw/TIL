import sys
from collections import deque
input = sys.stdin.readline
way = ((-1, 0), (1, 0), (0, -1), (0, 1))


def BFS():
    # initial setting
    que = deque()
    que.append([1, 1, 1])

    # Repeat until que is empty
    while que:
        y, x, weight = que.popleft()
        # If it's a wall or is visited, next
        if not board[y][x]:
            continue
        # If you arrive, print out the distance and break
        if x == M and y == N:
            print(weight)
            break
        # Setting to False as a sign of visit
        board[y][x] = False
        # Explore all four directions and put them in the queue if you can.
        for next in way:
            next_x = x + next[0]
            next_y = y + next[1]
            if next_x <= 0 or next_x > M or next_y <= 0 or next_y > N or not board[next_y][next_x]:
                continue
            que.append([next_y, next_x, weight+1])


if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    board = [[False] * (M+1) for _ in range(N+1)]

    # Save it in array for quick search
    # If it's a way, put it in True, if it's a wall, then False.
    for i in range(N):
        temp = input().strip()
        for j in range(M):
            board[i+1][j+1] = True if int(temp[j]) == 1 else False
    BFS()
