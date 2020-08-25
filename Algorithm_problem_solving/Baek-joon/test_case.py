import random

N = 10
pickup = random.sample(range(100, 200),10)

for item in pickup:
    print(item, end = ' ')
