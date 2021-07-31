import sys

input = sys.stdin.readline


def findShortestPartialSum(n, s, arr):
    sum = [0] * (n + 1)
    minLen = n
    for i in range(1, n + 1):
        sum[i] = sum[i - 1] + arr[i - 1]
    if sum[n] - sum[0] < s:
        minLen = 0
    else:
        for i in range(1, n + 1):
            for j in range(1, minLen + 1):
                if i - j < 0:
                    break
                elif sum[i] - sum[i - j] >= s and minLen > j:
                    minLen = j
                    break
    return minLen


if __name__ == "__main__":
    N, S = map(int, input().strip().split())
    arr = list(map(int, input().strip().split()))

    print(findShortestPartialSum(N, S, arr))
