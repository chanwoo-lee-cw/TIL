import sys
sys.setrecursionlimit(1000000000)


def memozation(dp, pos, N, number):
    fomular = []
    posN = N
    cnt = 1
    removelist = []
    while posN < number*N + 1:
        fomular.append([pos + posN, cnt])
        fomular.append([posN - pos, cnt])
        fomular.append([pos * posN, cnt])
        fomular.append([pos // posN, cnt])
        fomular.append([posN // pos, cnt])
        posN = posN*10 + N
        cnt += 1
    if(pos == 16):
        print()
    for item, cnt in fomular:
        if 0 < item and item < number*N + 1 and dp[item] > dp[pos]:
            dp[item] = dp[pos] + cnt
        else:
            removelist.append([item, cnt])
    for item in removelist:
        fomular.remove(item)
    for item, _ in fomular:
        if 0 < item and item < number*N + 1 :
            if dp[item] > 8:
                return
            # print(f'{item} : {dp[item]}')
            memozation(dp, item, N, number)


def solution(N, number):
    dp = [float('inf')]*(number*N+1)
    posN = N
    cnt = 1
    while posN < number*N + 1:
        dp[posN] = cnt
        memozation(dp, posN, N, number)
        posN = posN*10 + N
        cnt += 1

    return dp[number] if dp[number] < 8 else -1


if __name__ == "__main__":
    N = 4
    number = 17
    print(solution(N, number))
