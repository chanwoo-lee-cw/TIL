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