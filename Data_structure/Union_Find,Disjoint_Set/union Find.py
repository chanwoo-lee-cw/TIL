# https://www.crocus.co.kr/684
# https://www.acmicpc.net/problem/1717
import sys

input = sys.stdin.readline
print = sys.stdout.write

def find(u):
# 입력된 u의 root 부모가 누구인지 찾아내는 과정
    if (u == parent[u]):
        return u;
    parent[u] = find(parent[u]);
    return parent[u];


def merge(u, v):
# u,v를 하나로 묶는다.
    u = find(u)
    v = find(v)
	
    if (u == v):
	# 루트가 같은지 확인한다. 같다면 그냥 반환
        return
		
    if (level[u] > level[v]) :
	# 루트의 길이를 비교해서 더 짧은 쪽으로 연 연결한다.
        u, v = v, u

    parent[u] = v

    if(level[u]==level[v]) :
	# 트리길이가 늘어난거 늘려준다.
        level[v] = level[v] +1

if __name__=='__main__':
    n, m = map(int,input().split())

	# 부모가 누구인지, 그리고 트리 깊이가 어느 정도 일지 저장하는 배열
    parent = [i for i in range(n + 1)]
    level = [1] * (n + 1)

    for i in range(m):
        line = list(map(int,input().split()))
        if (line[0] == 0):
            merge(line[1], line[2], level)
        elif (line[0] == 1):
            u = find(line[1])
            v = find(line[2])
            if (u == v):
                print("yes\n")
            else:
                print("no\n")