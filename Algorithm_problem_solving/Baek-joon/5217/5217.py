import sys

input = sys.stdin.readline

if __name__ == '__main__':
    pairDic = {}
    for i in range(1, 13):
        for j in range(i + 1, 13):
            sum = i + j
            if sum in pairDic:
                pairDic[sum].append((i, j))
            else:
                pairDic[sum] = [(i, j)]
    n = int(input())
    for _ in range(n):
        want = int(input())
        output = []
        print(f'Pairs for {want}: ', end='')
        if want in pairDic:
            for item in pairDic.get(want):
                output.append(f'{item[0]} {item[1]}')
        print(', '.join(output))
