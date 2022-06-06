from sys import stdin

input = stdin.readline


def gcd(a, b):
    while a != 0:
        a, b = b % a, a
    return b


if __name__ == "__main__":
    a, b = map(int, input().strip().split())
    numerator = int(b ** 0.5) - int(a ** 0.5)
    denominator = b - a
    if numerator == 0 or denominator == 0:
        print(0)
    else:
        this_gcd = gcd(numerator, denominator)
        print(f'{numerator // this_gcd}/{denominator // this_gcd}')
