// https://www.acmicpc.net/problem/2237
// 실패 :  메모리 초과

class Solution(
    val n: Int,
    val t: Int,
    val numberList: MutableList<Int>
) {
    private val deque = ArrayDeque<Int>()

    /**
     * Con(i) 연산을 실행한다.
     * Con(i) : A(i) - A(i+1), 후 A(i+1) 삭제
     */
    private inline fun MutableList<Int>.conOperation(
        i: Int,
    ): Int {
        val removed = this.removeAt(i + 1)
        this[i] = this[i] - removed
        return removed
    }

    /**
     * Con(i) 연산을 롤백한다
     */
    private inline fun MutableList<Int>.rollbackConOperation(
        i: Int,
        removedNumber: Int
    ) {
        this[i] = this[i] + removedNumber
        this.add(i + 1, removedNumber)
    }


    /**
     * DFS로 T 값이 나올때까지 연산한다.
     */
    fun dfs(): Boolean {
        if (numberList.size == 1 && numberList.get(0) == t) {
            printAnswer()
            return true
        }
        // else
        for (i in 0 until numberList.size - 1) {
            val removed = numberList.conOperation(i)
            deque.addLast(i + 1)
            if (dfs()) return true
            numberList.rollbackConOperation(i, removed)
            deque.removeLast()
        }
        return false
    }

    private fun printAnswer() {
        deque.forEach {
            print("$it\n")
        }
    }

}


fun main() {
    val (n, t) = readLine()?.trim()?.split(" ")?.map { it.toInt() } ?: throw NumberFormatException()
    val numberList =
        readLine()?.split(" ")?.map { item -> item.toInt() }?.toMutableList() ?: throw NumberFormatException()


    val solution = Solution(n, t, numberList)

    when (solution.dfs()) {
        true -> {}
        false -> print("0")
    }
}