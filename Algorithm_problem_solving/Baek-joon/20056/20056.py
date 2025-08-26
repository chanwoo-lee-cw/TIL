# https://www.acmicpc.net/problem/20056

from dataclasses import dataclass
import sys

input_readline = sys.stdin.readline
DIRECTION = [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [0, -1], [-1, -1]]


@dataclass
class FireBall:
    mass: int
    speed: int
    direction: int


class MagicShark:
    """
    메이지 상어가 파이어 볼을 던졌을 때 시뮬레이션
    """
    def __init__(
            self,
            matrix_size,
    ):
        """
        :param matrix_size: 매트릭스의 사이즈 
        """
        self.matrix_size = matrix_size
        self.matrix = {}

    def repositioning(self, position: tuple) -> tuple:
        """
        매트릭스의 자표값을 다시 계산하는 함수
        :param position: n번 이동했을 때 좌표 값
        :return: 재배치된 좌표 값 ex) (0, 3)
        """
        y, x = position
        y, x = y % self.matrix_size, x % self.matrix_size
        if y < 0:
            y = self.matrix_size - y
        if x < 0:
            x = self.matrix_size - x
        return y, x

    def set_fireball(
            self,
            position: tuple,
            fireball: FireBall
    ) -> None:
        """
        해당 위치에 파이어볼을 배치한다.
        :param position: 배치할 좌표
        :param fireball: 파이어볼의 정보
        """
        self.__set_move_fireball(self.matrix, position, fireball)

    def __set_move_fireball(
            self,
            matrix: dict,
            position: tuple,
            fireball: FireBall
    )-> None:
        if position not in matrix:
            matrix[position] = []
        matrix[position].append(fireball)

    def command_move_event(
            self,
            move_count: int
    ):
        """
        파이어 볼을 move_count 만큼 움직인다.
        :param move_count: 파이어볼이 움직일 횟수
        :return: 
        """
        for _ in range(move_count):
            self.__move_event()

    def __move_event(self):
        updated_matrix = {}
        for position, fireballs in self.matrix.items():
            for fireball in fireballs:
                fireball: FireBall
                direction = DIRECTION[fireball.direction]
                move_position = (
                    position[0] + fireball.speed * direction[0],
                    position[1] + fireball.speed * direction[1]
                )
                move_position = self.repositioning(move_position)
                self.__set_move_fireball(updated_matrix, move_position, fireball)
        self.__merge_fireball(updated_matrix)
        self.matrix = updated_matrix

    def __merge_fireball(
            self,
            matrix: dict
    ):
        """
        움직인 이후에 파이어볼을 합치고, 분해한다.
        :param matrix: 업데이트된 파이어볼의 좌표 
        """
        for position, fireballs in matrix.items():
            if len(fireballs) >= 2:
                is_odd = 0
                sum_mass = 0
                sum_speed = 0
                for fireball in fireballs:
                    fireball: FireBall
                    is_odd += fireball.direction % 2
                    sum_mass += fireball.mass
                    sum_speed += fireball.speed
                divided_mass = sum_mass // 5
                divided_speed = sum_speed // len(fireballs)
                new_direction = []
                matrix[position] = []
                if divided_mass != 0:
                    if is_odd == 0 or is_odd == len(fireballs):
                        new_direction = [0, 2, 4, 6]
                    else:
                        new_direction = [1, 3, 5, 7]
                for direction in new_direction:
                    matrix[position].append(FireBall(divided_mass, divided_speed, direction))

    def print_fireball_mass(self):
        """
        남은 파이어볼의 질량을 Print
        """
        sum_fireball_mass = 0
        for values in self.matrix.values():
            for fireball in values:
                fireball: FireBall
                sum_fireball_mass += fireball.mass
        print(sum_fireball_mass)


if __name__ == "__main__":
    N, M, K = map(int, input_readline().strip().split())
    magicShark = MagicShark(N)
    for _ in range(M):
        r, c, m, s, d = map(int, input_readline().strip().split())
        position = (r - 1, c - 1)
        fireball = FireBall(m, s, d)
        magicShark.set_fireball(position, fireball)
    magicShark.command_move_event(K)
    magicShark.print_fireball_mass()
