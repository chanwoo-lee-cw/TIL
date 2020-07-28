import sys
input = sys.stdin.readline

minusone = 0
zero = 0
one = 0


def divide(xs, xe, ys, ye):
    # print(xs, xe, ys, ye)
    if xe - xs == 0 or xe - xs == 1:
        return paper[ys][xs]

    global minusone
    global zero
    global one

    # print((xs + xe)//3)
    x_line = [xs]
    x_line.append(xs + (xe - xs)//3)
    x_line.append(xs + (xe - xs)//3 * 2)
    x_line.append(xe)

    # print((ys + ye)//3)
    y_line = [ys]
    y_line.append(ys + (ye - ys)//3)
    y_line.append(ys + (ye - ys)//3 * 2)
    y_line.append(ye)

    out = []

    for i in range(3):
        for j in range(3):
            out.append(divide(x_line[j], x_line[j+1], y_line[i], y_line[i+1]))

    if out[0] != 2 and len(out) == out.count(out[0]):
        return out[0]
    else:
        for item in out:
            if item == -1:
                minusone += 1
            elif item == 0:
                zero += 1
            elif item == 1:
                one += 1
        return 2


if __name__ == "__main__":
    N = int(input().strip())
    paper = []
    for _ in range(N):
        paper.append(list(map(int, input().strip().split())))

    out = divide(0, N, 0, N)
    if out == -1:
        minusone += 1
    if out == 0:
        zero += 1
    if out == 1:
        one += 1
    print(minusone)
    print(zero)
    print(one)
