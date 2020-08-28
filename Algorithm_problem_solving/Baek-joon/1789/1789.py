import sys

input = sys.stdin.readline

if __name__ == "__main__":
    N = int(input().strip())
    
    sum = 0
    i = 0
    while sum < N:
        i += 1
        sum += i

    if sum == N:
        print(i)
    else:
        print(i-1)