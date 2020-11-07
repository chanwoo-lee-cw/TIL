import sys

input = sys.stdin.readline

way = (
    (0, 1)
    (-1, 0)
    (1, 0)
    (0, -1)
)
shark_pos = ()
shark_size = 2
fish_count = [0] * 7

def bfs():
    pass

if __name__ == "__main__":
    N = int(input())
    matrix = [[0]*N for _ in range(N)]
    for i in range(N):
        tile = list(map(int, input().strip().split()))
        for j in range(N):
            matrix[i][j] = tile[j]
            if tile[j] == 9:
                shark_pos = (i, j)
            elif tile[j] > 0:
                fish_count[tile[j]] += 1
    
    bfs()