// https://www.acmicpc.net/problem/1758
import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val customer = PriorityQueue<Int>()

    repeat(n) {
        customer.add(-br.readLine().toInt())
    }

    val result = calculateTotalTip(customer)

    println(result)
}

fun calculateTotalTip(customers: PriorityQueue<Int>): Long {
    var result = 0L
    var i = 0

    while (customers.isNotEmpty()) {
        i++
        val thisCost = -customers.remove() - (i - 1)
        result += if (thisCost > 0) {
            thisCost
        } else {
            break
        }
    }

    return result
}
