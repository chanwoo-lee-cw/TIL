import sys
input = sys.stdin.readline

def soulutuon(start, end, left, right, flag, moving, potato):
    while start >= 0 and end < N:
        if not left and not right:
            print(f'{potato} -1')
            return
        if flag:
            if not right:
                flag = False
                moving += end - start
                continue
            end += 1
            moving += 1
            if end < N:
                if S[end] == 'P':
                    potato += 1
                elif S[end] == 'R':
                    right = False
                else:
                    continue
                flag = False
                moving += end - start
        else:
            if not left:
                flag = True
                moving += end - start
                continue
            start -= 1
            moving += 1
            if start >= 0:
                if S[start] == 'P':
                    potato += 1
                elif S[start] == 'R':
                    left = False
                else:
                    continue
                flag = True
                moving += end - start
    print(f'{potato} {moving}')


if __name__ == "__main__":
    N, Q = map(int, input().strip().split())
    arr = input().strip()
    S = []
    for item in arr:
        S.append(item)
    pos = [0]*Q
    for i in range(Q):
        pos[i] = int(input().strip())

    for item in pos:
        start = item-1
        end = item-1
        left = True
        right = True
        flag = True
        moving = 0
        potato = 0
        soulutuon(start, end, left, right, flag, moving, potato)