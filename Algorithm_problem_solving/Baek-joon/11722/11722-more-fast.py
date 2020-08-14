import sys

input = sys.stdin.readline

def lower_bound(start, end, arr, check):
    while end-start > 0:
        mid = (start+end)//2
        if arr[mid] < check:
            start = mid + 1
        else:
            end = mid
    return end + 1

def lis(arr):
    upper_lis = [1]
    lis = [-float('INF')]
    i=0
    lis[i] = arr[i]
    i = i+1
    while i<n :
        if lis[-1]<arr[i] :
            lis.append(arr[i])
        else :
            ans = lower_bound(0,len(lis),lis,arr[i])
            lis[ans - 1] = arr[i]
        i = i + 1
        upper_lis.append(len(lis))
    return upper_lis

if __name__ == "__main__":
    n = int(input().strip())
    arr = list(map(int,input().strip().split()))
    arr.reverse()
    lower_lis = lis(arr)
    print(max(lower_lis))