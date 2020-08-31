import sys
input = sys.stdin.readline


class myQueue:
    def __init__(self, N):
        self.head = 0
        self.rear = 0
        self.maxSize = N
        self.queSize = 0
        self.arr = [0] * (self.maxSize+1)

    def is_full(self):
        return True if self.queSize==self.maxSize else False

    def is_empty(self):
        return True if self.queSize==0 else False

    def is_size(self):
        return self.queSize

    def push(self, item):
        if self.is_full():
            # print("is_full")
            return
        else:
            self.rear = (self.rear+1) % self.maxSize
            self.queSize += 1
            self.arr[self.rear] = item

    def pop(self):
        if self.is_empty():
            # print("is_empty")
            return -1
        else:
            self.head = (self.head+1) % self.maxSize
            self.queSize -= 1
            return self.arr[self.head]

    def front(self):
        if self.is_empty():
            return -1
        else:
            return self.arr[(self.head+1) % self.maxSize]

    def back(self):
        if self.is_empty():
            return -1
        else:
            return self.arr[(self.rear)]


if __name__ == "__main__":
    n = int(input().strip())
    que = myQueue(10001)
    for _ in range(n):
        command = input().strip().split()
        if command[0] == 'push':
            que.push(int(command[1]))
        elif command[0] == 'pop':
            print(que.pop())
        elif command[0] == 'size':
            print(que.is_size())
        elif command[0] == 'empty':
            if que.is_empty():
                print(1)
            else:
                print(0)
        elif command[0] == 'front':
            print(que.front())
        elif command[0] == 'back':
            print(que.back())
