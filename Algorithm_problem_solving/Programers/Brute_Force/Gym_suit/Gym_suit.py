def solution(n, lost, reserve):
    # 위에서부터 순서대로 체육복이 없는 사람의 수, 각 사람이 가지고 있는 체육복의 수
    no_suit = 0
    gym_suit = [1] * (n+2)
    for i in reserve:
        gym_suit[i] += 1
    for i in lost:
        gym_suit[i] -= 1
    # 만약 체육복이 없다면 일단, 이미 검사한 사람이 체육복의 여분이 있다면 필린다.
    for i in range(1,n+1):
        if(gym_suit[i]==0):
            if(gym_suit[i-1]>1):
                gym_suit[i-1] -= 1
                gym_suit[i] = 1
            elif(gym_suit[i+1]>1):
                gym_suit[i+1] -= 1
                gym_suit[i] = 1
            else:
                no_suit+=1
    return n-no_suit

if __name__ == "__main__":
    n =	1
    lost = []
    reserve = []
    print(solution(n, lost, reserve))