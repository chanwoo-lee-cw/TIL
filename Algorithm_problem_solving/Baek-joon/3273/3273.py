from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    n: int = int(input())
    numList: list = list(map(int, input().strip().split()))
    x: int = int(input())

    """
    The number and location are stored in a dictionary to make the time complexity O(n).
    After that, traverse the list again to confirm that (x - num) is in the dictionary and that the position is greater than the position of num.
    """
    numDict: dict = {}
    answer: int = 0
    for idx, num in enumerate(numList):
        numDict[num] = idx

    for idx, num1 in enumerate(numList):
        num2 = x - num1
        if num2 in numDict and numDict[num2] > idx:
            answer += 1

    print(answer)

