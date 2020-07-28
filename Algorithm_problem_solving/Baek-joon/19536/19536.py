import sys
input = sys.stdin.readline
print = sys.stdout.write

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
    r_rock = upper_bound(R, 0, Rnum, end)
    r_pot = upper_bound(P, 0, Pnum, end)
    l_rock = lower_bound(R, 0, Rnum, start)
    l_pot = lower_bound(P, 0, Pnum, start)
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
            print(f'{potato} -1\n')
            return
        # 오른쪽으로 이동할때
        if flag:
            if not right:
                flag = False
                moving += end - start
                continue
            # 오른쪽에서 가장 먼저 만나는게 돌이냐 감자냐 찾는다
            rock = R[r_rock]
            pot = P[r_pot]
            if rock <= pot:
                right = False
                end = rock
            else:
                potato += 1
                end = pot
                r_pot += 1
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
            rock = R[l_rock]
            pot = P[l_pot]
            if rock >= pot:
                left = False
                start = rock
            else:
                potato += 1
                start = pot
                l_pot -= 1
            # 방향을 바꾸고 움직인 거리만큼 추가한다.
            flag = True
            moving += end - start
    # 모든 길에서 빠져나왔을때 뽑은 감자의 수를 출력한다.
    print(f'{potato} {moving}\n')


if __name__ == "__main__":
    N, Q = map(int, input().strip().split())
    arr = input().strip()
    S = ['']*(N+1)
    # 감자가 있는 위치와 돌이 있는 위치를 저장하는 배열
    Rnum = 0
    Pnum = 0
    R = [0]*N
    P = [0]*N
    for i in range(N):
        S[i+1] = arr[i]
        if arr[i] == 'P':
            P[Pnum+1] = i+1
            Pnum += 1
        elif arr[i] == 'R':
            R[Rnum+1] = i+1
            Rnum += 1
    P[Pnum+1] = N+1
    R[Rnum+1] = N+1
    Pnum += 1
    Rnum += 1

    for i in range(Q):
        pos = int(input().strip())
        solution(pos)