from sys import stdin

input = stdin.readline


# 다른 사람의 풀이
# 셋에 집착하지 않고, binarySearch로 있는지 확인
def binarySearch(arr, s, e, find):
    while s <= e:
        m = (s + e) // 2
        if arr[m] == find:
            return 1
        elif arr[m] < find:
            s = m + 1
        else:
            e = m - 1
    return 0


if __name__ == "__main__":
    answer = 0
    a_len, b_len = map(int, input().strip().split())
    a_list = list(map(int, input().strip().split()))
    b_list = list(map(int, input().strip().split()))

    a_list.sort()
    b_list.sort()

    cnt = 0
    for item in a_list:
        # print(f'{item} : {binarySearch(b_list, 0, b_len-1, item)}')
        cnt += binarySearch(b_list, 0, b_len - 1, item)
    answer += a_len - cnt
    cnt = 0
    for item in b_list:
        # print(f'{item} : {binarySearch(a_list, 0, a_len-1, item)}')
        cnt += binarySearch(a_list, 0, a_len - 1, item)
    answer += b_len - cnt
    print(answer)
