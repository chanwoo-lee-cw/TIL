# https://www.acmicpc.net/problem/11866
import sys

input = sys.stdin.readline


class Queue:
    def __init__(self, n):
        self.head = -1
        self.fulsize = n
        self.currsize = n
        self.arr = [i+1 for i in range(n+1)]
        self.rear = n-1

    def put(self, num):
        self.rear = (self.rear + 1) % self.fulsize
        self.arr[self.rear] = num

    def get(self):
        self.head = (self.head + 1) % self.fulsize
        return self.arr[self.head]

    def empty(self):
        return True if self.head == self.rear else False


if __name__ == "__main__":
    N, K = map(int, input().strip().split())
    que = Queue(N)
    output = "<"

    for i in range(N):
        que.put(i+1)

    while not que.empty():
        for i in range(K-1):
            re_in = que.get()
            que.put(re_in)
        output += f"{que.get()}, "

    output = f"{output[:-2]}>"

    print(output)
