# https://leetcode.com/problems/two-sum

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        num_len = len(nums)
        dp = [[0] * num_len for _ in range(num_len)]
        for i in range(num_len):
            dp[i][i] = nums[i]
        for i in range(num_len):
            for j in range(i+1, num_len):
                dp[i][j] = dp[i][j - 1] + nums[j]
                if dp[i][j] == target:
                    return [i, j]
        return []
