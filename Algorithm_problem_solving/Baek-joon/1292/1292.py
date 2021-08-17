# https://www.acmicpc.net/problem/1292
from sys import stdin

input = stdin.readline

"""
[1,2,2,3,3,3 ... n] 같은 배열에서 a~b항목까지의 합
"""
def solution(a, b):
    number_list = []
    for i in range(1, 100):
        number_list += [i] * i
        if len(number_list) > 1000:
            break
    return sum(number_list[a - 1:b])


if __name__ == "__main__":
    A, B = map(int, input().split())
    print(solution(A, B))
