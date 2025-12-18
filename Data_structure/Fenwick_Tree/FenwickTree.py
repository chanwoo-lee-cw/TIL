from typing import List


class FenwickTree:
    """
    1-based FenwickTree
    """

    def __init__(
            self,
            arr: List[int]
    ):
        self.size = len(arr)
        self.arr = arr
        self.tree = [0] * (len(arr) + 1)
        self.__build()

    def range_sum(
            self,
            left: int,
            right: int
    ) -> int:
        """
        index left~right 까지의 합

        :param left: 범위의 시작 index
        :param right: 범위위 종료 index

        :return: left ~ right 까지의 합
        """
        assert left <= right
        return self.__prefix_sum(right + 1) - self.__prefix_sum(left)

    def update(
            self,
            index: int,
            value: int
    ):
        """
        배열의 index의 값을 value로 업데이트

        :param index: 업데이트할 인덱스
        :param value: 해당 배열의 값
        """
        assert index in range(0, self.size)
        self.__update_diff(index, value - self.arr[index])
        self.arr[index] = value

    def __lsb(self, idx) -> int:
        """
        :return: 해당 값의 가장 오른쪽 1비트
        """
        return idx & -idx

    def __build(self):
        """
        초기 값을 세팅
        """
        for item in range(0, self.size):
            self.__update_diff(item, self.arr[item])

    def __update_diff(
            self,
            index: int,
            diff: int
    ) -> None:
        """
        해당 index의 구간 합을 가진 index들을 업데이트
        :param index: 현재 업데이트 할
        :param diff: 업데이트 될 값의 차이
        """
        idx = index + 1
        while idx <= self.size:
            self.tree[idx] += diff
            idx += self.__lsb(idx)

    def __prefix_sum(
            self,
            index: int
    ) -> int:
        """
        해당 index까지의 LSB를 이용해 구간 합을 구한다.
        """
        prefix_sum = 0
        idx = index
        while idx > 0:
            prefix_sum += self.tree[idx]
            idx -= self.__lsb(idx)
        return prefix_sum


if __name__ == "__main__":
    fenwick_tree = FenwickTree(
        [1, 2, 3, 4, 5]
    )
    print(
        fenwick_tree.range_sum(0, 4)
    )
