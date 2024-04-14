// https://www.acmicpc.net/problem/1644

/**
 * 에라토스 테네스의 체로 소수들을 찾는다.
 *
 * @param n n의 범위 까지의 소수
 * @return 소수인 수는 true로 체크되어있는 n까지의 배열
 */
fun eratostenes(
    num: Int
): Array<Boolean> {
    val sieve = Array(num + 1) { true }
    sieve[0] = false
    sieve[1] = false

    for (i in 2..Math.sqrt(num.toDouble()).toInt()) {
        if (sieve[i]) {
            for (j in i * i..num step i) {
                sieve[j] = false
            }
        }
    }

    return sieve
}

/**
 * 연속된 소수의 합이 goal이 되는 경우의 수를 찾는다
 *
 * @param goal 목표 합계 값
 * @param sieve 소수 여부를 나타내는 배열
 * @return 연속된 소수의 합으로 goal이 만들어지는 경우의 수
 */
fun getPrimeSum(
    goal: Int,
    seive: Array<Boolean>
): Int {
    var answer = 0
    val primeList: MutableList<Int> = mutableListOf()

    var pivot = 2
    var primeSum = 0

    while (pivot <= goal + 1) {
        if (primeSum == goal) {
            answer += 1
        }
        if (primeSum <= goal) {
            while (pivot <= goal && !seive[pivot]) {
                pivot++
            }
            primeSum += pivot
            primeList.add(pivot++)
        } else {
            primeSum -= primeList.first()
            primeList.removeAt(0)
        }
    }

    return answer
}

fun main() {
    val num = readln().toInt()
    val eratosSeive = eratostenes(num)

    println(getPrimeSum(num, eratosSeive))
}