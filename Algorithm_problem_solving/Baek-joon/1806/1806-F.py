import sys
sys.setrecursionlimit(10**5 + 1)
input = sys.stdin.readline

# 가운데를 계산하는 함수
def calculate(start, end, mid):
    left = mid
    right = mid
    # 일단 가운데 두 부분을 합친다.
    sum = arr[right]
    # 만약 두 값의 합이 S보다 크다면 리턴
    if sum >= S:
        return right - left + 1
    # 수가 더 커지는 방향으로 찾는다.
    while left != start or right != end-1:
        if left == start:
            right += 1
            sum += arr[right]
        elif right == end-1:
            left -= 1
            sum += arr[left]
        else:
            if arr[left] > arr[right]:
                left -= 1
                sum += arr[left]
            else:
                right += 1
                sum += arr[right]
        # 만약 더 커졌다면 그 길이를 리턴
        if sum >= S:
            # print(sum, "     : ", arr[left:right+1])
            return right - left + 1
    return 0

# 분할 정복
# 왼쪽, 가운데, 오른쪽 에서 가장 작은 길이를 리턴
def divide(start, end):
    # 더이상 쪼개질 수 없을 때 단독 값을 계산하고 리턴
    if end - start == 1 or end - start == 0:
        if start < N and arr[start] >= S:
            return 1
        return 0
    mid = (start + end) // 2
    out = []
    left_result = divide(start, mid)
    right_result = divide(mid + 1, end)
    center = calculate(start, end, mid)
    # 3 분면에서 0이 아닌 수가 반환된 것들을 전부 out에 넣는다.
    if left_result != 0:
        out.append(left_result)
    if right_result != 0:
        out.append(right_result)
    if center != 0:
        out.append(center)
    # out 이 비어있다면 0 리턴, 아니면 가장 작은 값 리턴
    if not out:
        return 0
    else:
        return min(out)


if __name__ == "__main__":
    N, S = map(int, input().strip().split())
    arr = list(map(int, input().strip().split()))

    print(divide(0, N))
