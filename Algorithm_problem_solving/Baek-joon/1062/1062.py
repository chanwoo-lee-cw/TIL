from sys import stdin

input = stdin.readline


def getMaxRead(k, allAlphabetList, readableAlphabet, wordAlphabetSet, index):
    maxRead = 0
    if k == len(readableAlphabet):
        for i in range(n):
            flag = True
            for alphabet in wordAlphabetSet[i]:
                if alphabet not in readableAlphabet:
                    flag = False
                    break
            if flag:
                maxRead += 1
    else:
        for i in range(index, len(allAlphabetList)):
            readableAlphabet.add(allAlphabetList[i])
            maxRead = max(maxRead, getMaxRead(k, allAlphabetList, readableAlphabet, wordAlphabetSet, i + 1))
            readableAlphabet.remove(allAlphabetList[i])
    return maxRead


if __name__ == "__main__":
    n, k = map(int, input().strip().split())
    wordAlphabetSet = [set() for _ in range(n)]
    allAlphabetSet = set()

    for i in range(n):
        inputLine = input().strip()
        for alphabet in inputLine[4:-4]:
            if alphabet in ('a', 'n', 't', 'i', 'c'):
                continue
            # else
            wordAlphabetSet[i].add(alphabet)
            allAlphabetSet.add(alphabet)

    allAlphabetList = list(allAlphabetSet)
    readableAlphabet = set()
    if k < 5:
        print(0)
    elif (k - 5) >= len(allAlphabetList):
        print(n)
    else:
        print(getMaxRead(k - 5, allAlphabetList, readableAlphabet, wordAlphabetSet, 0))
