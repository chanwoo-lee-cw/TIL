# https://www.acmicpc.net/problem/2470
from sys import stdin

input = stdin.readline

"""
배열안에 있는 두 수의 쌍을 골랐을 때, 가장 0에 가까운 수의 쌍을 반환한다.
"""
def find_zero_nearest(n, arr):
    arr.sort()
    left, right = 0, n - 1      # 두 수를 선택할 포인터 2개를 설정
    answer = left, right        # 가장 작은 수의 쌍을 반환할 결과값
    pre = arr[0] + arr[n - 1]   # 지금까지 선택한 두 수의 쌍을 더했을 때 가장 0에 가까운 수
    while left < right:
        curr = arr[left] + arr[right]
        if abs(curr) < abs(pre):
            answer = left, right
            pre = curr
        if curr < 0:
            left += 1
        else:
            right -= 1
    return answer


if __name__ == "__main__":
    n = int(input().strip())
    arr = list(map(int, input().strip().split()))
    
    answer = find_zero_nearest(n, arr)
    
    print(f'{arr[answer[0]]} {arr[answer[1]]}')
