from sys import stdin

input = stdin.readline


def get_new_args(n, scores):
    """
    returns new average score obtained when all score are (socre/max_score * 100)
    
    :param n: size of scores
    :param scores: scores list
    :return: new average score
    """
    max_score = max(scores)
    new_sum = 0
    for item in scores:
        new_sum += (item / max_score) * 100

    return new_sum / n


if __name__ == '__main__':
    n = int(input())
    scores = list(map(int, input().strip().split()))

    print(get_new_args(n, scores))
