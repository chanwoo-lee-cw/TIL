from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    n = int(input().strip())
    s = []  # 입력된 문자열
    t = []  # 완성된 문자열
    for i in range(n):
        s.append(input().strip())
    cnt = 0
    while s:
        if s[0] > s[-1]:
            # 뒤의 글자가 사전순으로 빠르면 뒤에서 pop
            t.append(s.pop(-1))
        elif s[0] < s[-1]:
            # 앞의 글자가 사전순으로 빠르면 앞에서 pop
            t.append(s.pop(0))
        else:
            # 앞 뒤의 문자가 같은 경우
            if len(s) == 1:
                # 만약 남은 문자가 1개라면 그냥 그거 넣는다.
                t.append(s.pop(0))
            for i in range(1, len(s)):
                if s[i] == s[-(i+1)]:
                    continue
                elif s[i] > s[-(i+1)]:
                    for j in range(i):
                        t.append(s.pop(-1))
                elif s[i] < s[-(i+1)]:
                    for j in range(i):
                        t.append(s.pop(0))
                break
            # 앞에서 두번째와 뒤에서 두번째의 문자를 비교한다.


        cnt += 1
        if cnt == 80:
            # 문자가 80개가 될 때마다 '\n' 추가
            t.append("\n")
            cnt = 0
    print(''.join(t))
