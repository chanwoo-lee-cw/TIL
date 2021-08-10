# https://www.acmicpc.net/problem/1193
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    x = int(input().strip())
    sum = 0
    deno = 1
    while sum + deno < x:
        sum += deno
        deno += 1

    num = 1
    sum += 1

    while sum < x:
        sum += 1
        num += 1
        deno -= 1

    if (deno + num) % 2 == 0:
        num, deno = deno, num

    print(f'{num}/{deno}')
