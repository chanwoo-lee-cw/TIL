import java.util.*

/*
원하는 수를 모두 뽑아내는 경우의 최소 회전수를 반환한다.
 */
fun getMinRoate(n: Int, m: Int, arr: List<Int>): Int {
    var answer: Int = 0     // 몇번 2,3 번 연산을 했는지 계산
    var deque: ArrayDeque<Int> = ArrayDeque<Int>()  // 수를 저장해둘 deque
    for (i in 1..n) {
        deque.add(i)
    }
    for (item in arr) {
        var numIndex = deque.indexOf(item)
        if (numIndex < deque.size / 2.0) {
            // 만약 찾은 위치가 가운데에 있거나 그보다 앞에 있을 때
            while (deque.peekFirst() != item) {
                deque.addLast(deque.pollFirst())
                answer++
            }
        } else {
            // 찾은 위치가 가운데보다 뒤에 있을 때
            while (deque.peekFirst() != item) {
                deque.addFirst(deque.pollLast())
                answer++
            }
        }
        deque.pollFirst()
    }
    return answer
}


fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    var (n, m) = br.readLine().split(" ").map { it.toInt() }
    var arr: List<Int> = br.readLine().split(" ").map { it.toInt() }

    var answer = getMinRoate(n, m, arr)
    bw.write("${answer}\n")

    br.close()
    bw.close()
}