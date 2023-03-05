# https://www.acmicpc.net/problem/9012
from sys import stdin

input = stdin.readline


def check_vps(for_vps_check: str) -> str:
    """check this String is VPS rule

    Args:
        for_vps_check (str): String you want to check

    Returns:
        str: answer as "YES" or "NO"
    """
    stack = 0
    for item in for_vps_check:
        if item == '(':
            stack += 1
        else:
            if stack > 0:
                stack -= 1
            else:
                return "NO"
    if stack > 0:
        return "NO"
    else:
        return "YES"


if __name__ == '__main__':
    t: int = int(input().strip())
    for _ in range(t):
        for_vps_check = input().strip()
        stack = []
        print(check_vps(for_vps_check))
