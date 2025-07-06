# https://www.acmicpc.net/problem/33889
import sys

input = sys.stdin.readline


def main():
    toilet_number, person_number = map(int, input().strip().split())
    dp = [[0] * (person_number + 1) for _ in range(toilet_number + 1)]
    # dp[toilet_number][person_number]
    for i in range(1, toilet_number + 1):
        dp[i][1] = i
    for i in range(2, toilet_number + 1):
        for j in range(2, person_number + 1):
            if person_number % 2 == 0:
                dp[i][j] = dp[i - 1][j] + dp[i - 2][j - 1]
            else:
                pass

    print(dp[toilet_number][person_number])


if __name__ == "__main__":
    main()

"""

2 2

o o

3 2

o x o

4 2

o x o x
o x x o
x o x o

3 2
2 1
의 합

5 2

o x o x x
o x x o x
x o x o x
o x x x o
x o x x o
x x o x o


쩍수 일때, 맨 가장 자리에 
넣을 때, 변기 -2, 사람 -1
넣는다, 변기 -1, 사람 유지

----

4 3
o x o o
o o x o

5 3
o x o x o

6 3
o o x o x o
o x o x o o
4 3
5 3

넣는다


7 5
o x o x o x o



1. 넣는다.


7 3
o x o x o x 

넣는다



"""
