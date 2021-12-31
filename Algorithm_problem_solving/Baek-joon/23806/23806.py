from sys import stdin

input = stdin.readline


def draw_frame(n: int):
    """
    ㅁ 골뱅이 찍기를 하는 함수

    Args:
        n (int): 프레임의 크기

    Returns:
        list: 골뱅이 프레임이 그려진 배열을 반환하는 코드
    """
    arr = [['@'] * (n * 5) for _ in range(n * 5)]
    for i in range(n, 4 * n):
        for j in range(n, 4 * n):
            arr[i][j] = ' '
    return arr


if __name__ == "__main__":
    n = int(input().strip())
    arr = draw_frame(n)
    for item in arr:
        print(''.join(item))

