import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    println(calculateX(n))
}

fun calculateX(num: Int): Int {
    val dp = IntArray(num + 1) { Int.MAX_VALUE }
    dp[0] = 0
    dp[1] = 0

    for (i in 2..num) {
        dp[i] = dp[i - 1] + 1
        if (i % 2 == 0) {
            dp[i] = minOf(dp[i], dp[i / 2] + 1)
        }
        if (i % 3 == 0) {
            dp[i] = minOf(dp[i], dp[i / 3] + 1)
        }
    }
    return dp[num]
}
