class AntMove:
    def __init__(self, h, w, y, x):
        self.h = h
        self.w = w
        self.y = y
        self.x = x
        self.way = [1, 1]

    def move(self, t):
        for i in range(t):
            self.y += self.way[1]
            self.x += self.way[0]
            if self.y == self.h or self.y == 0:
                self.way[1] = -self.way[1]
            if self.x == self.w or self.x == 0:
                self.way[0] = -self.way[0]
        return self.y, self.x


if __name__ == "__main__":
    w, h = map(int, input().strip().split())
    x, y = map(int, input().strip().split())
    t = int(input())
    ant_move = AntMove(h, w, y, x)
    result = ant_move.move(t)
    print(f"{result[1]} {result[0]}")
