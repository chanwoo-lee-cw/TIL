# https://www.acmicpc.net/problem/1759
from sys import stdin

input = stdin.readline

VOWEL_LIST = ['a', 'e', 'i', 'o', 'u']      # 모음을 저장한 배열

"""
깊이 우선 탐색으로 모음 1개 이상 자음 2개 이상, 길이가 l인 문자열을 찾는다.

매개변수
output - 완성된 문자열
pos - 다음으로 검색할 위치
vowel - 사용된 모음의 수
consonant - 사용된 자음의 갯수
"""
def dfs(output, pos, vowel, consonant):
    if len(output) == l and vowel >= 1 and consonant >= 2:
        print("".join(output))
    else:
        for i in range(pos, c):
            output.append(alphaList[i])
            if alphaList[i] in VOWEL_LIST:
                dfs(output, i + 1, vowel + 1, consonant)
            else:
                dfs(output, i + 1, vowel, consonant+1)
            output.pop()


if __name__ == "__main__":
    l, c = map(int, input().strip().split())    # 원하는 문자열 길이, 가능한 문자의 갯수
    alphaList = list(input().strip().split())   # 필요한 문자의 배열
    alphaList.sort()
    output = []
    dfs(output, 0, 0, 0)
