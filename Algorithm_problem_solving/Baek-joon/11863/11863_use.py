# https://www.acmicpc.net/problem/11866
import queue
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    N, K = map(int, input().strip().split())
    que = queue.Queue(N)
    output = "<"

    for i in range(N):
        que.put(i+1)

    while not que.empty():
        for i in range(K-1):
            re_in = que.get()
            que.put(re_in)
        output += str(que.get())+", "

    output = output[:-2]+">"

    print(output)
