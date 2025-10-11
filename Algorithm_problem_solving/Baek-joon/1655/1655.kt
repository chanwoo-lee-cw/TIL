import java.util.Collections
import java.util.PriorityQueue

class Median(
) {
    private val leftHeap: PriorityQueue<Int> = PriorityQueue(Collections.reverseOrder())
    private val rightHeap: PriorityQueue<Int> = PriorityQueue()
    private val stringBuilder = StringBuilder()

    fun insertNumber(num: Int) {
        if (leftHeap.isEmpty()) {
            leftHeap.add(num)
        } else if (num < leftHeap.peek()) {
            leftHeap.add(num)
        } else {
            rightHeap.add(num)
        }
        balanceControl()
    }


    private fun balanceControl() {
        if (leftHeap.size == rightHeap.size) {
            // 양측의 밸런스가 맞는 경우 아무것도 안 함
            null
        } else if (leftHeap.size < rightHeap.size + 1) {
            leftHeap.add(rightHeap.poll())
        } else if (leftHeap.size > rightHeap.size + 1) {
            rightHeap.add(leftHeap.poll())
        }
    }

    fun medianToString() {
        stringBuilder.append(
            leftHeap.peek()
        ).append("\n")
    }


    fun printMedianList() {
        print(stringBuilder.toString())
    }

}

fun main() {
    val n: Int = readLine()!!.toInt()

    val median = Median()

    repeat(n) {
        median.insertNumber(readLine()!!.toInt())
        median.medianToString()
    }
    median.printMedianList()
}