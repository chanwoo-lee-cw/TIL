from sys import stdin

input = stdin.readline


def cypherNumCase(cypher):
    cypherLen = len(cypher)
    dp = [0] * (cypherLen + 1)
    # basecase
    dp[0] = 1
    dp[1] = 1 if cypher[-1] != '0' else 0
    # 점화식
    for i in range(2, cypherLen + 1):
        dp[i] = dp[i - 1];
        if cypher[-i] == '0':
            dp[i] = 0
            if cypher[-(i - 1)] == '0':
                return 0
        if cypher[-i] == '1':
            dp[i] += dp[i - 2]
        elif cypher[-i] == '2' and int(cypher[-(i - 1)]) <= 6:
            dp[i] += dp[i - 2]
        dp[i] %= 1000000
    return dp[cypherLen]


if __name__ == "__main__":
    cypher = input().strip()
    print(cypherNumCase(cypher))
