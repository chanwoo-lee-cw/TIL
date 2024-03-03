fun main() {
    val (n, s, m) = readLine()!!.trim().split(" ").map { it.toInt() }
    val volumes = readLine()!!.trim().split(" ").map { it.toInt() }

    val concert = Concert(n, s, m, volumes)
    println(concert.getMaxVolume())
}

class Concert(
    val numberOfSongs: Int, 
    val startVolume: Int, 
    val maxVolume: Int, 
    val volumeAdjustments: List<Int>
) {
    fun getMaxVolume(): Int {
        val dp = initDpArray()

        fillDpArray(dp)

        return findMaxPossibleVolume(dp)
    }

    fun initDpArray(): Array<BooleanArray> = Array(numberOfSongs + 1) { BooleanArray(maxVolume + 1) }

    fun fillDpArray(dp: Array<BooleanArray>) {
        dp[0][startVolume] = true

        for (i in 0 until numberOfSongs) {
            for (j in 0..maxVolume) {
                if (!dp[i][j]) continue

                applyVolumeAdjustment(dp, i, j)
            }
        }
    }

    fun applyVolumeAdjustment(dp: Array<BooleanArray>, songIndex: Int, currentVolume: Int) {
        listOf(currentVolume + volumeAdjustments[songIndex], currentVolume - volumeAdjustments[songIndex]).forEach { adjustedVolume ->
            if (adjustedVolume in 0..maxVolume) {
                dp[songIndex + 1][adjustedVolume] = true
            }
        }
    }

    fun findMaxPossibleVolume(dp: Array<BooleanArray>): Int {
        for (j in maxVolume downTo 0) {
            if (dp[numberOfSongs][j]) return j
        }
        return -1
    }
}
