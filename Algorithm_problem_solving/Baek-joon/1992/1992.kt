import java.io.File

fun main() {
    File("inputFile.txt").bufferedReader(Charsets.UTF_8).useLines { lines ->
        val iterator = lines.iterator()

        val n = iterator.next().trim().toInt()

        val matrix = Array(n) {
            val line = iterator.next()
            IntArray(n) { col -> line[col] - '0' }
        }

        val quadTree = QuadTree(n, matrix)
        print(quadTree.encode())
    }
}


class QuadTree(
    val n: Int, val matrix: Array<IntArray>
) {

    fun encode() = compression(0, 0, n)

    private fun compression(
        y: Int, x: Int, size: Int
    ): String {
        if (size == 1) return matrix[y][x].toString()
        val halfSize = size / 2;

        val leftUp = compression(
            y, x, halfSize
        );
        val leftDown = compression(
            y + halfSize, x, halfSize
        );
        val rightUp = compression(
            y, x + halfSize, halfSize
        );
        val rightDown = compression(
            y + halfSize, x + halfSize, halfSize
        );

        if (isUniform(leftUp, leftDown, rightUp, rightDown)) {
            return leftUp
        } else {
            return "($leftUp$rightUp$leftDown$rightDown)"
        }
    }

    private fun isUniform(
        leftUp: String,
        leftDown: String,
        rightUp: String,
        rightDown: String,
    ): Boolean {
        return leftUp.length == 1 && leftUp == leftDown && rightUp == rightDown && leftUp == rightUp
    }
}