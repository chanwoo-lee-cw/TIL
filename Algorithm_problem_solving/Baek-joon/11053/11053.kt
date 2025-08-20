/**
 * 가장 긴 부분 순열(lis)를 계산하는 클래스
 */
class LongestIncreasingSubsequence {
    /**
     * 가장 긴 부분 순열의 길이를 구한다.
     */
    fun lisLength(arr: LongArray): Int {
        val lis = LongArray(arr.size) { 0 }
        var lastIndex = -1
        arr.forEach {
            if (lastIndex == -1) {
                // 배열이 비어있다면 넣는다.
                lastIndex += 1
                lis[lastIndex] = it
            } else if (it > lis[lastIndex]) {
                // 마지막 값보다 현재 값이 크다면 가장 뒤에 붙힌다.
                lastIndex += 1
                lis[lastIndex] = it
            } else {
                lis[lowerBound(lis, it, 0, lastIndex)] = it
            }
        }
        return lastIndex + 1
    }

    /**
     * @param arr 정렬된 리스트
     * @param find 찾으려는 값
     * @param start 찾으려는 범위의 시작 index
     * @param end 찾으려는 범위의 마지막 index
     * @return 리스트에서 find 보다 커지는 값이 나오는 첫번째 위치
     */
    private fun lowerBound(
        arr: LongArray,
        find: Long,
        start: Int,
        end: Int,
    ): Int {
        var s = start
        var e = end
        while (s < e) {
            val mid = (s + e) / 2
            if (arr[mid] < find) {
                s = mid + 1
            } else {
                e = mid
            }
        }
        return e
    }
}


fun main() {
    val n = readLine()?.toInt() ?: throw NumberFormatException()
    val arr = readLine()?.split(" ")?.map { it.toLong() }?.toLongArray() ?: throw NumberFormatException()

    println(
        LongestIncreasingSubsequence().lisLength(arr)
    )
}