# https://www.acmicpc.net/problem/16967
import sys

input = sys.stdin.readline


def arr_restore(h, w, x, y, arrB):
    """
    arrA와 arrA를 아래로 X칸, 오른쪽으로 Y칸 이동시켜 만들어진 배열 A를 반환한다.
    Args:
        h,w ([Int, Int]): 배열의 세로, 가로 길이
        x,y ([Int, Int]): arrB를 만들 때 움직인 길이
        arrB (list[][]): 만들어진 배열

    Returns:
        [list[][]]: 원본 배열 arrA
    """
    arrA = [[0] * w for _ in range(h)]  # 원래 배열을 저장할 배열
    
    for i in range(h):
        for j in range(w):
            arrA[i][j] = arrB[i][j]

    for i in range(h - x):
        for j in range(w - y):
            arrA[x + i][y + j] -= arrA[i][j]

    return arrA


if __name__ == "__main__":
    h, w, x, y = map(int, input().strip().split())
    arrB = []
    for _ in range(h + x):
        arrB.append(list(map(int, input().strip().split())))
    arrA = arr_restore(h, w, x, y, arrB)

    for i in range(h):
        print(' '.join(str(item) for item in arrA[i]))
