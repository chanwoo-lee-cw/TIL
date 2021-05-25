from sys import stdin

input = stdin.readline

# https://www.acmicpc.net/problem/12174
if __name__ == "__main__":
    t = int(input().strip())    # 테스트케이스 수
    for i in range(t):
        b = int(input().strip())    # 글자 수
        intputLine = input().strip()
        output = [None] * b     # 문자를 저장해둘 배열
        for j in range(b):
            spelling = intputLine[8 * j: 8 * (j + 1)]  # 8글자씩 자른다.
            this_ascii = 0
            for k in range(1, 9):
                this_ascii += 2 ** (k - 1) if spelling[-k] == 'I' else 0
            output[j] = chr(this_ascii)
        print(f'Case #{i + 1}: {"".join(output)}')
