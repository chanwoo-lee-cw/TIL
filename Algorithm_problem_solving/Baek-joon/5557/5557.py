from sys import stdin

input = stdin.readline


def get_makeable_fomular(n: int, num_list: list) -> int:
    dp = [[0] * 21 for _ in range(n + 1)]
    dp[0][num_list[0]] = 1
    for i in range(1, n - 1):
        for j in range(21):
            if dp[i - 1][j] > 0:
                tmp_num = j + num_list[i]
                if 0 <= tmp_num <= 20:
                    dp[i][tmp_num] += dp[i - 1][j]
                tmp_num = j - num_list[i]
                if 0 <= tmp_num <= 20:
                    dp[i][tmp_num] += dp[i - 1][j]
    return dp[n - 2][num_list[-1]]


if __name__ == "__main__":
    n = int(input().strip())
    num_list = list(map(int, input().strip().split()))
    answer = get_makeable_fomular(n, num_list)
    print(answer)
