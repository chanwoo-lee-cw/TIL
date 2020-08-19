import sys
sys.setrecursionlimit(10**4)
input = sys.stdin.readline


def dfs(pos):
    global sum
    global max_sum
    for i in range(pos, n):
        if not output:
            output.append(arr[i])
            sum += arr[i]
            max_sum = max(max_sum, sum)
            dfs(i+1)
            sum -= arr[i]
            output.pop()
        elif output[-1] < arr[i]:
            output.append(arr[i])
            sum += arr[i]
            max_sum = max(max_sum, sum)
            dfs(i+1)
            sum -= arr[i]
            output.pop()


if __name__ == "__main__":
    n = int(input().strip())
    arr = list(map(int, input().strip().split()))
    output = []
    sum = 0
    max_sum = 0
    dfs(0)
    print(max_sum)
