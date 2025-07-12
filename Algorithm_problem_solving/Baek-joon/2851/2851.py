# https://www.acmicpc.net/problem/2851
import sys

input = sys.stdin.readline

"""
마리오가 먹은 버섯 점수가 최대한 100 가깝게 먹은 최대 점수를 출력합니다.

Parameters:
    mushrooms (list[int]): 버섯 점수의 리스트 

Returns:
    int: 버섯 점수의 최대합
"""
def mario_mushroom(mushrooms: list):
    answer = 5000
    save_point = 5000
    point_sums = 0
    for item in mushrooms:
        point_sums += item
        terms = abs(100 - point_sums)
        if terms <= save_point:
            save_point = terms
            answer = point_sums
    return answer


if __name__ == "__main__":
    mushrooms = []
    for _ in range(10):
        mushrooms.append(int(input()))

    print(mario_mushroom(mushrooms))
