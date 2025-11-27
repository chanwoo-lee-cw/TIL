/**
 * 1-based 펜윅 트리
 */
class FenwickTree(
    val arr: Array<Int>,
) {
    val n: Int
        get() = arr.size

    private val tree = Array(n + 1) { 0 }

    private fun lsb(idx: Int) = idx and -idx

    init {
        build()
    }

    fun update(index: Int, value: Int) {
        require(index in 0 until n) { "index 범위 초과: index=$index, n=$n" }
        updateDiff(index, value - arr[index])
        arr[index] = value
    }

    fun rangeSum(left: Int, right: Int): Int {
        require(left <= right) { "left는 right보다 클 수 없습니다. left=$left, right=$right" }
        // 1-base 라서 각자 +1
        return prefixSum(right + 1) - prefixSum(left)
    }

    private fun prefixSum(
        index: Int
    ): Int {
        var sum = 0
        var idx = index
        while (idx > 0) {
            // 비트 연상으로 구한다
            sum += tree[idx]
            idx -= lsb(idx)
        }
        return sum
    }

    private fun updateDiff(index: Int, diff: Int) {
        // 펜윅 트리는 1-based 라서 1을 더한다
        var idx = index + 1
        while (idx <= n) {
            tree[idx] += diff
            idx += lsb(idx)      // i가 담당하는 구간 길이만큼 점프
        }
    }

    private fun build() {
        arr.indices.forEach { i ->
            updateDiff(i, arr[i])
        }
    }
}