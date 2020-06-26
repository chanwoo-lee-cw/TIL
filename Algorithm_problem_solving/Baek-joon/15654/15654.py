import sys

input = sys.stdin.readline

n, m = map(int, input().strip().split())
numlist = list(map(int, input().strip().split()))
dfsvisited = [False]*n
outlist = []


def dfs():
    if len(outlist) == m:
        # print it, if the output reachedes the wants length
        for item in outlist:
            print(item, end=' ')
        print()
    else:
        for i in range(n):
            if(dfsvisited[i]):
                # 중복이 없는 순열이므로 한번 방문한데는 더이상 방문하지 않는다.
                # becase permutation dosen't have overlap, no more visits already visited
                continue
            dfsvisited[i] = True
            outlist.append(numlist[i])
            dfs()
            outlist.pop()
            dfsvisited[i] = False


if __name__ == "__main__":
    numlist.sort()
    dfs()
