import sys
input = sys.stdin.readline

if __name__ == "__main__" :
    n = int(input().strip())

    que = [i for i in range(1,n+1,2)]

    while len(que) > 1 :
        temp = que[0:2]
        que= que[2:]
        que.append(temp[1])

    print(que[0])