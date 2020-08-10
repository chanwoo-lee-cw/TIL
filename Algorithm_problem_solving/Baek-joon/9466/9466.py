import sys
sys.setrecursionlimit(10**5+1)

input = sys.stdin.readline


def dfs(student):
    if visited[student]:
        if student in this_team:
            return this_team.index(student)
        else:
            return 0
    this_team.append(student)
    visited[student] = True
    return dfs(wants[student])


if __name__ == "__main__":
    T = int(input().strip())
    for _ in range(T):
        n = int(input().strip())
        wants = [0]
        teams = [False] * (n+1)
        visited = [False] * (n+1)
        wants = wants + list(map(int, input().strip().split()))

        for student in range(1, n+1):
            if not visited[student]:
                this_team = [0]
                result = dfs(student)
                if result > 0:
                    for member in this_team[result:]:
                        teams[member] = True

        print(teams[1:].count(False))
