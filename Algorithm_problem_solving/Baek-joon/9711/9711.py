from sys import stdin, setrecursionlimit

setrecursionlimit(20001)
input = stdin.readline


class Fibonacci:
    def __init__(self):
        self.arr = [0, 1]

    def get(self, n):
        if len(self.arr) >= n + 1:
            return self.arr[n]
        elif n <= 1:
            self.arr.append(1)
        else:
            self.arr.append(self.get(n - 1) + self.get(n - 2))
        return self.arr[n]


if __name__ == "__main__":
    fibonacci = Fibonacci()
    t = int(input().strip())
    output = []
    for idx in range(t):
        n, q = map(int, input().strip().split())
        output.append(f"Case #{idx + 1}: {fibonacci.get(n) % q}")
    print('\n'.join(output))
