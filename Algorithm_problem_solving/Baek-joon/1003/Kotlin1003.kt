import java.util.*

fun fibonacci(n: Int): Array<Array<Int>> {
    var dp = Array(n + 1, { Array(2, { 0 }) })

    dp[0][0] = 1
    dp[0][1] = 0

    dp[1][0] = 0
    dp[1][1] = 1

    for (i in 2..n) {
        dp[i][0] = dp[i - 1][0] + dp[i - 2][0]
        dp[i][1] = dp[i - 1][1] + dp[i - 2][1]
    }

    return dp
}

fun main(args: Array<String>) {
    val sc: Scanner = Scanner(System.`in`)
    var t: Int = sc.nextInt()
    var testCase: Array<Int> = Array(t, { 0 })

    for (i in 0 until t) {
        testCase[i] = sc.nextInt()
    }

    var maxTest: Int = testCase.maxOrNull() ?: 41

    var dp: Array<Array<Int>> = fibonacci(maxTest)

    for (i in 0 until t) {
        println("${dp[testCase[i]][0]} ${dp[testCase[i]][1]}")
    }

}