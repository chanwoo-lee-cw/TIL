class SegmentTree(
    val arr: Array<Int>,
) {
    // 1-base SegmentTree
    private val tree = Array(n * 4) { 0 }

    private val n
        get() = arr.size

    init {
        build(1, 0, n - 1)
    }

    private fun build(
        node: Int,
        start: Int,
        end: Int
    ) {
        if (start == end) {
            //
            tree[node] = arr[start]
        } else {
            val mid: Int = (start + end) / 2
            build(node * 2, start, mid)
            build(node * 2 + 1, mid + 1, end)
            tree[node] = tree[node * 2] + tree[node * 2 + 1]
        }
    }

    fun query(
        left: Int,
        right: Int,
    ): Int {
        return query(left, right, 1, 0, n - 1)
    }

    fun update(
        index: Int,
        value: Int,
    ) {
        update(index, value, 1, 0, n - 1)
    }

    private fun query(
        left: Int,
        right: Int,
        node: Int,
        start: Int,
        end: Int,
    ): Int {
        if (right < start || left > end) {
            // 해당 노드가 범위 밖이라면 계산하지 않는다.
            return 0
        } else if (left <= start && end <= right) {
            // 해당 구간이 구해야하는 범위 안이면 전체 값을 리턴한다
            return tree[node]
        } else {
            // left, right가 전체 배열의 범위에 애매하게 걸쳐 있는경우 쪼갠다.
            val mid: Int = (start + end) / 2
            val leftValue = query(
                left = left,
                right = right,
                node = node * 2,
                start = start,
                end = mid
            )
            val rightValue = query(
                left = left,
                right = right,
                node = node * 2 + 1,
                start = mid + 1,
                end = end
            )

            return leftValue + rightValue
        }
    }

    private fun update(
        index: Int,
        value: Int,
        node: Int,
        start: Int,
        end: Int,
    ) {
        if (start == end) {
            // 리프 노드면 업데이트
            arr[index] = value
            tree[node] = value
        } else {
            val mid: Int = (start + end) / 2
            // 바이너리 서치로 바꿀 위치를 찾아간다.
            if (index <= mid) {
                update(index, value, node * 2, start, mid)
            } else {
                update(index, value, node * 2 + 1, mid + 1, end)
            }
            // 아래서 바꾼 내용을 바탕으로 부모 노드를 변경
            tree[node] = tree[node * 2] + tree[node * 2 + 1]
        }
    }
}

//fun main() {
//    val arr: Array<Int> = arrayOf(1, 2, 3, 4, 5)
//
//    val segmentTree: SegmentTree = SegmentTree(arr)
//
//    segmentTree.update(3, 6)
//    print(segmentTree.query(arr.indexOf(2), arr.indexOf(5)))
//}