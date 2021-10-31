from sys import stdin

input = stdin.readline


def dfs(s: list, t: list):
    if len(s) == 1:
        t.append(s.pop())
    elif ord(s[0]) > ord(s[-1]):
        t.append(s.pop(-1))
        t = dfs(s, t)
    elif ord(s[0]) < ord(s[-1]):
        t.append(s.pop(0))
        t = dfs(s, t)
    else:
        temp1 = dfs(s[:-1], t + s[-1:])
        temp2 = dfs(s[1:], t + s[:1])
        for i in range(len(t), n):
            if temp1[i] == temp2[i]:
                continue
            t = temp1 if temp1[i] < temp2[i] else temp2
            break
    return t


if __name__ == "__main__":
    n = int(input().strip())
    s = []  # 입력된 문자열
    t = []  # 완성된 문자열
    for i in range(n):
        s.append(input().strip())
    answer = dfs(s, t)
    for i in range(n):
        if i != 0 and i % 80 == 0:
            print()
        print(answer[i], end='')
