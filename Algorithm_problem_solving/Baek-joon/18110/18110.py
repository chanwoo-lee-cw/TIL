def _round(num):
    if int(num) % 2 == 0:
        return round(num + 0.1)
    else:
        return round(num)


def level_evaluation(n: int, user_difficult: list):
    if n == 0:
        return 0
    elif n == 1:
        return user_difficult[0]
    # else
    user_difficult.sort()
    cutting = _round(n * 0.15)
    use_opinion = user_difficult[cutting: -cutting]

    return round(sum(use_opinion) / len(use_opinion))


def solution():
    n = int(input())
    user_difficult = []
    for _ in range(n):
        user_difficult.append(int(input()))
    print(level_evaluation(n, user_difficult))


if __name__ == "__main__":
    solution()
