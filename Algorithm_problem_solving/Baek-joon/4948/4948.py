# https://www.acmicpc.net/problem/4948
import sys

input = sys.stdin.readline

def eratosthenes(n) :
    eratos = [True] * (n + 1)
    eratos[0] = False
    eratos[1] = False

    sqrtn = int(n**0.5)

    for i in range(2,sqrtn) :
        if(eratos[i]) :
            for j in range(i**2,n+1,i) :
                eratos[j] = False

    return eratos

if __name__ == "__main__":
    inputlist = []
    while True:
        num = int(input().strip())
        if num == 0:
            break
        else:
            inputlist.append(num)

    eratos = eratosthenes(2*max(inputlist)+1)
    
    for item in inputlist:
        cnt = 0
        for i in range(item+1, 2*item +1):
            if eratos[i]:
                cnt = cnt + 1

        print(cnt)