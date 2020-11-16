# https://www.acmicpc.net/problem/1003
import sys
input = sys.stdin.readline

if __name__=='__main__' :
    # 테스트 케이스를 받는 수
    n = int(input().strip())
    inputlist = []
    for i in range(n) :
        inputlist.append(int(input().strip()))

    # 빠른 속도를 위해 딕셔너리로 선언
    dp = {}
    # 피보나치 각각 0일 경우 0 1번, 1 0번
    # 1일 경우 0 0번, 1 1번을 세팅한다.
    dp[0] = [1,0]
    dp[1] = [0,1]

    maxnum = max(inputlist)
    # 피보나치의 n번째 숫자는 피보나치 배열의 n-1의 수와 n-2이므로
    # 즉, n-1, n-2의 0과 1의 가 호출되는 수와 같다.

    # 테스트 케이스 중에서 가장 큰 수만큼 미리 전부 계산한다.
    for i in range(2,maxnum + 1) :
        dp[i] = [dp[i-1][0]+dp[i-2][0],dp[i-1][1]+dp[i-2][1]]

    for i in inputlist :
        resutlt = dp[i]
        print(f"{resutlt[0]} {resutlt[1]}")