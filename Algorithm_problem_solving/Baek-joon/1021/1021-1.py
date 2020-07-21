import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().strip().split())
want = list(map(int, input().strip().split()))
deq = deque(range(1, n+1))

answer = 0
for num in want:
    idx = deq.index(num)

    if idx <= len(deq) - idx:
        deq.rotate(-idx)
        answer += idx
    else:
        deq.rotate(len(deq) - idx)
        answer += (len(deq)-idx)
    if num == deq[0]:
        deq.popleft()

print(answer)