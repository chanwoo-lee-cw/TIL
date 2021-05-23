from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    a, b = map(int, input().strip().split())
    a_set = set()
    b_set = set()

    inputline = list(map(int, input().strip().split()))
    for item in inputline:
        a_set.add(item)
    inputline = list(map(int, input().strip().split()))
    for item in inputline:
        b_set.add(item)
    a_set_sub_cnt = len(a_set - b_set)
    b_set_sub_cnt = len(b_set - a_set)
    print(a_set_sub_cnt + b_set_sub_cnt)
