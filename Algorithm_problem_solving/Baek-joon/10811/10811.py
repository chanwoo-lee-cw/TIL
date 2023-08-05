from sys import stdin

input = stdin.readline


class TurningBasket:
    def __init__(self, n: int):
        """
        생성자
        :param n : 바구니의 갯수
        """
        self.basket = [i for i in range(n + 1)]


    def reverse_basket(self, start: int, end: int) -> None:
        """
        start에서 end번째 바구니 갯수를 뒤집는다.
        """
        for_reverse = self.basket[start:end + 1]
        for_reverse.reverse()
        self.basket = self.basket[:start] + for_reverse + self.basket[end + 1:]


    def print_basket(self) -> None:
        """
        현재 바구니의 상태를 출력한다.
        """
        print(' '.join(str(item) for item in self.basket[1:]))


if __name__ == "__main__":
    # n : 바구니의 갯수
    # m : 바구니의 순서를 역순으로 만들 횟수
    n, m = map(int, input().strip().split())

    turning_basket = TurningBasket(n)
    for _ in range(m):
        i, j = map(int, input().strip().split())
        turning_basket.reverse_basket(i, j)

    turning_basket.print_basket()
