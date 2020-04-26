# https://www.acmicpc.net/problem/1351
# 무한 수열
import sys
input = sys.stdin.readline

# dp에 저장할 범위 매크로 지정
MAX = 500000

n,p,q = map(int,input().strip().split())

def memo(i) :
    # 첫 번째 조건 지정
    if(i==0) :
        return 1

    # 몫을 구한다
    pos1 = i // p
    pos2 = i // q

    # 각각 i/P인 경우와 i/Q인 경우 2가지의 경우를 각각 재귀로 계산
    if(pos1 <=MAX) :
        if(dp[pos1] != 0) :
            temp1 = dp[pos1]
        else :
            temp1 = memo(pos1)
    else :
        temp1 = memo(pos1)

    if (pos2 <= MAX):
        if(dp[pos2] != 0) :
            temp2 = dp[pos2]
        else :
            temp2 = memo(pos2)
    else :
        temp2 = memo(pos2)
    
    # 현재 구해진 경우가 MAX값 이내라면 DP에 저장하고, 그 의외의 경우에는 바로 리턴한다.
    if(i<=MAX) :
        dp[i] = temp1 + temp2
        return dp[i]
    else :
        return temp1 + temp2

if __name__ == "__main__" :
    dp = [0]*(MAX+1)
    print(memo(n))