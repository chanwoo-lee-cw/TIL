import sys

input = sys.stdin.readline


class Deque:
    def __init__(self, n):
        self.MAXSIZE = n
        self.arr = [0] * (self.MAXSIZE + 1)
        self.front = 0
        self.rear = 0

    def empty(self):
        return self.front == self.rear

    def push_front(self, item):
        self.front = (self.front-1+self.MAXSIZE) % self.MAXSIZE
        self.arr[self.front] = item

    def push_back(self, item):
        self.rear = (self.rear + 1) % self.MAXSIZE
        self.arr[self.rear] = item

    def pop_front(self):
        if self.empty():
            return -1
        ourput = self.front_peek()
        self.front = (self.front + 1) % self.MAXSIZE
        return ourput

    def pop_back(self):
        if self.empty():
            return -1
        ourput = self.back_peek()
        self.rear = (self.rear-1+self.MAXSIZE) % self.MAXSIZE
        return ourput

    def size(self):
        return (self.rear-self.front+self.MAXSIZE) % self.MAXSIZE

    def front_peek(self):
        if self.empty():
            return -1
        return self.arr[self.front]

    def back_peek(self):
        if self.empty():
            return -1
        return self.arr[self.rear]

if __name__ == "__main__":
    N = int(input().strip())
    deque = Deque(100001)

    for _ in range(N):
        command = input().strip().split()
        if command[0] == "push_front":
            deque.push_front(int(command[1]))
        elif command[0] == "push_back":
            deque.push_back(int(command[1]))
        elif command[0] == "pop_front":
            print(deque.pop_front())
        elif command[0] == "pop_back":
            print(deque.pop_back())
        elif command[0] == "size":
            print(deque.size())
        elif command[0] == "empty":
            if deque.empty():
                print("1")
            else:
                print("0")
        elif command[0] == "front":
            print(deque.front_peek())
        elif command[0] == "back":
            print(deque.back_peek())