import sys

input = sys.stdin.readline

if __name__ =="__main__":
    n = int(input().strip())

    dp_twostep = 1
    dp_onestep = 2

    for i in range(3,n+1) :
        dp = (dp_onestep + dp_twostep)%15746
        result = dp
        dp_twostep = dp_onestep
        dp_onestep = dp

    print(result)