import sys

input = sys.stdin.readline


def dfs(pos):
    if len(out) == 6:
        for item in out:
            print(item, end=' ')
        print()
    else:
        for i in range(pos, len(in_list)):
            out.append(in_list[i])
            dfs(i+1)
            out.pop()


if __name__ == "__main__":
    while True:
        in_list = list(map(int, input().strip().split()))
        if len(in_list) == 1 and in_list[0] == 0:
            break
        out = []
        in_list = list(set(in_list[1:]))
        in_list.sort()
        dfs(0)
        print()
