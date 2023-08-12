from sys import stdin

input = stdin.readline


class SelectionSort:
    def __init__(self, n: int, arr: list):
        self.n = n
        self.arr = arr

    def solution(self, k: int) -> tuple:
        """
        선택 정렬을 실행했을 때, k번째로 교환되는 두 값을 리턴
        :param k: 구하고자하는 k번째 인덱스
        :return: k번째로 교환되는 두 값을, 작은 순서를 앞으로 출력, 만약 없다면 -1
        """
        exchage_cnt = 0
        for i in range(n - 1, 0, -1):
            pivot = i
            for j in range(0, i):
                if arr[pivot] < arr[j]:
                    pivot = j
            if pivot != i:
                arr[i], arr[pivot] = arr[pivot], arr[i]
                exchage_cnt += 1
                if exchage_cnt == k:
                    return (arr[i], arr[pivot]) if arr[i] < arr[pivot] else (arr[pivot], arr[i])
        return None


if __name__ == "__main__":
    n, k = map(int, input().strip().split())
    arr = list(map(int, input().strip().split()))

    sort = SelectionSort(n, arr)

    result = sort.solution(k)
    if result:
        print(f'{result[0]} {result[1]}')
    else:
        print(-1)
