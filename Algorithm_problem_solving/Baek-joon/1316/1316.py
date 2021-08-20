# https://www.acmicpc.net/problem/1316
from sys import stdin

input = stdin.readline

"""
단어가 그룹 단어라면 True, 아니면 False 리턴
line - 체크하고 싶은 단어
"""
def isGroupSentence(line):
    pre = '-'
    alphaSet = set()
    for alphabet in line:
        if alphabet == pre or alphabet not in alphaSet:
            pre = alphabet
            alphaSet.add(alphabet)
        else:
            return False
    return True


if __name__ == "__main__":
    N = int(input().strip())
    answer = 0
    for _ in range(N):
        line = input().strip()
        if isGroupSentence(line):
            answer += 1
    print(answer)
