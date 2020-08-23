import sys

input = sys.stdin.readline

def lower_bound(start, end, arr, find):
    while end - start > 0:
        mid = (end + start)//2
        if arr[mid] < find:
            start = mid+1
        else:
            end = mid
    return (end + start)//2 + 1

if __name__ == "__main__":
    N = int(input().strip())
    cards = list(map(int, input().strip().split()))
    cards.sort()
    M = int(input().strip())
    searchs = list(map(int, input().strip().split()))
    for i in range(M):
        result = lower_bound(0, N, cards, searchs[i]) - 1
        if result >= N or cards[result] != searchs[i]:
            print(0, end=' ')
        else:
            print(1, end=' ')
