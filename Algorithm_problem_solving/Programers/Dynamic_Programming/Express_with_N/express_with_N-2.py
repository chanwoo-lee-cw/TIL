def solution(N, number):
    N_set = [set() for _ in range(8)]

    for i in range(1,8+1):
        N_set[i-1].add(int(str(N)*i))

    for i in range(1, 8):
        for j in range(i):
            for op1 in N_set[j]:
                for op2 in N_set[i-j-1]:
                    N_set[i].add(op1 + op2)
                    N_set[i].add(op1 - op2)
                    N_set[i].add(op1 * op2)
                    if op2 != 0:
                        N_set[i].add(op1 // op2)

        if  number in N_set[i]:
            answer = i + 1
            break

    else:
        answer = -1

    return answer

if __name__ == "__main__":
    N = 5
    number = 12
    solution(N, number)
    pass
