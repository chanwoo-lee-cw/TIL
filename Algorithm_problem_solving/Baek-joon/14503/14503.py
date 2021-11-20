import sys

input = sys.stdin.readline

DIRT = ((-1, 0), (0, 1), (1, 0), (0, -1))


class Room:
    def __init__(self, n, m, room, r, c, d):
        self.n, self.m = n, m       # 방의 세로, 가로 크기
        self.room = room        # 현재 방의 모양
        self.robot = Robot(r, c, d)     # 로봇에 대한 정보
        self.visited = [[False] * m for _ in range(n)]  # 해당위치를 청ㅇ소했는지에 대한 질문

    def clearRoom(self):
        """
        로봇이 청소할 수 있는 방의 수를 리턴한다.

        Returns:
            int: 청소된 방의 수
        """
        answer = 0  # 현재 방이 청소가 된다면 ++
        while True:
            curr = self.robot.position
            if not self.visited[curr[0]][curr[1]]:
                # 후진이 아닌 전진을 통해 현재 위치에 왓을때
                self.visited[curr[0]][curr[1]] = True
                answer += 1
            flag = False        # 계속 반복할것인지를 확인하는 플래그
            for i in range(1, 5):
                nextMv = self.robot.d - i   # 이동할 방향으로 로봇이 다음으로 바라볼 방향
                nextMv = nextMv if nextMv >= 0 else 4 + nextMv
                nextY = curr[0] + DIRT[nextMv][0]
                nextX = curr[1] + DIRT[nextMv][1]
                if self.room[nextY][nextX] == 1 or self.visited[nextY][nextX]:
                    continue
                flag = True
                break
            if flag:
                # 만약 청소가 가능하다면 이동
                self.robot.setPosition(nextY, nextX)
                self.robot.d = nextMv
            else:
                # 청소가 불가능하다면 후진 or 로봇 종료
                dirt = (self.robot.d + 2) % 4
                nextY = self.robot.position[0] + DIRT[dirt][0]
                nextX = self.robot.position[1] + DIRT[dirt][1]
                if self.room[nextY][nextX] != 1:
                    self.robot.setPosition(nextY, nextX)
                else:
                    break
        return answer


class Robot:
    def __init__(self, r, c, d):
        self.position = (r, c)      # 로봇의 좌표
        self.d = d          # 로봇이 바라보고 있는 방향

    def setPosition(self, r, c):
        self.position = (r, c)


if __name__ == "__main__":
    n, m = map(int, input().strip().split())
    r, c, d = map(int, input().strip().split())
    arr = []
    for i in range(n):
        arr.append(list(map(int, input().strip().split())))
    room = Room(n, m, arr, r, c, d)
    print(room.clearRoom())
