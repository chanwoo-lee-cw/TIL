from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    a, b, c = map(int, input().strip().split())

    suffix_len = len(str(c))
    curr, first = a, str(a * b)
    if b <= 1 or a <= 0:
        print("NIKAD")
    else:
        i = 0
        while True:
            i += 1
            curr *= b
            if len(str(curr)) <= suffix_len:
                if curr == c:
                    print(i)
                    break
            else:
                temp = int(str(curr)[-suffix_len:])
                if temp == c:
                    print(i)
                    break
                if i != 1 and str(curr)[-len(first):] == first:
                    print("NIKAD")
                    break
