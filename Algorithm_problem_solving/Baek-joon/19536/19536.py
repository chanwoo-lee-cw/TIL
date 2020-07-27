import sys
input = sys.stdin.readline

# 왼쪽을 찾는 함수
def lower_bound(arr, start, end,  check):
    while end - start > 0:
        mid = (start + end) // 2
        if arr[mid] < check:
            start = mid + 1
        else:
            end = mid
    if end-1 < 0:
        return 0
    return end-1

# 오른쪽을 찾는 함수
def upper_bound(arr, start, end,  check):
    while end - start > 0:
        mid = (start + end) // 2
        if arr[mid] <= check:
            start = mid + 1
        else:
            end = mid
    return end

# 문제의 답을 찾는 함수
def solution(pos):
    # 초기 세팅
    start = end = pos
    # 왼쪽, 오른쪽으로 더 갈 수 있는지 확인하는 변수
    left = True
    right = True
    # 왼쪽으로 가느냐, 오른쪽으로 가느냐 찾는 함수
    flag = True
    # 움직인 거리
    moving = 0
    # 뽑은 감자의 수
    potato = 0
    # 범위 내에 있는 동안 반복한다.
    while 0 < start and end <= N:
        # 만약, 왼쪽 오른쪽 모두 돌이라면 return
        if not left and not right:
            print(f'{potato} -1')
            return
        # 오른쪽으로 이동할때
        if flag:
            if not right:
                flag = False
                moving += end - start
                continue
            # 오른쪽에서 가장 먼저 만나는게 돌이냐 감자냐 찾는다
            rock = R[upper_bound(R, 0, len(R), end)]
            pot = P[upper_bound(P, 0, len(P), end)]
            if rock <= pot:
                right = False
                end = rock
            else:
                potato += 1
                end = pot
            # 방향을 바꾸고 움직인 거리만큼 추가한다.
            flag = False
            moving += end - start
        # 왼쪽으로 움직일 때
        else:
            if not left:
                flag = True
                moving += end - start
                continue
            # 오른쪽에서 가장 먼저 만나는게 돌이냐 감자냐 찾는다
            rock = R[lower_bound(R, 0, len(R), start)]
            pot = P[lower_bound(P, 0, len(P), start)]
            if rock >= pot:
                left = False
                start = rock
            else:
                potato += 1
                start = pot
            # 방향을 바꾸고 움직인 거리만큼 추가한다.
            flag = True
            moving += end - start
    # 모든 길에서 빠져나왔을때 뽑은 감자의 수를 출력한다.
    print(f'{potato} {moving}')


if __name__ == "__main__":
    N, Q = map(int, input().strip().split())
    arr = input().strip()
    S = ['']*(N+1)
    # 감자가 있는 위치와 돌이 있는 위치를 저장하는 배열
    R = [0, ]
    P = [0, ]
    for i in range(N):
        S[i+1] = arr[i]
        if arr[i] == 'P':
            P.append(i+1)
        elif arr[i] == 'R':
            R.append(i+1)
    P.append(N+1)
    R.append(N+1)

    for i in range(Q):
        pos = int(input().strip())
        solution(pos)