# https://www.acmicpc.net/problem/9935
from sys import stdin

input = stdin.readline


def strFiltering(sentence: str, filter: str):
    stk = []
    for item in sentence:
        stk.append(item)
        if stk[-1] == filter[-1] and len(stk) - len(filter) >= 0:
            if ''.join(stk[-len(filter):]) == filter:
                # 배열을 새로 선언하는 것보다 del로 뒷 부분을 지우는 것이 훨씬 빠르다.
                del stk[-len(filter):]
    return ''.join(stk) if stk else "FRULA"


if __name__ == '__main__':
    sentence = input().strip()
    filter = input().strip()
    print(strFiltering(sentence, filter))

