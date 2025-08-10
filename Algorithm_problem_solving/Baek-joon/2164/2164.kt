class Solution(
    n: Int,
) {
    private val deque: ArrayDeque<Int> = ArrayDeque()

    init {
        for (i in 1..n) {
            deque.addFirst(i)
        }
    }

    /**
     * 스탭 1 : 제일 위의 카드를 버린다.
     */
    fun discardLast() {
        deque.removeLast()
    }

    /**
     * 스탭 2 : 제일 아래의 카드를 제일 위로 옮긴다
     */
    fun moveLastToFirst() {
        deque.addFirst(
            deque.removeLast()
        )
    }

    /**
     * 카드 마지막 한장이 남을 때까지, 작업을 실행한다.
     */
    fun runJob(): Int {
        while (deque.size != 1) {
            discardLast()
            moveLastToFirst()
        }
        return deque.removeFirst()
    }

}


fun main() {
    val n = readLine()?.trim()?.toInt() ?: throw NumberFormatException()
    val solution = Solution(n)

    println(solution.runJob())
}