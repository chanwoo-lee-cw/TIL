import java.io.IOException

// https://www.acmicpc.net/problem/1600
fun main() {
    val availableJump: Int = readlnOrNull()?.toIntOrNull() ?: throw IOException("에러 발생")
    val (width, height) = readLine()!!.trim().split(" ").map { it.toInt() }

    val matrix = Array(height) { Array(width) { 0 } }

    for (i in 0 until height) {
        val tempInput = readLine()!!.trim().split(" ").map { it.toInt() }.toTypedArray()
        for ((j, value) in tempInput.withIndex()) { //indices -> 0..2
            matrix[i][j] = value
        }
    }

    println(MonkeyJump(availableJump, matrix).getShortestJumping())
}

class MonkeyJump(
    val k: Int, val matrix: Array<Array<Int>>
) {
    val w: Int = matrix[0].size
    val h: Int = matrix.size
    var walk = arrayOf(
        intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1)
    )
    val jump: Array<Array<Int>> = arrayOf(
        arrayOf(2, -1),
        arrayOf(2, 1),
        arrayOf(1, 2),
        arrayOf(-1, 2),
        arrayOf(-2, 1),
        arrayOf(-2, -1),
        arrayOf(1, -2),
        arrayOf(-1, -2)
    )

    class MoveInfo(
        val y: Int, val x: Int, val jumpCount: Int, val moveCount: Int
    )

    fun getShortestJumping(): Int {
        val visited = Array(h) { Array(w) { -1 } }
        val queue = ArrayDeque<MoveInfo>()

        queue.add(MoveInfo(0, 0, 0, 0))
        visited[0][0] = 0

        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            if (curr.y == h - 1 && curr.x == w - 1) {
                return curr.moveCount
            }
            for ((moveY, moveX) in walk) {
                val nextY = curr.y + moveY
                val nextX = curr.x + moveX
                if (nextY < 0 || nextY >= h || nextX < 0 || nextX >= w) continue
                if (matrix[nextY][nextX] == 1) continue
                if (visited[nextY][nextX] >= curr.jumpCount) continue
                queue.add(MoveInfo(nextY, nextX, curr.jumpCount, curr.moveCount + 1))
                visited[nextY][nextX] = curr.jumpCount
            }
            if (curr.jumpCount < k) {
                for ((moveY, moveX) in jump) {
                    val nextY = curr.y + moveY
                    val nextX = curr.x + moveX
                    if (nextY < 0 || nextY >= h || nextX < 0 || nextX >= w) continue
                    if (matrix[nextY][nextX] == 1) continue
                    if (visited[nextY][nextX] >= curr.jumpCount) continue
                    queue.add(MoveInfo(nextY, nextX, curr.jumpCount + 1, curr.moveCount + 1))
                    visited[nextY][nextX] = curr.jumpCount + 1
                }
            }
        }

        return -1
    }
}