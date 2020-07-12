# https://www.acmicpc.net/problem/5430
import sys

input = sys.stdin.readline


class Node:
    def __init__(self, item):
        self.item = item
        self.l_node = None
        self.r_node = None

# Declaire Deque
class Deque:
    def __init__(self):
        self.cnt = 0
        self.head = None
        self.tail = None

    def size(self):
        return self.cnt

    def empty(self):
        return True if self.cnt == 0 else False

    def front(self):
        if self.empty():
            return -1
        return self.head.item

    def back(self):
        if self.empty():
            return -1
        return self.tail.item

    def push_front(self, item):
        new_node = Node(item)
        if self.head:
            new_node.r_node = self.head
            self.head.l_node = new_node
        else:
            self.tail = new_node
        self.head = new_node
        self.cnt += 1

    def push_back(self, item):
        new_node = Node(item)
        if self.head:
            new_node.l_node = self.tail
            self.tail.r_node = new_node
        else:
            self.head = new_node
        self.tail = new_node
        self.cnt += 1

    def pop_front(self):
        if self.empty():
            return -1
        output = self.front()
        temp = self.head
        self.head = self.head.r_node
        if self.head:
            self.head.l_node = None
        else:
            self.tail = None
        del temp
        self.cnt -= 1
        return output

    def pop_back(self):
        if self.empty():
            return -1
        output = self.back()
        temp = self.tail
        self.tail = self.tail.l_node
        if self.tail:
            self.tail.r_node = None
        else:
            self.head = None
        del temp
        self.cnt -= 1
        return output

    def printall(self):
        node = self.head
        arr = "["
        while node:
            arr += f"{node.item},"
            node = node.r_node
        if len(arr) > 1:
            arr = arr[:-1]
        arr = arr+"]"
        return arr

    def reverse_printall(self):
        node = self.tail
        arr = "["
        while node:
            arr += f"{node.item},"
            node = node.l_node
        if len(arr) > 1:
            arr = arr[:-1]
        arr = arr+"]"
        return arr


if __name__ == "__main__":
    # in put testcase
    T = int(input().strip())
    for _ in range(T):
        # Flag to determine whether to pull from the back or from the front when D is entered
        flag = True
        # Receive a command.
        p = input().strip()
        n = int(input().strip())
        deque = Deque()
        # If the list of numbers is 0, Deque is blank, or the list entered into Deque is inserted.
        if n == 0:
            input()
            arr = []
        else:
            arr = list(map(int, input().strip().strip(
                "]").strip("[").split(",")))
        for elements in arr:
            deque.push_back(elements)
        
        try:
            for item in p:
                # If input is R, revert flag
                if item == 'R':
                    flag = False if flag else True
                else:
                    # Generate an error when Deque is empty.
                    if deque.empty():
                        raise
                    # Decide whether to back out or front out according to the flag.
                    if flag:
                        deque.pop_front()
                    else:
                        deque.pop_back()
            # Determine whether to print in reverse or regular order according to the flag.
            if flag:
                print(deque.printall())
            else:
                print(deque.reverse_printall())
        # If an error occurs, print the error and run the next test case.
        except:
            print("error")
