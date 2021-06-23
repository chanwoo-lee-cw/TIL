from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    t = int(input())
    MAX = 1000000
    tcase = []
    for i in range(t):
        tcase.append(int(input()))
    maxNum = max(tcase)

    fx = [1] * (maxNum + 1)
    gx = [0] * (maxNum + 1)

    for i in range(2, maxNum + 1):
        for j in range(1, MAX + 1):
            if i * j > maxNum:
                break
            fx[i * j] += i

    for i in range(1, maxNum + 1):
        gx[i] = gx[i - 1] + fx[i]

    for item in tcase:
        print(gx[item])
