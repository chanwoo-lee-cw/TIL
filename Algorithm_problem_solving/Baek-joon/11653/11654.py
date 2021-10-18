from sys import stdin

input = stdin.readline


def factorization(n):
    sqrtN = int(n ** 0.5)
    answer = []
    for i in range(2, sqrtN + 1):
        while n % i == 0:
            answer.append(i)
            n = n // i
        if n == 1:
            break
    if n != 1:
        answer.append(n)
    return answer


if __name__ == '__main__':
    n = int(input())
    answer = factorization(n)
    print("\n".join(str(i) for i in answer))
