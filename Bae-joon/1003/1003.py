import sys
input = sys.stdin.readline

if __name__=='__main__' :
    n = int(input().strip())
    inputlist = []
    for i in range(n) :
        inputlist.append(int(input().strip()))

    dp = {}
    dp[0] = [1,0]
    dp[1] = [0,1]

    maxnum = max(inputlist)

    for i in range(2,maxnum + 1) :
        num1 = dp[i-1]
        num2 = dp[i-2]
        dp[i] = [num1[0]+num2[0],num1[1]+num2[1]]

    for i in inputlist :
        resutlt = dp[i]
        print(str(resutlt[0])+ " " + str(resutlt[1]))