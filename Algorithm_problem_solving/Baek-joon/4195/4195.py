# https://www.acmicpc.net/problem/4195
import sys

input = sys.stdin.readline


def find(u):
    if (u == parent[u]):
        return u
    parent[u] = find(parent[u])
    return parent[u]


def merge(u, v):
    u = find(u)
    v = find(v)
    if (u == v):
        return
    if (level[u] > level[v]):
        u, v = v, u
    parent[u] = v
    if(level[u] == level[v]):
        level[v] = level[v] + 1


if __name__ == "__main__":
    T = int(input().strip())
    for _ in range(T):
        F = int(input().strip())
        peoplenum = 0
        # 문자열 비교 대신 정수로 비교하기 위한 정수형 배열
        parent = [i for i in range(2*F)]
        level = [1] * (2*F)
        # 빠른 위치 검색을 위한 딕셔너리
        names = {}
        for _ in range(F):
            # 입력받은 문자열을 찾는다.
            first, second = input().strip().split()
            # 딕셔너리에 없다면 이름을 추가하고 번호를 부여한다.
            if first not in names:
                names[first] = peoplenum
                peoplenum = peoplenum + 1
            if second not in names:
                names[second] = peoplenum
                peoplenum = peoplenum + 1
            # 각각 딕셔버리로부터 번호를 받아온다.
            first = names.get(first)
            second = names.get(second)
            temp1 = parent[first]
            temp2 = parent[second]
            # 두 이름을 union find
            merge(first, second)
            # 안 있다면 찾아서 바꿔준다.
            if temp1 != parent[first]:
                while temp1 in parent:
                    find(parent.index(temp1))
                cnt = parent.count(temp2)
            else:
                while temp2 in parent:
                    find(parent.index(temp2))
                cnt = parent.count(temp1)
            
            print(cnt)
