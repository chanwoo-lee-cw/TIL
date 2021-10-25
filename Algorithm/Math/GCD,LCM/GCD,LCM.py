def lcm(a, b):
    this_gcd = gcd(a, b)
    return a // this_gcd * b


def gcd(a, b):
    if b > a:
        a, b = b, a
    while b:
        r = a % b
        a, b = b, r
    return a