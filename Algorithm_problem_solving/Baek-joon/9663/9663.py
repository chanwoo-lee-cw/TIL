n = int(input())

board = [[False]*n for _ in range(n)]

# def dfs(x, y):
#     if x == n:
#         return 0
#     num = dfs(x+1, y)
#     currnum = 0
#     for i in range(n):
#         for j in range(n):
#             if j == x or i == y:
#                 continue
#             elif abs(j-x) == abs(i-y):
#                 continue
#             else:
#                 currnum+=1

#     return currnum

def dfs(queen):
    if queen == 0:
        return 1
    sumnum = 0
    flag = False
    for i in range(n):
        if True in board[i]:
            continue
        for j in range(n):
            for a in range(n):
                if True == board[a][j]:
                    flag = True
                    break
            for a in range(-8,8):
                if board[i+a][j+a]:
                    flag = True
                    break
            if flag:
                flag = False
                continue
            board[i][j] = True
            sumnum += dfs(queen-1)
            board[i][j] = False
    return sumnum


if __name__ == "__main__":
    print(dfs(n))