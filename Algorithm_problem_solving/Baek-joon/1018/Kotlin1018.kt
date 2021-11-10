class ChessBoard(n: Int, m: Int, board: Array<Array<Char>>) {
    private var n: Int = n      // 보드의 세로 길이
    private var m: Int = m      // 보드의 가로 길이
    private var board = board   // 현재 보드에 칠해져 있는 색

    private fun recoloring(topColor: Char): Int {
        /*
        좌측 최상단의 색이 topColor일 때 칠해야 하는 최소한의 수를 반환
         */
        var answer = Int.MAX_VALUE      // 결과를 반환해주는 변수
        var boardColorCnt = Array(n + 1) { Array(m + 1) { 0 } }     // i,j의 위치까지 새로 칠해야 되는
        for (i in 1..n) {
            for (j in 1..m) {
                boardColorCnt[i][j] = boardColorCnt[i - 1][j] + boardColorCnt[i][j - 1] - boardColorCnt[i - 1][j - 1]
                if ((i + j) % 2 == 0) {
                    if (topColor == board[i][j]) boardColorCnt[i][j]++
                } else {
                    if (topColor != board[i][j]) boardColorCnt[i][j]++
                }
            }
        }
        // 8*8의 사이즈로 잘라낸다.
        for (i in 8..n) {
            for (j in 8..m) {
                answer = Math.min(
                    answer,
                    boardColorCnt[i][j] - boardColorCnt[i][j - 8] - boardColorCnt[i - 8][j] + boardColorCnt[i - 8][j - 8]
                )
            }
        }

        return answer
    }

    fun getMinRecolor(): Int {
        /*
        흰색과 검은 색 중 가장 적게 칠해야 되는 수를 반환
         */
        return Math.min(recoloring('W'), recoloring('B'))
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var (n, m) = br.readLine().split(" ").map { it.toInt() }
    var board = Array(n + 1) { Array(m + 1) { ' ' } }
    for (i in 1..n) {
        var inLine = br.readLine()
        for (j in 1..m) {
            board[i][j] = inLine.get(j - 1)
        }
    }
    var chess = ChessBoard(n, m, board)
    var answer: Int = chess.getMinRecolor()

    bw.write("${answer}\n")

    br.close()
    bw.close()
}