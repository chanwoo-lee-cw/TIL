from queue import PriorityQueue

def solution(scoville, K):
    answer = 0
    # 우선 순위 큐를 선언
    priorQue = PriorityQueue()

    # 배열의 하나하나 우선 순위 큐에 넣는다.
    for spicy in scoville:
        priorQue.put(spicy)

    while True:
        # -1 은 자동으로 반환 되므로 고려하지 않아도 된다.
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
