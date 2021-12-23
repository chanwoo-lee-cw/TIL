from sys import stdin

input = stdin.readline


def moveItem(from_, to_):
    if from_:
        to_.append(from_.pop())


def makeOutput(inputList: str) -> str:
    front = []
    back = []
    for item in inputList:
        if item == '<':
            moveItem(front, back)
        elif item == '>':
            moveItem(back, front)
        elif item == '-':
            if front: front.pop()
        else:
            front.append(item)

    while back:
        moveItem(back, front)

    return ''.join(front)


if __name__ == "__main__":
    t = int(input().strip())
    for _ in range(t):
        tempStr = input().strip()
        print(makeOutput(tempStr))
