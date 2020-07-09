# https://www.acmicpc.net/problem/9663

# 참고 : https://www.crocus.co.kr/829
class Chess:
    def __init__(self, n):
        self.board_size = n
        # self.board = [-1]*(self.board_size+1)
        # 퀸의 갯수는 총 n개 각각 가로, 세로줄 마다 적어도 하나의 체스말을 있다.
        self.board = [False]*(self.board_size+1)
        # 대각선을 검사해줄 코드
        self.cross1 = [False]*(self.board_size*2)
        self.cross2 = [False]*(self.board_size*2)
        # 현재 놓여진 체스 말의 갯수
        self.queen = 0
        # 놓을 수 있는 경우의 수
        self.answer = 0

    def dfs(self, col):
        # 만약 체스말이 n개 라면, 즉, 세로로 더이상 갈 수 없는 경우
        if self.queen == n:
            self.answer += 1
            return
        else:
            # 가로로 놓을 수 있는 경우의 수를 센다.
            for row in range(1, n+1):
                # 대각선에 체스 말이 존재 한다면 다음으로 간다.
                if self.board[row] or self.cross1[col + row - 1] or self.cross2[self.board_size - row + 1 + col -1]:
                    continue
                self.cross1[col + row - 1] = True
                self.cross2[self.board_size - row + 1 + col -1] = True
                self.board[row] = True
                self.queen += 1

                self.dfs(col + 1)

                self.cross1[col + row - 1] = False
                self.cross2[self.board_size - row + 1 + col -1] = False
                self.board[row] = False
                self.queen -= 1

if __name__ == "__main__":
    n = int(input())
    chess = Chess(n)
    chess.dfs(1)
    print(chess.answer)