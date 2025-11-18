from queue import Queue

class Solution:
    def isOneBitCharacter(self, bits: List[int]) -> bool:
        queue = Queue()
        queue.put(bits)
        while not queue.empty():
            item = queue.get()
            if len(item) == 0:
                continue
            elif len(item) == 1 and item[0] == 0:
                return True
            # else
            if item[0] == 1:
                queue.put(item[2:])
            else:
                queue.put(item[1:])
        return False