import sys

input = sys.stdin.readline

# 덱을 위한 노드 클래스
class Node:
    def __init__(self, item):
        self.item = item
        self.l_node = None
        self.r_node = None

# 덱 클래스
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


if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    wants = list(map(int, input().strip().split()))
    cnt = 0
    seqeunce = 0
    deque = Deque()

    # N까지의 값을 덱에 집어 넣는다.
    for i in range(1, N+1):
        deque.push_back(i)

    for want in wants:
        # 원하는 수가 덱에 앞쪽에 있는지 뒤쪽에 있는지 확인하는 변수
        pos = (want - 1 + seqeunce) % deque.cnt
        # 앞쪽이라면 앞부터 pop한다
        if pos < deque.cnt//2:
            while True:
                out = deque.pop_front()
                if out == want:
                    # deque.push_back(out)
                    break
                else:
                    seqeunce -= 1
                    deque.push_back(out)
                    cnt += 1
        # 뒤라면 뒤에서 팝한다.
        else:
           while True:
                out = deque.pop_back()
                if out == want:
                    # deque.push_front(out)
                    break
                else:
                    seqeunce += 1
                    deque.push_front(out)
                    cnt += 1

    print(cnt)
