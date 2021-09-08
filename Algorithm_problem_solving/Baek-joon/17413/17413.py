import sys

input = sys.stdin.readline


def reverse_str(sentence: str):
    stk = []
    while sentence:
        if sentence[0] == '<':
            temp = sentence.find('>') + 1
            stk.append(sentence[:temp])
        else:
            temp = sentence.find('<')
            if temp == -1:
                temp = len(sentence)
            splited = sentence[:temp].split(' ')
            for i in range(len(splited)):
                for_reversed = list(splited[i])
                for_reversed.reverse()
                splited[i] = ''.join(for_reversed)
            stk.append(' '.join(splited))
        sentence = sentence[temp:]
    return ''.join(stk)


if __name__ == "__main__":
    sentence: str = input().strip()
    answer = reverse_str(sentence)
    print(answer)
