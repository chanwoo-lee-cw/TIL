// https://www.acmicpc.net/problem/1654


fun getNeedLength(
    needLines: Int,
    lanLines: List<Long>,
): Long {
    var answer = 0L
    val sortedLines = lanLines.sorted()

    var start = 0L
    var end = sortedLines.last()
    while (end - start >= 0) {
        val mid = (start + end) / 2
        if (mid == 0L) {
            return end
        }
        var getLineNumber = 0L
        for (item in sortedLines) {
            getLineNumber += item / mid
        }
        if (getLineNumber < needLines) {
            end = mid - 1
        } else {
            answer = mid
            start = mid + 1
        }
    }

    return answer
}


fun main() {
    val (lanNumber, needLines) = readln().trim().split(" ").map { it.toInt() }
    val lanLines = mutableListOf<Long>()

    repeat(lanNumber) {
        lanLines.add(readln().trim().toLong())
    }

    println(getNeedLength(needLines, lanLines))
}