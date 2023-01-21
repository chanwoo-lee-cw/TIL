from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    a, b, c = map(int, input().strip().split())

    suffix_len = len(str(c))
    curr, answers = a, [str(a)]
    i = 0
    while True:
        i += 1
        curr *= b
        answers.append(str(curr))
        if len(str(curr)) <= suffix_len:
            if curr == c:
                print(i)
                break
        else:
            temp = int(str(curr)[-suffix_len:])
            if temp == c:
                print(i)
                break
            if i != 1 and str(curr)[-len(answers[1]):] == answers[1]:
                print("NIKAD")
                break
