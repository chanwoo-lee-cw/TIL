class Solution:
    def isLongPressedName(self, name: str, typed: str) -> bool:
        pre_char = name[0]
        count_char = 1
        type_pivot = 0
        for idx, item in enumerate(name[1:]):
            if item == pre_char:
                count_char += 1
            else:
                type_pivot = self.check_type_pivot(typed, type_pivot, pre_char, count_char)
                if type_pivot == -1:
                    return False
                count_char = 1
                pre_char = item
        type_pivot = self.check_type_pivot(typed, type_pivot, pre_char, count_char)
        if type_pivot == -1:
            return False
        if type_pivot != len(typed):
            return False
        return True

    def check_type_pivot(
            self,
            typed: str,
            type_pivot: int,
            pre_char: chr,
            count_char: int
    ):
        count_type = 0
        while type_pivot < len(typed):
            if typed[type_pivot] == pre_char:
                count_type += 1
            else:
                break
            type_pivot += 1
        if count_type < count_char:
            return -1
        return type_pivot