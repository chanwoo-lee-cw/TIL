## 백준 11725 풀이

https://www.acmicpc.net/problem/11725

### 문제

*루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.*

### 입력

*첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.*

### 출력

*첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.*

***

### 풀이

트리의 부모를 찾는 문제이다.

일단 내 생각으로는 루트부터 하나하나 이어가는 거라고 생각랬다.

근데 그러면 안됬다.



첫번째 코드

```python
import sys
input = sys.stdin.readline

global result, check
result = None
check = False

class Node:
    def __init__(self, item):
        self.item = item
        self.next = []

    def add(self,item):
        temp = Node(item)
        self.next.append(temp)


def addSearch(curr, parent, child) :
    if(curr.item == parent) :
        curr.add(child)
    else:
        for i in range(len(curr.next)) :
            addSearch(curr.next[i], parent, child)

def searching(parent, child, item) :
    # print("어디냐 : ",parent.item, " ", child.item)
    global check, result
    if(child.item == item) :
        result = parent.item
        check = True
    parent = child
    for i in range(len(child.next)):
        if check : return
        searching(parent, child.next[i], item)


if __name__ == "__main__":
    n = int(input().strip())
    root = Node(1)
    connect = [1]
    for i in range(n-1) :
        parent, child = map(int,input().strip().split())
        if child in connect:
            temp = child
            child = parent
            parent = temp
        addSearch(root, parent, child)
        connect.append(child)


    for i in range(2,n+1) :
        searching(root,root,i)
        check = False
        print(result)
```

루트에서 부터 하나하나 이어가는 코드를 설계했다.



근데 그것이 아니라, 그냥 각 노드마다 이어달라고 하는대로 이어주고 양측을 모두 찾으면 되는 거였다.



```python
import sys
input = sys.stdin.readline

# 시간을 절약하기 위해 값을 찾는다면 재귀에서 리턴하기 위한 전역 변수
global check, result
check = False
result = None

node = [0]

class Node:
    def __init__(self, item):
        # 다음 연결과 현재의 값을 초기화
        self.item = item
        self.next = []

    def link(self,connect):
        # 입력받은 두개의 노드를 연결
        self.next.append(connect)


def searchNode(curr,parentItem,searching) :
    global check, result
    if curr.item == searching:
        # 만약 현재 노드가 찾는 값이였다면 부모의 값을 저장
        result = parentItem
        check = True
    for i in range(len(curr.next)) :
        # 아니라면 계속해서 깊이 우선 탐색으로 찾는다.
        if check : return
        if curr.next[i].item == parentItem : continue
        searchNode(curr.next[i],curr.item,searching)

if __name__ == "__main__":
    n = int(input().strip())
    
    # n개 만큼 노드를 생성한다.
    for i in range(n):
        node.append(Node(i+1))

    for i in range(n-1) :
        # 입력받은 두개의 노드를 연결
        first, second = map(int,input().strip().split())
        node[first].link(node[second])
        node[second].link(node[first])

    for i in range(2, n+1) :
        # 2번 부터 부모 노드를 찾기 시작한다.
        check = False
        searchNode(node[1], 1, i)
        print(result)
```

다시 시간 초과...





### 전체 코드

```python
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
```


