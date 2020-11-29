import sys
input = sys.stdin.readline

if __name__ == "__main__":
    N = int(input().strip())
    k = int(input().strip())
    # temp = []
    # for i in range(1, N+1):
    #     for j in range(1, N+1):
    #         print(i*j, end=' ')
    #         temp.append(i*j)
    #     print()
    # print()
    # temp.sort()
    # print(temp[k-1])
    start = 1
    end = k
    result = 0
    while (end - start) >= 0:
        mid = (start + end)//2
        cnt = 0
        for i in range(1, N+1):
            cnt += min(mid//i, N)
        # print(start, end, cnt)
        if cnt >= k:
            result = mid
            end = mid - 1
        else:
            start = mid + 1
    print(result)
