from sys import stdin
from queue import Queue

input = stdin.readline


def getMinBtn(floor:int, start:int, goal:int, up:int, down:int) -> int:
    visited = [False] * (floor + 1)     # 각 층의 방문 여부
    que = Queue()
    visited[start] = True
    que.put((start, 0))
    while not que.empty():
        curr, cnt = que.get()   # 현재 층 수, 버튼을 누른 횟수 
        if curr == goal:
            return cnt
        if curr + up <= floor and not visited[curr + up]:
            que.put((curr + up, cnt + 1))
            visited[curr + up] = True
        if curr - down > 0 and not visited[curr - down]:
            que.put((curr - down, cnt + 1))
            visited[curr - down] = True
    return -1


if __name__ == "__main__":
    F, S, G, U, D = map(int, input().strip().split())
    answer = getMinBtn(F, S, G, U, D)
    print(answer if answer != -1 else "use the stairs")
