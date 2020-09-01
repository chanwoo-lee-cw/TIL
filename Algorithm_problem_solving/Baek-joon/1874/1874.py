import sys

input = sys.stdin.readline


class Stack:
    def __init__(self, N):
        self.top = -1
        self.maxSize = N
        self.stack = [0]*self.maxSize

    def is_empty(self):
        return True if self.top == -1 else False

    def is_full(self):
        return True if self.top == (self.maxSize-1) else False

    def push(self, item):
        if self.is_full():
            return
        else:
            self.top += 1
            self.stack[self.top] = item

    def pop(self):
        if self.is_empty():
            return -1
        else:
            gets = self.stack[self.top]
            self.top -= 1
            return gets

    def peek(self):
        if self.is_empty():
            return -1
        else:
            return self.stack[self.top]


if __name__ == "__main__":
    n = int(input().strip())
    stack = Stack(100000)
    poped = Stack(100000)
    wants_arr = []
    actions = []
    for _ in range(n):
        wants_arr.append(int(input().strip()))
    j = 0
    for i in range(1, n+1):
        stack.push(i)
        actions.append('+')
        while not stack.is_empty() and stack.peek() == wants_arr[j]:
            stack.pop()
            actions.append('-')
            j += 1
    if stack.is_empty():
        for item in actions:
            print(item)
    else:
        print("NO")
