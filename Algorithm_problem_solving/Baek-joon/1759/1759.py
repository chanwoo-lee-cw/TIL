from sys import stdin

input = stdin.readline

VOWEL_LIST = ['a', 'e', 'i', 'o', 'u']


def dfs(output, pos, vowel, consonant):
    if len(output) == l and vowel >= 1 and consonant >= 2:
        print("".join(output))
    else:
        for i in range(pos, c):
            output.append(alphaList[i])
            if alphaList[i] in VOWEL_LIST:
                vowel += 1
            else:
                consonant += 1
            dfs(output, i + 1, vowel, consonant)
            if alphaList[i] in VOWEL_LIST:
                vowel -= 1
            else:
                consonant -= 1
            output.pop()


if __name__ == "__main__":
    l, c = map(int, input().strip().split())
    alphaList = list(input().strip().split())
    alphaList.sort()
    output = []
    dfs(output, 0, 0, 0)
