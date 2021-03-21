from sys import stdin

input = stdin.readline


class Combination:
    def __init__(self, n, m):
        self.n = n
        self.m = m
        self.dp = [[0] * (m+1) for _ in range(n+1)]

    def getNM(self, n, m):
        if(m == 0):
            return 1
        elif(n == m):
            return 1
        elif(self.dp[n][m] != 0):
            return self.dp[n][m]
        # else
        self.dp[n][m] = self.getNM(n - 1, m - 1) + self.getNM(n - 1, m)
        return self.dp[n][m]


if __name__ == "__main__":
    n, m = map(int, input().strip().split())
    combination = Combination(n, m)
    print(combination.getNM(n, m))
