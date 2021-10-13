import sys

input = sys.stdin.readline

if __name__ == '__main__':
    nameSet = set()
    n, m = map(int, input().strip().split())
    output = []
    for _ in range(n):
        nameSet.add(input().strip())
    for _ in range(m):
        name = input().strip()
        if name in nameSet:
            output.append(name)
    output.sort()
    print(f'{len(output)}')
    print("\n".join(output))
