# https://leetcode.com/problems/add-two-numbers/

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        answer = []
        pre_node = None
        first_node = None
        upper_number = 0
        while l1 or l2:
            sum_number = upper_number
            if l1:
                sum_number += l1.val
                l1 = l1.next
            if l2:
                sum_number += l2.val
                l2 = l2.next
            upper_number = sum_number // 10
            new_node = ListNode(sum_number % 10, None)
            if pre_node:
                pre_node.next = new_node
            else:
                first_node = new_node
            pre_node = new_node
        if upper_number:
            new_node = ListNode(upper_number, None)
            if pre_node:
                pre_node.next = new_node
        return first_node
