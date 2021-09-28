import sys

input = sys.stdin.readline


def fillBlank(pos):
    if pos >= len(blank):
        for i in range(nSqaure):
            print(' '.join(str(_) for _ in matrix[i]))
        return True
    # else
    curr = blank[pos]
    for i in range(1, nSqaure + 1):
        areaNum = curr[1] // 3 + curr[0] // 3 * 3
        if rowCheck[curr[0]][i] or colCheck[curr[1]][i] or areaCheck[areaNum][i]:
            continue
        matrix[curr[0]][curr[1]] = i
        rowCheck[curr[0]][i] = True
        colCheck[curr[1]][i] = True
        areaCheck[areaNum][i] = True
        if fillBlank(pos + 1):
            return True
        rowCheck[curr[0]][i] = False
        colCheck[curr[1]][i] = False
        areaCheck[areaNum][i] = False
    return False


n = 3
nSqaure = n ** 2
rowCheck = [[False] * (nSqaure + 1) for _ in range(nSqaure + 1)]
colCheck = [[False] * (nSqaure + 1) for _ in range(nSqaure + 1)]
areaCheck = [[False] * (nSqaure + 1) for _ in range(nSqaure + 1)]
matrix = [[0] * (nSqaure) for _ in range(nSqaure)]

blank = []
for i in range(nSqaure):
    inputLine = list(map(int, input().strip().split()))
    for j in range(nSqaure):
        matrix[i][j] = inputLine[j]
        if matrix[i][j] == 0:
            blank.append((i, j))
        else:
            rowCheck[i][matrix[i][j]] = True
            colCheck[j][matrix[i][j]] = True
            areaNum = j // 3 + i // 3 * 3
            areaCheck[areaNum][matrix[i][j]] = True
fillBlank(0)
