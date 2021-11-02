from sys import stdin
from queue import Queue

input = stdin.readline

WAY = ((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 0), (0, 1), (1, -1), (1, 0), (1, 1))


class AreaMap:
    """
    AreaMap(너비, 높이, 필드의 모양)
    """
    def __init__(self, w: int, h: int, field: list):
        self.w = w
        self.h = h
        self.field = field

    """
    bfs(방문 여부, 탐색시작할 y좌표, 탐색 시작할 x좌표) - return void
    visited[i][j] - i, j 좌표를 이미 방문 했는지 여부
    """
    def bfs(self, visited: list, y: int, x: int):
        que = Queue()
        que.put((y, x))
        visited[y][x] = True
        while not que.empty():
            curr = que.get()
            for next in WAY:
                nextY = next[0] + curr[0]
                nextX = next[1] + curr[1]
                if nextY < 0 or nextX < 0 or nextY >= self.h or nextX >= self.w:
                    continue
                elif visited[nextY][nextX] or self.field[nextY][nextX] == 0:
                    continue
                que.put((nextY, nextX))
                visited[nextY][nextX] = True

    """
    return 섬의 수
    """
    def getIslandCount(self):
        answer = 0
        visited = [[False] * self.w for _ in range(self.h)]
        for i in range(self.h):
            for j in range(self.w):
                if self.field[i][j] == 1 and not visited[i][j]:
                    self.bfs(visited, i, j)
                    answer += 1
        return answer


if __name__ == "__main__":
    while True:
        w, h = map(int, input().strip().split())
        if w == 0 and h == 0:
            break
        filed = []
        for _ in range(h):
            filed.append(list(map(int, input().strip().split())))
        areaMap = AreaMap(w, h, filed)
        answer = areaMap.getIslandCount()
        print(answer)
