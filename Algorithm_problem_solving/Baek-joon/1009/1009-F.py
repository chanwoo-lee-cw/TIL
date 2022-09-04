from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    t = int(input().strip())
    for _ in range(t):
        a, b = map(int, input().strip().split())
        num = a
        cnt = 1
        for i in range(2, b + 1):
            num = (num * a) % 10
            if a == num:
                cnt = i
                break
        if cnt - 1 > 1:
            b = b % (cnt - 1)

        num = 1
        for i in range(b):
            num = (num * a) % 10
        print(num)
