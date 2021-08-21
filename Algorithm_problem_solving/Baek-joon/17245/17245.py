# https://www.acmicpc.net/problem/17245
from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    N = int(input().strip())  # 서버실의 크기
    arr = []  # arr[i][j] : i,j 위치에 있는 컴퓨터의 크기
    answer = 0  # 답

    allSum = 0  # 모든 컴퓨터의 합
    high = 0  # 가장 높은 컴퓨터의 위치
    low = 0  # 가장 낮은 컴퓨터의 위치
    for i in range(N):
        arr.append(list(map(int, input().strip().split())))
        for item in arr[i]:
            high = max(high, item)
            allSum += item
    needOn = allSum / 2  # 켜져야 하는 컴퓨터의 수
    while low <= high:
        mid = (low + high) // 2
        currOn = 0
        for i in range(N):
            for j in range(N):
                currOn += min(mid, arr[i][j])
        if currOn >= needOn:
            high = mid - 1
            answer = mid
        else:
            low = mid + 1
    print(answer)
