from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    t = int(input())
    tcase = []
    for i in range(t):
        tcase.append(int(input()))
    maxNum = max(tcase)

    fx = [0] * (maxNum + 1)
    gx = [0] * (maxNum + 1)

    for i in range(1, maxNum + 1):
        numSqrt = int(i ** 0.5)
        for j in range(1, numSqrt + 1):
            if i % j == 0:
                fx[i] += j
                if i // j != j:
                    fx[i] += i // j
        gx[i] += gx[i - 1] + fx[i]

    for item in tcase:
        print(gx[item])
