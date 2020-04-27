import math

n = int(input())

def eratosthenes(n) :
    isPrime = [True for i in range(n + 1)]
    isPrime[0] = False
    isPrime[1] = False
    sqrtn = int(math.sqrt(n))
    for i in range(2,sqrtn+1) :
        if(isPrime[i]) :
            for j in range(i*i,n+1,i) :
                isPrime[j] = False
    return isPrime

def allRootnumalar(n,orgin,inspection) :
    sum = 0
    while (n>0) :
        root = n%10
        n = int(n/10)
        sum += int(math.pow(root,2))
    # print(sum)
    if (sum==1) :
        return True
    elif (sum==4 and inspection==False) :
        inspection=True
        return allRootnumalar(sum,orgin,inspection)
    elif (sum==4 and inspection==True) :
        return False
    else :
        return allRootnumalar(sum,orgin,inspection)

isPrime = eratosthenes(n)

# print(allRootnumalar(3,3,False))

for i in range(1,n+1) :
    if(isPrime[i]) :
        if (allRootnumalar(i,i,False)):
            print(i)