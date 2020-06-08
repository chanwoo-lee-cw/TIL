import sys

sys.setrecursionlimit(10000000)
input = sys.stdin.readline
print = sys.stdout.write

n, m = map(int, input().strip().split())

# List for storing output values
outputlist = []
# If it's place already visited, true, or false
dfsvisited = [False] * (n+1)


# Find by deepth-first search
def dfs():
    # If arrangement is as fully as m, print it out
    if(len(outputlist) == m):
        for i in outputlist:
            print(str(i) + " ")
        print("\n")
        return
    # Enter the first number and searching
    else:
        for i in range(1, n + 1):
            if(dfsvisited[i]):
                continue
            dfsvisited[i] = True
            outputlist.append(i)
            dfs()
            outputlist.pop()
            dfsvisited[i] = False


if __name__ == "__main__":
    dfs()
