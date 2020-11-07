# https://www.acmicpc.net/problem/20056

import sys
input = sys.stdin.readline

distance = [(0, 1), (1, 1), (1, 0), (1, -1),
            (0, -1), (-1, -1), (-1, 0), (-1, 1)]


def fire_move(field):
    is_unioned = []
    new_field = {}
    for item in field:
        m, d, s = field(item)
        r, c = field(item)
        r = distance[d][0]
        c = distance[d][1]
        if r >= N or r < 0 or c >= N or c < 0:
            continue
        if (r, c) in new_field:
            duplicated_fire = new_field[(r, c)]
            if duplicated_fire[1] % 2 != d % 2:
                new_field[(r, c)] = [duplicated_fire[0] +
                                     m, d, duplicated_fire[2]+s, True]
                if not duplicated_fire[3]:
                    is_unioned.add((r, c))
            else:
                new_field[(r, c)] = [duplicated_fire[0] + m,
                                     d, duplicated_fire[2]+s, False]
        else:
            new_field[(r, c)] = [m, d, s, False]
    del field
    return is_unioned, new_field


if __name__ == "__main__":
    N, M, K = map(int, input().split())
    field = {}
    for _ in range(M):
        r, c, m, d, s = map(int, input().split())
        # massive, speed, direction, all_odd_even
        field[(r, c)] = [m, d, s, False]

    for i in range(K):
        is_unioned, field = fire_move(field)
        for unioned in is_unioned:
            m, d, s, check = field[unioned]

