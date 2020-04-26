import sys

input = sys.stdin.readline


# 왼쪽 노드와 우측 노드를 링크드리스트로 선언하고
# 값이 들어갈 item도 알려준다.
class Node:
    def __init__(self, item):
        self.item = item
        self.leftNode = None
        self.rightNode = None

    def linkleftNode(self, left):
        self.leftNode = left

    def linkrightNode(self, right):
        self.rightNode = right


# 전위 순회(재귀)
def Preorder(node):
    print(node.item, end="")

    if node.leftNode is not None:
        Preorder(node.leftNode)
    if node.rightNode is not None:
        Preorder(node.rightNode)


# 중위 순회(재귀)
def Inorder(node):
    if node.leftNode is not None:
        Inorder(node.leftNode)

    print(node.item, end="")

    if node.rightNode is not None:
        Inorder(node.rightNode)


# 후위 순회(재귀)
def Postorder(node):
    if node.leftNode is not None:
        Postorder(node.leftNode)
    if node.rightNode is not None:
        Postorder(node.rightNode)

    print(node.item, end="")


if __name__ == "__main__":
    n = int(input().strip())

    # 아스키 코드를 1씩 증가시면 알파벳이 하나씩 증가 하는 원리를 이용하여 A,B,C,등등이 순서대로 들어가 있는
    # 노드를 리스트로 만든다.
    NodeList = [Node(chr(ord('A') + i)) for i in range(n)]
    # 무엇이 루트 노드인지를 알아내기 위한 배열, 모든 노드가 True일때 혼자 False인 얘가 루트 노드
    isparent = [False] * n

    for i in range(n):
        root, left, right = input().strip().split()

        # . 이 아닌 얘들이 부모 노드가 있는 얘들이므로
        # 왼쪽과 오른쪽을 'A...B...C'-'A' 등으로 입력된 부모노드의 자식을 연결한다.
        if left != '.':
            NodeList[ord(root) - ord('A')].linkleftNode(NodeList[ord(left) - ord('A')])
            isparent[ord(left) - ord('A')] = True

        if right != '.':
            NodeList[ord(root) - ord('A')].linkrightNode(NodeList[ord(right) - ord('A')])
            isparent[ord(right) - ord('A')] = True

    Preorder(NodeList[isparent.index(False)])
    print("")
    Inorder(NodeList[isparent.index(False)])
    print("")
    Postorder(NodeList[isparent.index(False)])
