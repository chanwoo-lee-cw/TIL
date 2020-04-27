import sys

input = sys.stdin.readline

# 이진 트리의 특성
# 1. 모든 키는 유일하다.
# 2. 노드의 왼쪽 값은 현재 노드의 키보다 작다
# 3. 노드의 오른쪽 값은 현재 노드의 키보다 크다.

class Node:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None

# 이진트리 클래스
class BinaryTree:
    def __init__(self):
        self.root = None

    # 이진 트리의 삽입
    def insertNode(self, key):
        parent = None
        child = self.root

        # 텀색을 먼저 수행
        while child is not None:
            # 만약에 키가 이미 존재 한다면 리턴
            if key == child.key:
                return
            parent = child
            child = child.left if key < child.key else child.right

        if parent is not None:
            if key < parent.key:
                parent.left = Node(key)
            else:
                parent.right = Node(key)
        # 루트 노드에 삽입하는 경우
        else:
            self.root = Node(key)

    # 이진 트리의 삭제
    def deleteNode(self, key):
        parent = None
        child = self.root

        # 일단 값을 탐색한다.
        while child is not None and child.key != key:
            parent = child
            child = child.left if key < child.key else child.right

        # 찾는 값이 존재하지 않는 경우
        if child is None:
            print("값이 존재하지 않습니다.")
            return

        # 만약 삭제하려는 노드가 단말노드인 경우
        if child.left is None and child.right is None:
            if parent is not None:
                # 삭제하려는 노드를 그냥 삭제해주면 된다.
                if parent.left == child:
                    parent.left = None
                else:
                    parent.right = None
            # 루트 노드를 삭제하는 경우
            else:
                self.root = None

        # 만약 삭제하려는 노드의 자식이 두개의 자식을 가지는 경우
        elif child.left is not None and child.right is not None:
            # 삭제하려는 노드의 자식 노드중 가장 근접한 값 선정
            # 우측으로 일단 한번 간다.
            subParent = child
            sub = child.right
            
            # 가장 근접한 값이 나올때 까지 왼쪽으로 타고 내려간다.
            while sub.left is not None:
                subParent = sub
                sub = sub.left
            if subParent.left == sub:
                subParent.left = sub.right
            else:
                subParent.right = sub.right
            
            # 삭제 되려는 노드의 값을 가장 인접한 값을 넣고
            # 대입된 값을 가지고 있는 노드를 삭제
            child.key = sub.key
            child = sub

        # 삭제하려는 노드의 자식 노드가 1개인 경우
        elif child.left is not None or child.right is not None:
            temp = child.left if child.left is not None else child.right

            # 탐색된 노드가 루트 노드가 아닌 경우
            if parent is not None:
                # 삭제 되는 노드의 자식 노드와 부모 노드를 연결해 준다.
                if parent.left == child:
                    parent.left = temp
                else:
                    parent.right = temp
            else:
                self.root = child

        del (child)


# 전위 순회로 모든 노드를 출력한다.
def Preorder(node):
    print(node.key)
    if node.left is not None:
        Preorder(node.left)
    if node.right is not None:
        Preorder(node.right)


if __name__ == "__main__":
    bt = BinaryTree()

    bt.insertNode(4)
    bt.insertNode(2)
    bt.insertNode(3)
    bt.insertNode(1)
    bt.insertNode(5)

    Preorder(bt.root)

    print("삭제")

    bt.deleteNode(3)

    Preorder(bt.root)
