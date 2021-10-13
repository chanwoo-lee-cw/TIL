import sys

input = sys.stdin.readline

if __name__ == '__main__':
    arr = []
    for _ in range(9):
        arr.append(int(input()))

    maxPos = 1
    maxNum = arr[0]

    for idx, item in enumerate(arr):
        if maxNum < item:
            maxNum = item
            maxPos = idx + 1
    print(f'{maxNum}\n{maxPos}')
