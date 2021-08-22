# https://www.acmicpc.net/problem/1718
from sys import stdin

input = stdin.readline


def solution(plainText: str, key: str) -> list:
    alphbet: list = [chr(ord('a') + i)
                     for i in range(26)]        # 알파벳의 위치를 알아내기 위한 배열
    output: list = []
    lenPlain: int = len(plainText)       # 플레인 텍스트의 길이
    lenKey: int = len(key)               # 키 텍스트의 길이
    for i in range(lenPlain):
        if not ord('a') <= ord(plainText[i]) <= ord('z'):
            output.append(plainText[i])
        else:
            crypt = ord(plainText[i]) - (ord(key[i % lenKey]) + 1)
            output.append(alphbet[crypt])
    return ''.join(output)


if __name__ == "__main__":
    plainText = input()     # 공백 문자로만 이루어질 수도 있어서
    key = input().strip()
    print(solution(plainText, key))
