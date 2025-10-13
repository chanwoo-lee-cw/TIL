import kotlin.math.max

class ConveyorBeltSushi(
    private val n: Int,
    private val eatAble: Int,
    private val freeCoupon: Int,
) {
    private val conveyorBelt = Array(n + 1) { 0 }
    private var insertPosition = 0

    fun setSushi(sushi: Int) {
        conveyorBelt[insertPosition++] = sushi
    }

    fun maxSushiEat(): Int {
        var answer = 0;
        for (i in 0 until n) {
            val eatSushi: MutableSet<Int> = mutableSetOf()
            answer = max(answer, eatSushi(eatSushi, 0, i))
        }
        return answer
    }

    private fun nextPosition(position: Int): Int {
        return if (position + 1 < n) {
            position + 1
        } else {
            (position + 1) % n
        }
    }

    private fun eatSushi(
        eatSushi: MutableSet<Int>,
        payCount: Int,
        position: Int,
    ): Int {
        var answer = 0
        if (payCount == eatAble) {
            answer = eatSushi.size
            if (!eatSushi.contains(freeCoupon)) {
                answer += 1
            }
        } else {
            eatSushi.add(conveyorBelt[position])
            answer = max(
                answer,
                eatSushi(eatSushi, payCount + 1, nextPosition(position))
            )
        }
        return answer
    }

}

fun main() {
    val (n, d, k, c) = readLine()!!.split(" ").map { it.toInt() }
    val conveyorBeltSushi = ConveyorBeltSushi(n, k, c)

    repeat(n) {
        conveyorBeltSushi.setSushi(
            readLine()!!.toInt()
        )
    }
    println(conveyorBeltSushi.maxSushiEat())
}