from queue import PriorityQueue

def solution(scoville, K):
    answer = 0
    priorQue = PriorityQueue()

    for spicy in scoville:
        priorQue.put(spicy)

    while True:
        spicy1 = priorQue.get()
        if spicy1 >= K:
            break
        spicy2 = priorQue.get()

        mixspicy = spicy1 + (2 * spicy2)
        priorQue.put(mixspicy)
        answer += 1

    return answer


if __name__ == "__main__":
    scoville = [1, 2, 3, 9, 10, 12]
    K = 7
    print(solution(scoville, K))
