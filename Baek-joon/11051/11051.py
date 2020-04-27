import sys
# input 시간을 줄이기 위해 오버라이딩
input = sys.stdin.readline()
# 재귀의 한계를 풀어서 stackoverflow 에러 방지
sys.setrecursionlimit(1000000000)

# 배열을 필요한 만큼 선언하는 동시 0으로 초기화한다.
dp = [[0]*1001 for i in range(1001)]

def binomial_coefficient(n,k) :
    if dp[n][k] != 0 :
        # 이미 한번 계산한 값이라면 그냥 리턴
        return dp[n][k]
    elif k==n or k==0 :
        # 만약 n과 k가 같거나 k가 0이면 1 리턴
        return 1
    else :
        # 나머지의 경우에는 이항 계수 공식으로 빠르게 계산한다.
        dp[n][k] = (binomial_coefficient(n-1,k-1) + binomial_coefficient(n-1,k))%10007
        return dp[n][k]

if __name__ == "__main__":
    n,k = input.split()

    n= int(n)
    k =int (k)

    print(binomial_coefficient(n,k));