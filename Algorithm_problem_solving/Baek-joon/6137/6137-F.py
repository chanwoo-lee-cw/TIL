from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    n = int(input().strip())
    s = []        # 입력된 문자열
    t = []        # 완성된 문자열
    for i in range(n):
        s.append(input().strip())
    cnt = 0
    while s:
        if ord(s[0]) > ord(s[-1]):
            # 앞의 글자가 사전순으로 빠르면 앞에서 pop
            t.append(s.pop(-1))
        else:
            # 뒤의 글자가 사전순으로 빠르면 뒤에서 pop
            t.append(s.pop(0))
        cnt += 1
        if cnt == 80:
            # 문자가 80개가 될 때마다 '\n' 추가
            t.append("\n")
            cnt = 0
    print(''.join(t))

