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
            start = 0
            end = len(s) - 1
            flag = True
            while start < end:
                # 안쪽으로 하나씩 줄여가며, 먼저 사전순으로 작은게 있으면 1개만 팝한다.
                # 여러개 팝히면 CAABC 같은 경우 
                if end > 0:
                    end -= 1
                if start < len(s) - 2:
                    start += 1
                if s[start] < s[end]:
                    flag = True
                    break
                elif s[start] > s[end]:
                    flag = False
                    break
            # 따로 빼준 이유, 끝까지 모두 같은 경우 그냥 아무데서나 빼줘야 하기 때문
            if flag:
                t.append(s.pop(0))
            else:
                t.append(s.pop(-1))

    for i in range(n):
        if i != 0 and i % 80 == 0:
            print()
        print(t[i], end='')

