fun main() {
    val insertLine = readLine()!!
    val numberArray = insertLine
        .toCharArray()
        .map { it.toString().toInt() }
        .toIntArray()
        .sortedDescending()

    println(numberArray.joinToString(""))
}