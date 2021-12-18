from sys import stdin

input = stdin.readline

# 사유 시간 초과
def makeOutput(inputList: str) -> str:
    output = []
    cursor = 0
    for item in inputList:
        if item == '<':
            if cursor > 0:
                cursor -= 1
        elif item == '>':
            if cursor < len(output):
                cursor += 1
        elif item == '-':
            if cursor - 1 >= 0:
                output.pop(cursor - 1)
                cursor -= 1
        else:
            output.insert(cursor, item)
            cursor += 1

    return ''.join(output)


if __name__ == "__main__":
    t = int(input().strip())
    for _ in range(t):
        tempStr = input().strip()
        print(makeOutput(tempStr))
