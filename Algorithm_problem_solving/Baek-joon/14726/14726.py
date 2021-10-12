import sys

input = sys.stdin.readline

if __name__ == '__main__':
    t = int(input())
    output = []
    for _ in range(t):
        oddSum = 0
        evenSum = 0
        cardNum = input().strip()
        for idx, item in enumerate(cardNum):
            item = int(item)
            if (idx) % 2:
                oddSum += item
            else:
                temp = item * 2
                evenSum += temp if temp < 10 else temp // 10 + temp % 10
        print("T" if (oddSum + evenSum) % 10 == 0 else "F")
