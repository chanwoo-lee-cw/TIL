import sys
input = sys.stdin.readline

# 이진 트리의 특성
# 1. 모든 키는 유일하다.
# 2. 노드의 왼쪽 값은 현재 노드의 키보다 작다
# 3. 노드의 오른쪽 값은 현재 노드의 키보다 크다.
class Node :
    def __init__ (self, key) :
        self.key = key
        left = None
        right = None

# 완전 이진트리 클래스
def BinaryTree :
    def __init__(self) :
        root = None

    #완전 이진 트리의 삽입
    def insertNode (self, key) :
        if root == None :
            root = Node(key)
        
        else :
            parent = root
            child = root

            while child is not None :
                parent = child
                if key < child.key :
                    child = child.left
                else :
                    child = child.right
            
            if key < child
                parent.left = Node(key)
            else
                parent.right = Node(key)
