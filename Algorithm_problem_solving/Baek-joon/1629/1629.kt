// https://www.acmicpc.net/problem/1629

fun calculator(
    numA: Long,
    numB: Long,
    numC: Long
): Long {
    return if (numB == 0L) {
        1
    } else if (numB % 2 == 0L) {
        val splitedResult = calculator(numA, numB / 2, numC)
        return splitedResult * splitedResult % numC
    } else {
        return (calculator(numA, numB - 1, numC) * numA) % numC
    }
}

fun main() {
    val (numA, numB, numC) = readln().trim().split(' ').map { it.toLong() }


    println(calculator(numA, numB, numC))
}