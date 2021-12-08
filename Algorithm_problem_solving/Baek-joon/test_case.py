import random

N = 100
numList = range(100, 200)
alphaList = []
for i in range(26):
    alphaList.append(chr(ord('A') + i))

# pickup = random.sample(alphaList,N)

# for item in pickup:
#     print(item)

print(N)
for _ in range(N):
    print(random.choice(alphaList))