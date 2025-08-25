// 시간 초과

class MakePalindromeSolution(
    val forPalindrome: String
) {
    private val sortedArr = forPalindrome.toCharArray().sorted()
    private val palindromeLength: Int = forPalindrome.length

    private val visited: BooleanArray by lazy { BooleanArray(palindromeLength) }
    private val charArray = mutableListOf<Char>()

    fun dfs(
    ): String? {
        if (charArray.size == palindromeLength) {
            if (charArray == charArray.reversed()) {
                return charArray.joinToString("")
            }
        } else {
            for (idx in 0 until palindromeLength) {
                if (visited[idx]) continue
                visited[idx] = true
                charArray.add(sortedArr[idx])
                val answer = dfs()
                charArray.removeLast()
                answer?.let { return it }
                visited[idx] = false
            }
        }
        return null
    }

    fun printPalindrome() {
        print(dfs() ?: "I'm Sorry Hansoo")
    }

}


fun main() {
    val checkString = readLine().toString()
    MakePalindromeSolution(checkString).printPalindrome()
}