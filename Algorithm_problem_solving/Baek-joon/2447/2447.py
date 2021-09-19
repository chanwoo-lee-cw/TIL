import sys

sys.setrecursionlimit(10 ** 5)
input = sys.stdin.readline


def divide(xs, xe, ys, ye):
    if xe - xs == 0 or xe - xs == 1:
        star[ys][xs] = '*'
        return

    x_line = [xs]
    x_line.append(xs + (xe - xs) // 3)
    x_line.append(xs + (xe - xs) // 3 * 2)
    x_line.append(xe)

    y_line = [ys]
    y_line.append(ys + (ye - ys) // 3)
    y_line.append(ys + (ye - ys) // 3 * 2)
    y_line.append(ye)

    for i in range(3):
        for j in range(3):
            if i == 1 and j == 1:
                continue
            divide(x_line[j], x_line[j + 1], y_line[i], y_line[i + 1])


if __name__ == "__main__":
    N = int(input().strip())

    star = [[' '] * N for _ in range(N)]

    divide(0, N, 0, N)

    for i in range(N):
        for j in range(N):
            print(star[i][j], end='')
        print()
