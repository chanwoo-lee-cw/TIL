from sys import stdin

input = stdin.readline


def checkBalanceStr(inLine: str):
    stk = []    # 괄호의 형식을 저장할 스택
    for item in inLine:
        if item == '[':
            stk.append('[')
        elif item == '(':
            stk.append('(')
        elif item == ']':
            if not stk or stk.pop() != '[':
                return "no"
        elif item == ')':
            if not stk or stk.pop() != '(':
                return "no"
    return "yes" if not stk else "no"


if __name__ == "__main__":
    while True:
        inLine = input()
        if len(inLine) == 2 and inLine[0] == '.':
            break
        print(checkBalanceStr(inLine))
