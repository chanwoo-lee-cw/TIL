import sys

input = sys.stdin.readline


class Node:
    def __init__(self, item):
        self.item = item
        self.next = None


class LinkedStack:
    def __init__(self):
        self.root = Node(None)
        self.top = -1

    def is_empty(self):
        return True if self.root is None else False

    def size(self):
        return self.top + 1

    def push(self, item):
        if self.top == -1:
            self.root = Node(item)

        else:
            temp = Node(item)
            temp.next = self.root
            self.root = temp
        self.top += 1

    def peek(self, item):
        if self.top == -1:
            print("빈 스택입니다.")
            return
        else:
            print(self.root.item)

    def pop(self):
        if self.top == -1:
            return "빈 스택입니다."
        else:
            num = self.root.item
            self.root = self.root.next
            self.top -= 1
            return num

    def print(self):
        current = self.root
        sum = 0
        while current is not None:
            sum += current.item
            current = current.next
        return sum


if __name__ == "__main__":
    stack = LinkedStack()

    k = int(input().strip())

    for _ in range(k) :
        num = int(input().strip())
        if num == 0 :
            stack.pop()
        else :
            stack.push(num)

    print(stack.print())
