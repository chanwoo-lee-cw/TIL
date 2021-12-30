from sys import stdin

input = stdin.readline


class Chessboard:
    def __init__(self, n, board):
        """
        (n - 1) - (b - a) 과 (a + b) 로 구분한다.
        """
        self.n = n      # 판의 크기
        self.board = board      # 판의 모양
        self.able_bishop = []   # 비숍이 놓일 수 있는 위치
        for i in range(n):
            for j in range(n):
                if board[i][j] == 1:
                    self.able_bishop.append((i, j))
        self.cross1 = [False] * (n * 2)     # (n - 1) - (b - a) : 왼쪽 위부터 오른쪽 아래로 내려가는 대각선 체크
        self.cross2 = [False] * (n * 2)     # (a + b) : 왼쪽 아래부터 오른쪽 위로 올라가는 대각선 체크

    def dfs(self, pos):
        """
        dfs로 비숍을 둘 수 있는 위치를 체크한다.

        Args:
            pos (int): 놓을 수 있는 비숍의 위치를 저장해둔 배열의 위치

        Returns:
            int: 둘 수 있는 모든 곳에 다 두었을 때 비숍의 갯수
        """
        if pos == len(self.able_bishop):
            # 현재 놓여있는 비숍의 수를 세서 반환
            return self.cross1.count(True)
        # else
        answer = 0      # 최대로 놓인 비숍의 수
        for i in range(pos, len(self.able_bishop)):
            a, b = self.able_bishop[i]
            temp1 = (self.n - 1) - (b - a)
            temp2 = a + b
            if self.cross1[temp1] or self.cross2[temp2]:
                continue
            self.cross1[temp1] = True
            self.cross2[temp2] = True
            answer = max(answer, self.dfs(i + 1))
            self.cross1[temp1] = False
            self.cross2[temp2] = False
        return answer


if __name__ == "__main__":
    n = int(input().strip())
    board = []
    for i in range(n):
        board.append(list(map(int, input().strip().split())))
    chess = Chessboard(n, board)
    print(chess.dfs(0))
