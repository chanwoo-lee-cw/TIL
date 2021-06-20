from sys import stdin

input = stdin.readline

"""
dfs로 가르칠 수 있는 k개의 알파벳을 모두 구한다.
만약 readableAlphabet에 k개의 알파벳이 채워지면 학생은 몇개의 단어를 읽을 수 있는지 확인하고, 그 최대의 수를 반환
"""
def getMaxRead(k, allAlphabetList, readableAlphabet, wordAlphabetSet, index):
    maxRead = 0     # 읽을 수 있는 단어의 수
    if k == len(readableAlphabet):
        for i in range(n):
            flag = True     # True읽을 수 있는 단어, False 읽을 수 없는 단어
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
    n, k = map(int, input().strip().split())        # n - 단어의 수, k- 가르칠 수 있는 알파벳 수
    wordAlphabetSet = [set() for _ in range(n)]     # 해당 단어에 쓰인 알파벳의 셋
    allAlphabetSet = set()      # 배울 수 있는 모든 알파벳 셋

    for i in range(n):
        inputLine = input().strip()
        for alphabet in inputLine[4:-4]:
            if alphabet in ('a', 'n', 't', 'i', 'c'):
                # 만약 필수적으로 배워야 하는 알파벳이면 제외
                continue
            # else
            wordAlphabetSet[i].add(alphabet)
            allAlphabetSet.add(alphabet)

    allAlphabetList = list(allAlphabetSet)
    readableAlphabet = set()
    if k < 5:
        # 필수적으로 배워야 하는 알파벳을 못 배우면 0
        print(0)
    elif (k - 5) >= len(allAlphabetList):
        # 사용된 모든 알파벳을 재울 수 있으면 전부 익힐 수 있다.
        print(n)
    else:
        print(getMaxRead(k - 5, allAlphabetList, readableAlphabet, wordAlphabetSet, 0))
