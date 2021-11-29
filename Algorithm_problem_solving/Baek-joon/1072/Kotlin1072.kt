import kotlin.collections.HashMap
import kotlin.collections.HashSet


fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var (x, y) = br.readLine().split(" ").map { it.toInt() }

    var game = Game(x, y)
    var answer: Int = game.getConvertRate()

    bw.write("${answer}\n")
    br.close()
    bw.close()
}

class Game(x: Int, y: Int) {
    var x = x
    var y = y
    var z = ((100 * y.toDouble()) / x).toInt()

    fun getConvertRate(): Int {
        if (z >= 99) {
            return -1
        }
        var s = 0
        var e = x
        var answer = Int.MAX_VALUE
        while (e - s >= 0) {
            var m = (s + e) / 2
            var temp = ((100 * (y + m).toDouble()) / (x + m)).toInt()
            if (z < temp) {
                answer = m
                e = m - 1
            } else {
                s = m + 1
            }
        }
        return answer
    }
}
