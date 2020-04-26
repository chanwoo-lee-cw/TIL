import sys
input = sys.stdin.readline

# 가장 긴 증가하는 수열

# 찾고자 하는 값의 이상인 곳이 처음으로 나타나는 곳을 반환한다.
def lower_bound(arr, start, end,  check) :
    while end - start > 0 :
        mid = int((start + end) / 2)
        if arr[mid] < check :
            start = mid + 1
        else :
            end = mid
    return end+1

if __name__ == "__main__":
    n = int(input().strip())
    arr = list(map(int,input().strip().split()))
    lis = [-float('INF')]
    i=0
    # 일단 가장 긴 증가하는 순열을 찾기 위해 배열의 시작을 넣어준다.
    lis[i] = arr[i]
    i = i+1

    while i<n :
        # 만약 현재의 LIS 배열의 마지막 숫자(현재 LIS에서 가장 큰 수)가 더 작다면 그냥 뒤에 붙힌다.
        if lis[-1]<arr[i] :
            lis.append(arr[i])
        else :
            # 아니라면 그냥 지금 수가 현재의 수보다 가장 처음으로 커지는 곳에 대신 집어 넣는다.
            ans = lower_bound(lis, 0,len(lis),arr[i])
            lis[ans - 1] = arr[i]
        i = i + 1

    print(len(lis))

    # 주의점 LIS의 길이를 알 수 있을뿐 LIS 배열을 알고 싶을땐 이 방법이 아니다.