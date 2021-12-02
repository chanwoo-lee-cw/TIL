# https://www.acmicpc.net/problem/23805
from sys import stdin

input = stdin.readline


def draw_snail(n):
    """
    돌아간 ㄹ이 그려진 배열을 반환

    Args:
        n (int): 셀의 크기

    Returns:
        list: 돌아간 ㄹ이 그려진 배열
    """
    snail = [['@'] * (n * 5) for _ in range(n * 5)]
    for k in range(4):
        for i in range(n):
            for j in range(n):
                snail[k * n + i][n * 3 + j] = ' '
                snail[(k + 1) * n + i][n + j] = ' '
    return snail


if __name__ == '__main__':
    n = int(input().strip())
    snail = draw_snail(n)
    for item in snail:
        print(''.join(item))
