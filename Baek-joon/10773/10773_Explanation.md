## 백준 10773 풀이

https://www.acmicpc.net/problem/10773

### 문제

*나코더 기장 재민이는 동아리 회식을 준비하기 위해서 장부를 관리하는 중이다.*

*재현이는 재민이를 도와서 돈을 관리하는 중인데, 애석하게도 항상 정신없는 재현이는 돈을 실수로 잘못 부르는 사고를 치기 일쑤였다.*

*재현이는 잘못된 수를 부를 때마다 0을 외쳐서, 가장 최근에 재민이가 쓴 수를 지우게 시킨다.*

*재민이는 이렇게 모든 수를 받아 적은 후 그 수의 합을 알고 싶어 한다. 재민이를 도와주자!*

### 입력

*첫 번째 줄에 정수 K가 주어진다. (1 ≤ K ≤ 100,000)*

*이후 K개의 줄에 정수가 1개씩 주어진다. 정수는 0에서 1,000,000 사이의 값을 가지며, 정수가 "0" 일 경우에는 가장 최근에 쓴 수를 지우고, 아닐 경우 해당 수를 쓴다.*

*정수가 "0"일 경우에 지울 수 있는 수가 있음을 보장할 수 있다.*

### 출력

*재민이가 최종적으로 적어 낸 수의 합을 출력한다. 최종적으로 적어낸 수의 합은 231-1보다 작거나 같은 정수이다.*

***

### 풀이

스택을 구현하는 문제이다.

하지만, 항상 스택을 C나 Java로 짜보기만 했기 때문에 이번에는 새로운 시도도 해볼겸, Python의 class와 LinkedList구조를 학습해 볼겸 Python을 이용한 링크드 리스트 스택을 구현해 보았다.



코드 설명

```python
class Node:
    def __init__(self, item):
        self.item = item
        self.next = None
```

다음 노드를 가르킬 포인터와 가지고 있는 값을 저장할 item값을 초기화 한다.

파이썬은 클래스에 딱히 값을 클래스 내부로 인스턴스를 선언 해줄 필요 없이 인스턴스만 초기화하면 자동으로 인스턴스를 선언 시켜 준다.



```python
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
```

기본적인 링크드 리스트 기반 스택 구조이다.

평범한 언어라면 is_full도 선언해 줘야 하지만, 링크드 리스트 기반이므로 얼마든지 추가할 수 있으므로 굳이 선언하지 않았다.



파이썬의 클래스를 이용하는 도중에 독특한 것이 self였는데, 이게 파이썬의 클래스의 인스턴스를 전달해 주는 매개변수, 자바 같은 경우는 암묵적으로 선언되어 있어서 선언하지 않아도 되었던 것을 직접 선언했어야 했다.





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


