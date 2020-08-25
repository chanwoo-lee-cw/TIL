import sys

input = sys.stdin.readline


def parametric(min_budget, max_budget, arr):
    while max_budget - min_budget >= 0:
        mid = (min_budget+max_budget)//2
        sum = 0
        for want in wants:
            sum += want if want <= mid else mid
            if sum > budget:
                break
        if sum > budget:
            max_budget = mid - 1
        else:
            result = mid
            min_budget = mid + 1
    return result


if __name__ == "__main__":
    N = int(input().strip())
    wants = list(map(int, input().strip().split()))
    budget = int(input().strip())
    wants.sort()

    print(parametric(0, wants[-1], wants))
