from sys import stdin

input = stdin.readline

"""
arr배열의 오큰수 배열을 리턴한다.
"""
def findFisrtGreater(n, arr):
    stk = []    # 오큰술ㄹ 저장할 배열
    answer = [-1] * n       # 각각 위치의 오큰수의 배열
    stk.append((arr[0], 0))
    for i in range(1, n):
        if (stk[-1][0]) >= arr[i]:
            stk.append((arr[i], i))
        else:
            while stk and stk[-1][0] < arr[i]:
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
