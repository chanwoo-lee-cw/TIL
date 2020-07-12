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