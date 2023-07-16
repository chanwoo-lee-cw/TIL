MOD = 1000000007  # 10^9 + 7

def get_oneSide(side_length, square, i, mod):
    if square == 0:
        return (1 * side_length - 1) * pow(side_length - 1, i, mod) % mod
    else:
        sum_side = pow(side_length, square, mod) - pow(side_length, square - 1, mod) * i % mod
        sum_side = (sum_side + get_oneSide(side_length, square - 1, i + 1, mod)) % mod
        if i == -1:
            sum_side = (sum_side * side_length) % mod
        return sum_side


def get_fractal_sum_side(side_length, square, mod):
    sum_side = get_oneSide(side_length, square, -1, mod)
    return sum_side


if __name__ == "__main__":
    side_length, square = map(int, input().strip().split())
    
    answer = get_fractal_sum_side(side_length, square, mod)
    print(answer)