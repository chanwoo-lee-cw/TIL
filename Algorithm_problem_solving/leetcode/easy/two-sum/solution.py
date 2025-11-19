class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        num_len = len(nums)
        for i in range(num_len):
            for j in range(i+1, num_len):
                sum_num = nums[i] + nums[j]
                if sum_num == target:
                    return [i, j]
        return []
