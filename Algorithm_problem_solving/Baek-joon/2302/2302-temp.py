import sys

input = sys.stdin.readline


def solution(maxLen: int, area):
    answer = 1
    dp = [0] * (maxLen + 2)
    dp[1] = 1
    dp[2] = 2
    for i in range(3, maxLen + 1):
        dp[i] = dp[i - 1] + dp[i - 2]
    for item in area:
        answer *= dp[item]
    return answer


if __name__ == "__main__":
    n = int(input().strip())
    m = int(input().strip())
    area = []
    pre = 0
    for i in range(1, m + 1):
        curr = int(input().strip())
        area.append((curr - 1) - pre)
        pre = curr
    area.append(n - pre)
    print(solution(max(area), area))
