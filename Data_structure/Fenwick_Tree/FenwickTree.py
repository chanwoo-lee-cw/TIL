from typing import List


class FenwickTree:
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
        assert left <= right
        return self.__prefix_sum(right + 1) - self.__prefix_sum(left)

    def update(
            self,
            index: int,
            value: int
    ):
        assert index in range(0, self.size)
        self.__update_diff(index, value - self.arr[index])
        self.arr[index] = value

    def __lsb(self, idx) -> int:
        return idx & -idx

    def __build(self):
        for item in range(0, self.size):
            self.__update_diff(item, self.arr[item])

    def __update_diff(
            self,
            index: int,
            diff: int
    ) -> None:
        idx = index + 1
        while idx <= self.size:
            self.tree[idx] += diff
            idx += self.__lsb(idx)

    def __prefix_sum(
            self,
            index: int
    ) -> int:
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
