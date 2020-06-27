import sys

input = sys.stdin.readline

n, m = map(int, input().strip().split())
numlist = list(map(int, input().strip().split()))
output = []


def dfs(pos):
    if len(output) == m:
        for item in output:
            print(item, end=' ')
        print()
    else:
        for i in range(pos, n):
            output.append(numlist[i])
            dfs(i)
            output.pop()


if __name__ == "__main__":
    numlist.sort()
    dfs(0)