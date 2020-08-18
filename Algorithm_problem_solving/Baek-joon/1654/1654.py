import sys

input = sys.stdin.readline

if __name__ == "__main__":
    K, N = map(int, input().strip().split())
    cables = [0]*K
    for i in range(K):
        cables[i] = int(input().strip())
    minimum = 0
    maximum = max(cables)
    while maximum - minimum >= 0:
        mid = (minimum + maximum)//2
        if mid == 0:
            break
        mkcable = 0
        for cable in cables:
            mkcable += cable//mid
            if mkcable > N:
                break
        if mkcable >= N:
            minimum = mid + 1
            if mid > maximum:
                maximum = mid
        else:
            maximum = mid - 1
    print(round(maximum))
