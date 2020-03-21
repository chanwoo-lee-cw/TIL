#https://www.acmicpc.net/problem/1912
import sys

input = sys.stdin.readline

def sumstr(str,start,end) :
    if(start == end) :
        return str[start]
    mid = int((start + end)/2)
    leftsum = sumstr(str, start, mid)
    rightsum = sumstr(str, mid+1, end)

    left = mid
    right = mid + 1
    midsum = 0
    maxmum = -float('INF')

    while True :
        if left < start or right > end :
            break;
        if str[left] > str[right] :
            midsum = midsum + str[left]
            maxmum = max(midsum,maxmum)
            left = left - 1
            if left < start :
                while right <= end :
                    midsum = midsum + str[right]
                    maxmum = max(midsum, maxmum)
                    right = right + 1
        else :
            midsum = midsum + str[right]
            maxmum = max(midsum, maxmum)
            right = right + 1
            if right > end :
                while left >= start :
                    midsum = midsum + str[left]
                    maxmum = max(midsum, maxmum)
                    left = left - 1

    return max(leftsum, rightsum, maxmum)

if __name__ =="__main__":
    n = int(input().strip())

    str = list(map(int,input().strip().split()))

    print(sumstr(str,0,len(str)-1))