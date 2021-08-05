from sys import stdin

input = stdin.readline


def findShortestLen(n, c, houses):
    start = 1
    end = houses[-1] - houses[0]
    answer = end
    while start <= end:
        mid = (start + end) // 2
        cnt = 1
        pre = 0
        for i in range(1, n):
            if houses[i] - houses[pre] >= mid:
                cnt += 1
                pre = i
        if cnt >= c:
            answer = mid
            start = mid + 1
        else:
            end = mid - 1
    return answer


if __name__ == "__main__":
    N, C = map(int, input().strip().split())
    houses = [8] * N
    for i in range(N):
        houses[i] = int(input().strip())
    houses.sort()
    print(findShortestLen(N, C, houses))
