import sys

input = sys.stdin.readline

# Retruns the number of points.
# param(correct answer, individual check way)
def abandonment(answers, check):
    lenAnswers = len(answers)
    select = 0

    for i in range(lenAnswers):
        if(answers[i] == check[i % len(check)]):
            select = select + 1

    return select


def solution(answers):
    answer = []

    first = (1, 2, 3, 4, 5)
    second = (2, 1, 2, 3, 2, 4, 2, 5)
    third = (3, 3, 1, 1, 2, 2, 4, 4, 5, 5)

    # Obtain each score
    score = []
    score.append(abandonment(answers, first))
    score.append(abandonment(answers, second))
    score.append(abandonment(answers, third))

    # Find out who's best
    maximum = max(score)
    for i in range(len(score)):
        if(score[i] == maximum):
            answer.append(i+1)

    return answer


if __name__ == "__main__":
    print(solution([1, 2, 3, 4, 5]))
