from sys import stdin

input = stdin.readline

"""
findAlphabetPosition(str) - str문장 속에 있는 가장 먼저 알파벳이 사용되는 위치를 배열로 반환한다.
"""
def findAlphabetPosition(inputLine: str):
    answer = [-1] * 26
    cnt = 0
    for idx, alphabet in enumerate(inputLine):
        thisAlpha = ord(alphabet) - ord('a')
        if answer[thisAlpha] == -1:
            answer[thisAlpha] = idx
            cnt += 1
        if cnt == 26:
            break
    return answer


if __name__ == "__main__":
    inputLine = input().strip()
    answer = findAlphabetPosition(inputLine)
    print(' '.join(str(i) for i in answer))
