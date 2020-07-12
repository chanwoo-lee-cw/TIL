# https://www.acmicpc.net/problem/1966
import sys

input = sys.stdin.readline

class Queue:
    def __init__(self, n):
        self.head = -1
        self.fulsize = n
        self.currsize = n
        self.arr = [i+1 for i in range(n)]
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
    T = int(input())
    for _ in range(T):
        n, m = map(int, input().strip().split())
        numlist = list(map(int, input().strip().split()))
        que = Queue(n)
        cnt = 0
        for i in range(n):
            if(i!=m):
                que.put([numlist[i],False])
            else:
                que.put([numlist[i],True])
        numlist.sort(reverse=True)
        while True:
            out = que.get()
            if out[0] != numlist[0]:
                que.put(out)
            else:
                numlist.pop(0)
                cnt+=1
                if out[1]:
                    break
        print(cnt)
        del que