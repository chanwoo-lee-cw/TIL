import sys

N=int(sys.stdin.readline())

case=[0]*(N+1)
for i in range(1, N+1):
    j=1
    case[i]=i
    while j*j<=i:
        if case[i]>1+case[i-j*j]:
            case[i]=min(case[i], 1+case[i-j*j])
        j=j+1
print(case[N])