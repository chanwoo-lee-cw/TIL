data class NumberWithConvertCount(
    val number: Long,
    val convertCount: Int
)

class ConvertAtoB {

    /**
     * startNumber로 destNumber 다음과 같은 규칙을 통해 만들 수 있는 최소의 횟수를 리턴한다.
     *
     * <ul>
     *     <li>2를 곱한다.</li>
     *     <li>1을 수의 가장 오른쪽에 추가한다.</li>
     * </ul>
     *
     * @param startNumber 시작할 수
     * @param destNumber 목표로 하는 수
     * @return 변경할 수 있다면 : 최소 횟수, 없다면 -1
     */
    fun getConvertCount(
        startNumber: Long,
        destNumber: Long
    ): Int {
        val queue = ArrayDeque<NumberWithConvertCount>();
        val visited: HashSet<Long> = HashSet()

        queue.add(NumberWithConvertCount(startNumber, 1))

        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            if (curr.number == destNumber) {
                return curr.convertCount
            }
            val nextDouble = curr.number * 2
            if (!isOverDestNumber(nextDouble, destNumber)) {
                pushIfNotVisited(nextDouble, visited, queue, curr.convertCount)
            }
            val rightAddOne = (curr.number.toString() + "1").toLong()
            if (!isOverDestNumber(rightAddOne, destNumber)) {
                pushIfNotVisited(rightAddOne, visited, queue, curr.convertCount)
            }
        }
        return -1
    }

    /**
     * next에 한번도 방문한적 없다면 queue에 넣는다
     */
    private fun pushIfNotVisited(
        next: Long,
        visited: HashSet<Long>,
        queue: ArrayDeque<NumberWithConvertCount>,
        convertCount: Int
    ) {
        if (!isVisited(next, visited)) {
            visited.add(next)
            queue.add(
                NumberWithConvertCount(next, convertCount + 1)
            )
        }
    }

    /**
     * @return next가 target 보다 크다면 true
     */
    private fun isOverDestNumber(currNumber: Long, destNumber: Long): Boolean {
        return currNumber > destNumber
    }

    /**
     * @return next가 visited에 방문한적 있다면 true
     */
    private fun isVisited(currNumber: Long, visited: HashSet<Long>): Boolean {
        return visited.contains(currNumber)
    }
}


fun main() {
    val (a, b) = readLine()?.trim()?.split(" ")?.map { it.toLong() } ?: throw NumberFormatException()

    print(ConvertAtoB().getConvertCount(a, b))
}