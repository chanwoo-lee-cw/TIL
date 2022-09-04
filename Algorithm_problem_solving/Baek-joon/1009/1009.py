from sys import stdin

input = stdin.readline


def make_cycle():
    cycle = [[10]]
    for i in range(1, 10):
        num = i
        this_cycle = [num]
        while True:
            num = (num * i) % 10
            if num == i:
                cycle.append(this_cycle)
                break
            else:
                this_cycle.append(num)
    return cycle


if __name__ == "__main__":
    cycle = make_cycle()
    t = int(input().strip())
    for _ in range(t):
        a, b = map(int, input().strip().split())
        a %= 10
        b = b % len(cycle[a])
        print(cycle[a][b - 1])
