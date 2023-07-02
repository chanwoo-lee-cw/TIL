# https://www.acmicpc.net/problem/1644
import sys

input = sys.stdin.readline


def eratos(n: int):
    """
    에라토스 테네스의 체로 소수인 것에면 True체크를 해서 리턴한다.

    Args:
        n (int): 소수가 들어있는 최대의 범위 지정

    Returns:
        int: 소수가 표시된 배열을 반환한다.
    """
    seive = [True] * (n + 2)
    sqrtN = int(n ** 0.5)
    seive[0] = False
    seive[1] = False
    for i in range(2, sqrtN + 1):
        if seive[i]:
            for j in range(i * i, n + 1, i):
                seive[j] = False
    return seive


def serial_prime_sum(seive:list, n:int):
    """
    연속된 소수의 합으로 n이 되는 경우의 수를 반환한다.
    Args:
        seive (list): 에라토스테네스의 체로 걸러진 소수의 집합
        n (int): 원하는 수 n

    Returns:
        int: n이 되는 경우의 수
    """
    answer = 0      # 반환할 값
    numList = []    # 현재 연속된 수들의 집합
    num, numSum = 1, 0  # 현재 체크하고 있는 수, 현재 연속된 수들의 합
    while num <= n + 1:
        # 연속된 소수의 합이 n보다 커지면 앞에서 빼고, n보다 작아지면 뒤에서 붙힌다.
        if numSum == n:
            answer += 1
        if numSum >= n:
            numSum -= numList[0]
            numList.pop(0)
        else:
            while not seive[num]:
                num += 1
            numSum += num
            numList.append(num)
            num += 1
    return answer


if __name__ == "__main__":
    n = int(input().strip())
    seive = eratos(n)

    print(serial_prime_sum(seive, n))
