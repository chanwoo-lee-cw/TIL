def gcd(a, b):
    while a != 0:
        a, b = b % a, a
    return b


if __name__ == "__main__":
    a, b = map(int, input().split())
    numerator = 0
    curr = a ** 0.5 - 10
    if curr <= 0:
        curr = 1
    while True:
        if float(curr * curr) > b:
            break
        elif a < float(curr * curr):
            numerator += 1
        curr += 1
    denominator = b - a
    if numerator == 0 or denominator == 0:
        print(0)
    else:
        this_gcd = gcd(numerator, denominator)
        print(f'{numerator // this_gcd}/{denominator // this_gcd}')
