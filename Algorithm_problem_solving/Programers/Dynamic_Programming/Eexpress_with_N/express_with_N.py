import sys
sys.setrecursionlimit(1000000000)

def memozation(dp, pos, N, number):
    fomular = []
    posN = N
    cnt = 1
    while posN < number*10:
        fomular.append([pos + posN, cnt])
        fomular.append([pos - posN, cnt])
        fomular.append([pos * posN, cnt])
        fomular.append([pos // posN, cnt])
        posN = posN*10 + N
        cnt += 1
    for item, cnt in fomular:
        if 0 < item and item < number*N+1:
            if dp[item] > dp[pos]:
                dp[item] = dp[pos] + cnt
                memozation(dp, item, N, number)


def solution(N, number):
    dp = [float('inf')]*(number*N+1)
    posN = N
    cnt = 1
    while posN < number*10:
        dp[posN] = cnt
        memozation(dp, posN, N, number)
        posN = posN*10 + N
        cnt += 1
    
    return dp[number] if dp[number] < 8 else -1


if __name__ == "__main__":
    N = 2
    number = 11
    print(solution(N, number))
    pass
