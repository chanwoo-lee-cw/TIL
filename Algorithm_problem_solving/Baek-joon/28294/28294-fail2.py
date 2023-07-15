"""
x번 돌때마다 늘어나는 갯수


(N-1)*N^(a-1-x)*N(N-1)^x - N^(a-1-x)*N(N-1)^(x-a)
"""


MOD = 1000000007  # 10^9 + 7

def get_oneSide(side_length, square, i):
    if i == square:
        return 0
    sum_side = get_oneSide(side_length, square, i+1)
    sum_side += (side_length - 1) * side_length ^ (square - i - 1) * side_length(side_length - 1) ^ square - side_length ^ (i - 1 - square) * side_length(side_length - 1) ^ (square - i)
    return sum_side // MOD


def get_fractal_sum_side(side_length, square):
    sum_side = get_oneSide(side_length, square, 0)
    return sum_side


if __name__ == "__main__":
    side_length, square = map(int, input().strip().split())

    answer = get_fractal_sum_side(side_length, square)
    print(answer)