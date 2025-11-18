# https://www.acmicpc.net/problem/14003
class LongestIncreasingSubsequence:

    @staticmethod
    def lower_bound(arr, find):
        s: int = 0
        e: int = len(arr)
        while s < e:
            m: int = (s + e) // 2
            if arr[m] < find:
                s = m + 1
            else:
                e = m
        return e

    def printLis(self, arr):
        lis = [arr[0]]
        insert_position = [0]
        for item in arr[1:]:
            if lis[-1] < item:
                insert_position.append(len(lis))
                lis.append(item)
            else:
                replace_pos = self.lower_bound(lis, item)
                lis[replace_pos] = item
                insert_position.append(replace_pos)
        lis_list = self.backtrackingLis(len(lis), arr, insert_position)

        print(len(lis))
        print(" ".join([f"{item}" for item in lis_list]))
        return len(lis)

    def backtrackingLis(self, lis_lennth, arr, insert_position):
        lis_list = [float("inf") for _ in range(lis_lennth)]
        for i in range(len(insert_position) - 1, -1, -1):
            tracking = insert_position[i]
            if lis_list[tracking] > arr[i]:
                lis_list[tracking] = arr[i]
        return lis_list


if __name__ == "__main__":
    n = int(input())
    arr = list(map(int, input().strip().split()))
    lis = LongestIncreasingSubsequence()
    lis.printLis(arr)

"""
6
10 20 10 30 20 50

7
10 20 15 20 30 20 50
"""
