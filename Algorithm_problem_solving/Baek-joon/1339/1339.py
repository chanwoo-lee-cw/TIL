# https://www.acmicpc.net/problem/1339
from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    N = int(input().strip())
    words = {}

    for i in range(N):
        sentence = input().strip()
        digits = 10 ** (len(sentence) - 1)
        for j in range(len(sentence)):
            if sentence[j] in words:
                words[sentence[j]] = words[sentence[j]] + digits
            else:
                words[sentence[j]] = digits
            digits //= 10

    values = list(words.values())
    values.sort(reverse=True)

    alphaNum = 9
    sum = 0
    for item in values:
        sum += alphaNum * item
        alphaNum -= 1
    print(sum)
