# 펜윅 트리

## 펜윅 트리란?

> 배열의 누적합을 부분적으로 저장해서, 구간 합과 값 변경을 둘 다 **O(log N)** 시간에 계산할 수 있게 만든 자료구조
>
> 즉, 펜윅 트리는 비트연산자를 이용한 "구간 합 + 값 변경"을 처리하기 위한 자료 구조이다.



## 원리

해당 인덱스의 **마지막 1비트**를 이용해서 누적합을 구한 구간을 계산한다.

![](https://velog.velcdn.com/images/alphanewbie/post/7c2b8e03-5d07-40a4-8965-d46f4058fa99/image.png)


| tree 인덱스 | 비트 패턴(2진수) | 담당 길이 | 저장되는 값(구간 합)          |
| ----------- | ---------------- | --------- | ----------------------------- |
| 1           | `1`              | 1칸       | 1                             |
| 2           | `10`             | 2칸       | 1 + 2                         |
| 3           | `11`             | 1칸       | 3                             |
| 4           | `100`            | 4칸       | 1 + 2 + 3 + 4                 |
| 5           | `101`            | 1칸       | 5                             |
| 6           | `110`            | 2칸       | 5 + 6                         |
| 7           | `111`            | 1칸       | 7                             |
| 8           | `1000`           | 8칸       | 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 |



## 구간 범위 구하기

```
i & -i : i에서 담당할 길이
```

예시,

```
2 -> 0010
-2 -> 1110

0010
1110
-----
0010
```

즉, 2는 `0010 -> 2` 의 길이의 범위만큼 저장한다



### 구간합 구하기

```kotlin
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
```

예, 0~5까지의 구간 합을 구할시 필요한 값

![](https://velog.velcdn.com/images/alphanewbie/post/ebbcab1a-801d-4bb2-901d-58ed06dc915d/image.png)


1. Idx : 5
   1. sum : 5
   2. next idx : 5 - 1 = 4
2. Idx : 4
   1. sum : 21
   2. next idx : 4 - 4 = 0





### 구간 업데이트

```kotlin
fun update(index: Int, value: Int) {
    require(index in 0 until n) { "index 범위 초과: index=$index, n=$n" }
    updateDiff(index, value - arr[index])
    arr[index] = value
}

private fun updateDiff(index: Int, diff: Int) {
    // 펜윅 트리는 1-based 라서 1을 더한다
    var idx = index + 1
    while (idx <= n) {
        tree[idx] += diff
        idx += lsb(idx)      // i가 담당하는 구간 길이만큼 점프
    }
}
```

예, index 0을 6으로 업데이트 했을 시, 같이 업데이트 해야하는 값

![](https://velog.velcdn.com/images/alphanewbie/post/22521f32-6f68-40b3-8beb-efa3ba499a0f/image.png)


1. Idx : 1
   1. tree[1] = 1 (+5) = 6
   2. next idx = 2
2. Idx : 2
   1. tree[2] = 1 (+5) + 2 = 8
   2. next idx  = 4
3. idx : 4
   1. tree[4] = 1 (+5) + 2 + 3 + 4 =  15
   2. next idx = 8



## 구현

### Kotlin

```kotlin
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

//fun main() {
//    val arr: Array<Int> = arrayOf(1, 2, 3, 4, 5)
//
//    val fenwickTree = FenwickTree(arr)
//
//    fenwickTree.update(0, 6)
//    println(
//        fenwickTree.rangeSum(0, arr.size - 1)
//    )
//}
```



## 참고 문헌

- [https://yoongrammer.tistory.com/104](https://yoongrammer.tistory.com/104)
- [https://yabmoons.tistory.com/438](https://yabmoons.tistory.com/438)