import sys

input = sys.stdin.readline


class GearGroup:
    def __init__(self, t, arr):
        self.t = t      # 톱니 바퀴의 갯수
        self.gears = [None] * (t+2)     # 톱니바퀴의 양 사이드는 None으로 하여 더이상 돌려지 말아야 할 부분을 찾는다.
        for i in range(t):
            self.gears[i+1] = Gear(arr[i])

    def turn_gears(self, n, turn, dirt):
        """
        재귀로 오른쪽과 왼쪽의 톱니바퀴를 돌려준다.
        Args:
            n (int): 현재 돌아가는 톱니바퀴의 번호
            turn (int): 톱니바퀴가 돌아가는 방향, 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향
            dirt (int): 0 양쪽의 톱니바퀴를 돌린다. 1 오른쪽 톱니바퀴를 돌린다. -1 왼쪽의 톱니바퀴를 돌린다.
        """
        if dirt <= 0 and self.gears[n - 1] is not None \
                and self.gears[n].left_tooth() != self.gears[n - 1].right_tooth():
            self.turn_gears(n - 1, 1 if turn == -1 else -1, -1)
        if dirt >= 0 and self.gears[n + 1] is not None \
                and self.gears[n].right_tooth() != self.gears[n + 1].left_tooth():
            self.turn_gears(n + 1, 1 if turn == -1 else -1, 1)
        self.gears[n].turn_gear(turn)

    def countS(self):
        """
        12시에 S극인 톱니바퀴의 갯수 반환
        Returns:
            int: 12시에 S극인 톱니바퀴의 갯수
        """
        cnt = 0
        for i in range(self.t):
            if self.gears[i + 1].tooth[0] == 1:
                cnt += 1
        return cnt


class Gear:
    def __init__(self, tooth: list):
        self.tooth = tooth      # 현재 톱니 바퀴의 이빨 극 모양

    def left_tooth(self):
        """
        왼쪽 톱니바퀴의 극을 반환한다.
        Returns:
            int: 왼쪽 톱니바퀴의 극
        """
        return self.tooth[6]

    def right_tooth(self):
        """
        오른쪽 톱니바퀴의 극을 반환한다.
        Returns:
            int: 오른쪽 톱니바퀴의 극
        """
        return self.tooth[2]

    def turn_gear(self, turn):
        """
        현재 톱니바퀴를 돌린다.
        Args:
            turn (int): 톱니바퀴가 돌아가는 방향, 1인 경우는 시계 방향, -1인 경우는 반시계 방향
        """
        if turn == 1:
            self.tooth.insert(0, self.tooth.pop())
        else:
            self.tooth.append(self.tooth.pop(0))


if __name__ == "__main__":
    t = int(input().strip())
    arr = []
    for _ in range(t):
        temp = [int(item) for item in input().strip()]
        arr.append(temp)
    gear_group = GearGroup(t, arr)
    k = int(input().strip())
    for _ in range(k):
        n, turn = map(int, input().strip().split())
        gear_group.turn_gears(n, turn, 0)
    print(gear_group.countS())
