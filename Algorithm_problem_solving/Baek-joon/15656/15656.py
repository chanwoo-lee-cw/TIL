import sys
inpit = sys.stdin.readline

n, m = map(int, input().strip().split())
numlist = list(map(int, input().strip().split()))
output = []

def dfs(pos):
    # You can visit the place you visited again because it is allowed to overlap.
    if len(output) == m:
        for item in output:
            print(item, end=' ')
        print()
    else:
        for i in range(len(numlist)):
            output.append(numlist[i])
            dfs(i)
            output.pop()

if __name__ == "__main__":
    numlist.sort()
    dfs(0)