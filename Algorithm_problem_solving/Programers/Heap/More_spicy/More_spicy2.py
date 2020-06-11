import heapq


def solution(scoville, K):
    answer = 0
    # Initailize the entered sequence with heap
    heapq.heapify(scoville)

    while True:
        spicy1 = heapq.heappop(scoville)
        # 만약 가장 낮은 값이 k를 초과 했다면 break
        # If a lowest value is exceeds k, break
        if spicy1 >= K:
            break
        # If the heap no longer has a value, return -1
        elif len(scoville) == 0:
            return -1
        spicy2 = heapq.heappop(scoville)

        mixspicy = spicy1 + (2 * spicy2)
        heapq.heappush(scoville, mixspicy)
        answer += 1

    return answer


if __name__ == "__main__":
    scoville = [1, 2, 3, 9, 10, 12]
    K = 7
    print(solution(scoville, K))
