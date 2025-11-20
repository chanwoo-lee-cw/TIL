class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        duplicate_check_set = set()
        start = 0
        end = 0
        longest_string = [-1, -1]
        if not s:
            return 0
        for idx, item in enumerate(s):
            if item in duplicate_check_set:
                duplicate_check_set = set()
                duplicate_check_set.add(item)
                if end - start >= (longest_string[1] - longest_string[0]):
                    longest_string = [start, end]
                start = idx
                end = idx
            else:
                duplicate_check_set.add(item)
                end = idx
        if end - start >= (longest_string[1] - longest_string[0]):
            longest_string = [start, end]
        return longest_string[1] - longest_string[0] + 1