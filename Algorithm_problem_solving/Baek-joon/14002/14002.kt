import java.util.*

/**
 * 가장 긴 부분 순열(lis)를 계산하는 클래스
 */
class LIS(
    private val arr: LongArray,
) {
    data class LisResult(
        val length: Int,
        val arr: LongArray,
    )

    private val lisResult: LisResult by lazy {
        getLisTracking()
    }


    fun printLisLength() {
        println(lisResult.length)
        println(
            lisResult.arr.joinToString(" ")
        )
    }


    /**
     * 가장 긴 부분 순열의 길이를 구한다.
     */
    private fun getLisTracking(): LisResult {
        val positionBackTracking = Stack<Int>()
        val lisTrackingArr: LongArray = LongArray(arr.size) { 0 }
        var lastIndex = -1
        arr.forEach {
            if (lastIndex == -1) {
                // 배열이 비어있다면 넣는다.
                lastIndex += 1
                lisTrackingArr[lastIndex] = it
                positionBackTracking.add(lastIndex)
            } else if (it > lisTrackingArr[lastIndex]) {
                // 마지막 값보다 현재 값이 크다면 가장 뒤에 붙힌다.
                lastIndex += 1
                lisTrackingArr[lastIndex] = it
                positionBackTracking.add(lastIndex)
            } else {
                val replacePosition = lowerBound(lisTrackingArr, it, 0, lastIndex)
                positionBackTracking.add(replacePosition)
                lisTrackingArr[replacePosition] = it
            }
        }

        return LisResult(
            lastIndex + 1,
            getLisArr(positionBackTracking, lastIndex)
        )
    }

    /**
     * 백 트래킹으로 가장 긴 lis를 구한다
     */
    private fun getLisArr(
        positionBackTracking: Stack<Int>,
        lastIndex: Int
    ): LongArray {
        val lisList = mutableListOf<Long>()

        var trackingTop = lastIndex
        arr.reversed().forEach {
            val insertPosition = positionBackTracking.pop()
            if (insertPosition == trackingTop) {
                lisList.add(it)
                trackingTop -= 1
                if (trackingTop < 0) return@forEach;
            }
        }
        return lisList.reversed().toLongArray()
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
    LIS(arr).printLisLength()
}