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
    n = int(input().strip())
    eratos = eratosthenes(n)

    for i in range(n + 1):
        if (eratos[i]):
            print(i)
            
#출처 : https://www.crocus.co.kr/805