from sys import stdin

input = stdin.readline


def getSquare(s: str):
    if not s:
        return 0
    for i in range(1, len(s) // 2 + 1):
        if len(s) % i != 0:
            # 아예 n으로 나누어 떨어지지 않을 때
            continue
        a = s[:i]
        flag = True
        for j in range(i, len(s), i):
            if a != s[j:j + i]:
                # 만약 하나라도 안 맞는게 있으면 더이상 확인해볼 필요가 없다.
                flag = False
                break
        if flag:
            # 현재 문자열으로 나눈다.
            return len(s) // i
    return 1


if __name__ == "__main__":
    while True:
        s = input().strip()
        if s == ".":
            break
        print(getSquare(s))
