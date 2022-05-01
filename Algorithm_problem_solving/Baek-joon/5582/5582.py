from sys import stdin

input = stdin.readline


def lcs_lenght(str1: str, str2: str) -> int:
    str1_len = len(str1)
    str2_len = len(str2)
    dp = [[0] * (str2_len + 1) for _ in range(str1_len + 1)]
    answer = 0

    for i in range(1, str1_len + 1):
        for j in range(1, str2_len + 1):
            if str1[i - 1] == str2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
            answer = max(answer, dp[i][j])

    return answer


if __name__ == "__main__":
    str1 = input().strip()
    str2 = input().strip()

    print(lcs_lenght(str1, str2))
