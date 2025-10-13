// https://www.acmicpc.net/problem/15961
// 시간 초과
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
            answer = max(answer, eatSushi(i))
        }
        return answer
    }

    private fun nextPosition(position: Int): Int {
        return if (position + eatAble < n) {
            position + eatAble
        } else {
            (position + eatAble) % n
        }
    }

    private fun eatSushi(
        startPosition: Int,
    ): Int {
        val next = nextPosition(startPosition)
        val eatSushi = mutableSetOf<Int>();
        if (next <= startPosition) {
            eatSushi.addAll(conveyorBelt.slice(0 until next))
            eatSushi.addAll(conveyorBelt.slice(startPosition until n))
        } else {
            eatSushi.addAll(conveyorBelt.slice(startPosition..next))
        }
        if (!eatSushi.contains(freeCoupon)) {
            eatSushi.add(freeCoupon)
        }
        return eatSushi.size
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