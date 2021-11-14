import java.util.*


class Combination {
    private var n: Int
    private var combination: Array<Array<Long>>

    constructor(n: Int) {
        this.combination = Array(n + 1, { Array(n + 1, { 0 }) })
        this.n = n
    }

    fun init(n: Int, r: Int): Long {
        if (combination[n][r] != 0L) {
            return combination[n][r]
        } else if (n == r || r == 0) {
            combination[n][r] = 1
            return 1
        } else {
            combination[n][r] = init(n - 1, r - 1) + init(n - 1, r)
            return combination[n][r]
        }
    }
}

fun main() {
    val s = Scanner(System.`in`)
    val t = s.nextInt()

    var com = Combination(40);
    repeat(t) {
        var n = s.nextInt()
        var m = s.nextInt()
        println(com.init(m, n))
    }

}