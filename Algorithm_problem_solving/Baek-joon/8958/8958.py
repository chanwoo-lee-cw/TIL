import sys

input = sys.stdin.readline


def count_score(testcase: str) -> int:
    answer = 0
    seq = 0
    for item in testcase:
        if item == 'O':
            seq += 1
            answer += seq
        else:
            seq = 0
    return answer


if __name__ == "__main__":
    testcase_num = int(input())
    for _ in range(testcase_num):
        testcase = input().strip()
        print(count_score(testcase))
