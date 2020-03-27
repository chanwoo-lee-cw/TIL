import sys
input = sys.stdin.readline

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
    lis[i] = arr[i]
    i = i+1

    while i<n :
        if lis[-1]<arr[i] :
            lis.append(arr[i])
        else :
            ans = lower_bound(lis, 0,len(lis),arr[i])
            lis[ans - 1] = arr[i]
        i = i + 1

    print(len(lis))