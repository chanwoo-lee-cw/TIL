# https://www.acmicpc.net/problem/16953
# 다른 사람의 풀이
from sys import stdin

input = stdin.readline

"""
b에서 부터 역순으로 찾아가는 방법
"""
def minDiverse(a, b):
    answer = 1
    while a < b:
        answer += 1
        if str(b)[-1] == '1':
            # 2배씩 증가한다는 특성상 맨 뒤에 1이 붙는 경우는 오직 첫번째 케이스 밖에 없다.
            b //= 10
        elif b % 2 == 0:
            b //= 2
        else:
            return -1

    return answer if a == b else -1


if __name__ == "__main__":
    A, B = map(int, input().strip().split())
    print(minDiverse(A, B))
