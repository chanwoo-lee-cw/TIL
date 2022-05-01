from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    n = int(input().strip())
    answer = 0

    for i in range(1, n):
        this_num = i
        splitSum = this_num
        while this_num > 0:
            splitSum += this_num % 10
            this_num = this_num // 10
        if splitSum == n:
            answer = i
            break
    print(answer)
