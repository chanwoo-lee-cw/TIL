fun main() {
    val (n, s, m) = readLine()!!.trim().split(" ").map { it.toInt() }
    val volumes = readLine()!!.trim().split(" ").map { it.toInt() }

    val concert = Concert(n, s, m, volumes)
    println(concert.getMaxVolume())
}

class Concert(
    val n: Int,
    val s: Int,
    val m: Int,
    val volumeList: List<Int>
) {
    fun getMaxVolume(): Int {
        val dp = initDpArray()

        fillDpArray(dp)

        return findMaxPossibleVolume(dp)
    }

    fun initDpArray(): Array<BooleanArray> = Array(n + 1) { BooleanArray(m + 1) }

    fun fillDpArray(dp: Array<BooleanArray>) {
        dp[0][s] = true

        for (i in 0 until n) {
            for (j in 0..m) {
                if (!dp[i][j]) continue
                checkNextVolume(dp, i, j)
            }
        }
    }

    fun checkNextVolume(dp: Array<BooleanArray>, volmeIndex: Int, currentVolume: Int) {
        listOf(
            currentVolume + volumeList[volmeIndex], currentVolume - volumeList[volmeIndex]
        ).forEach { adjustedVolume ->
            if (adjustedVolume in 0..m) {
                dp[volmeIndex + 1][adjustedVolume] = true
            }
        }
    }

    fun findMaxPossibleVolume(dp: Array<BooleanArray>): Int {
        for (j in m downTo 0) {
            if (dp[n][j]) return j
        }
        return -1
    }
}
