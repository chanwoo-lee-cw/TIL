import sys

input = sys.stdin.readline

# 최대 공약수 리턴
def gcd(a, b):
    a = abs(a)
    b = abs(b)
    divisor = 0     # 유클리드 호제법을 위한 수 저장
    if a < b:
        a, b = b, a
    while b > 0:
        divisor = a % b
        if divisor == 0:
            divisor = b
            break
        a, b = b, divisor
    return divisor

# 두 수의 최소 공배수 리턴
def lcm(a, b):
    c = gcd(a, b)   # 최대공약수를 저장한다.
    answer = c
    answer *= a // c
    answer *= b // c
    return answer


if __name__ == "__main__":
    t = int(input())        # 테스트케이스의 수
    for i in range(t):
        a, b = map(int, input().split())
        print(lcm(a, b))