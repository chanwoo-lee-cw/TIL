import java.util.*

var totalZero = 0L
var totalOne = 0L
// 다른 사람의 풀이
fun main() {
    val s = Scanner(System.`in`)
    val num = s.nextInt()
    repeat(num) {
        totalZero = 0L
        totalOne = 0L
        fib(s.nextInt())
        println("$totalZero $totalOne")
    }
}

fun fib(n: Int): Long = when (n) {
    0 -> {
        totalZero++
        0
    }
    1 -> {
        totalOne++
        1
    }
    else -> fib(n - 1) + fib(n + 1)
}