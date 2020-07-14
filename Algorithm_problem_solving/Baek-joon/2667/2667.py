# https://www.acmicpc.net/problem/2667
import sys

input = sys.stdin.readline
N = int(input().strip())
# Village to be inputted
town = []
# Preset values for up, down, left, and right for search
way = ((-1, 0), (1, 0), (0, -1), (0, 1))
# output value
cntlist = []

# Explore the top, bottom, left and right by recursive. 
# If the current position is not 1, return 0
# else if it is 1, convert it to -1 to mean that it has been explored, and return 1 to count the number.
def dfs(y, x):
    if town[y][x] != 1:
        return 0
    else:
        town[y][x] = -1
    result = 1
    for ymove, xmove in way:
        xpos = x + xmove
        ypos = y + ymove
        if xpos < 0 or xpos >= N \
                or ypos < 0 or ypos >= N:
            continue
        result += dfs(ypos, xpos)
    return result

# Input
for _ in range(N):
    innum = input().strip()
    temp = [0]*N
    for i in range(N):
        temp[i] = int(innum[i])
    town.append(temp)
# Locate the dfs function if you are navigating N*N and come to the part with a value of 1.
# Parts within the same neighborhood have changed from dfs to -1 and are not searched again.
for i in range(N):
    for j in range(N):
        if town[i][j] == 1:
            result = dfs(i, j)
            cntlist.append(result)
# Output
cntlist.sort()
print(len(cntlist))
for cnt in cntlist:
    print(cnt)
