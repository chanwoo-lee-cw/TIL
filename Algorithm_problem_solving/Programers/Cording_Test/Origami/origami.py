# https://programmers.co.kr/learn/courses/30/lessons/62049
import sys

# 종이접기 : 가운데는 아래로 내려가 있고 그 가운데를 대칭으로 위, 아래를 바꾸면 된다.
def solution(n):
    # basecase
    answer = [0]
    # recurrence relation
    for i in range(1, n):
        # store the current length so as not to reflect the extension of length
        len_origami = len(answer)
        # Since the center is always 0, append 0.
        answer.append(0)
        # Lt starts from the center and goes up in reverse order and attachtes symmetrically
        for j in range(len_origami):
            answer.append(0 if answer[len_origami-1-j] == 1 else 1)

    return answer


if __name__ == "__main__":
    n = 3
    print(solution(n))
