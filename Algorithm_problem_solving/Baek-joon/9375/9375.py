import sys

input = sys.stdin.readline

if __name__ == "__main__":
    T = int(input().strip())

    for _ in range(T):
        n = int(input().strip())
        wear = {}
        for _ in range(n):
            waer_name, wear_type = input().strip().split()
            if wear_type in wear:
                wear[wear_type] = wear[wear_type] + 1
            else:
                wear[wear_type] = 1
        result = 1
        for cnt in wear.values():
            result *= cnt+1
        print(result-1)
