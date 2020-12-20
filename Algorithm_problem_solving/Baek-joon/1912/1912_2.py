#https://www.acmicpc.net/problem/1912
import sys

input = sys.stdin.readline
sys.setrecursionlimit(1000000000)

def sumstr(str) :
    if(len(str)==1) :
        return str[0]
    mid = int((0 + len(str))/2)
    leftsum = sumstr(str[0:mid])
    rightsum = sumstr(str[mid:len(str)])

    left = mid-1
    right = mid
    midsum = 0
    maxmum = -float('INF')

    while True :
        if left < 0 or right > len(str) :
            break;
        if str[left] > str[right] :
            midsum = midsum + str[left]
            maxmum = max(midsum,maxmum)
            left = left - 1
            if left < 0 :
                while right < len(str) :
                    midsum = midsum + str[right]
                    maxmum = max(midsum, maxmum)
                    right = right + 1
        else :
            midsum = midsum + str[right]
            maxmum = max(midsum, maxmum)
            right = right + 1
            if right >= len(str) :
                while left >= 0 :
                    midsum = midsum + str[left]
                    maxmum = max(midsum, maxmum)
                    left = left - 1

    return max(leftsum, rightsum, maxmum)

if __name__ =="__main__":
    n = int(input().strip())

    str = list(map(int,input().strip().split()))

    print(sumstr(str))

