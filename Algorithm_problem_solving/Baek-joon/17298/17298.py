from sys import stdin

input = stdin.readline


def findFisrtGreater(n, arr):
    stk = []
    answer = [-1] * n
    stk.append((arr[0], 0))
    for i in range(1, n):
        if (stk[-1][0]) >= arr[i]:
            stk.append((arr[i], i))
        else:
            while stk and stk[-1][0] <= arr[i]:
                curr = stk.pop()
            stk.append((arr[i], i))
            for j in range(curr[1], i):
                if answer[j] != -1:
                    continue
                answer[j] = arr[i]
    return answer


if __name__ == "__main__":
    n = int(input().strip())
    arr = list(map(int, input().strip().split()))

    answer = findFisrtGreater(n, arr)
    for item in answer:
        print(item, end=' ')