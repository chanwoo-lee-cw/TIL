class Node:
    def __init__(self, item):
        self.item = item
        self.l_node = None
        self.r_node = None

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
        while node:
            print(node.item)
            node = node.r_node
