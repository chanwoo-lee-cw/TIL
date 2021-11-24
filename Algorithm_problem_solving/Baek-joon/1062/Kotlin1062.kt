import kotlin.collections.HashMap
import kotlin.collections.HashSet

class WordDictionnary(n: Int, k: Int, useAlphabet: Array<HashSet<Char>>) {
    private var n: Int = n      // 단어의 총 갯수
    private var k: Int = k      // 배울 수 있는 알파벳의 갯수
    private var useAlphabet: Array<HashSet<Char>> = useAlphabet     // 각 단어별 사용된 알파벳의 set

    fun canLearnWordNum(): Int {
        var answer: Int = 0     // 배울 수 있는 단어의 갯수
        var allAlphabet: HashSet<Char> = HashSet()
        for (i in 0 until n) {
            allAlphabet.addAll(useAlphabet[i])
        }
        if (k < 5) {
            // 'a', 'n', 't', 'i', 'c' 중 하나라도 못 배우는 경우
            return 0
        } else if (k - 5 >= allAlphabet.size) {
            // 사용된 모든 알파벳을 배울 수 있는 경우
            return n
        } else {
            // 그 밖의 다른 경우
            var arrList: ArrayList<Char> = arrayListOf()
            var alphabetList: List<Char> = allAlphabet.toList()
            answer = dfs(arrList, alphabetList, 0)
        }
        return answer;
    }

    /*
    dfs으로 배울 수 있는 모든 알파벳의 경우의 수를 구한다.
     */
    private fun dfs(arrList: ArrayList<Char>, alphabetList: List<Char>, position: Int): Int {
        var answer = 0      // 배울 수 있는 단어의 갯수
        if (arrList.size >= k - 5) {
            // 필수 알파벳을 제외한 다른 단어들을 배운 경우
            for (i in 0 until n) {
                answer++;
                for (item in useAlphabet[i]) {
                    if (!arrList.contains(item)) {
                        answer--
                        break
                    }
                }
            }
        } else {
            // 배울 수 있는 알파벳이 남아 있는 경우
            for (i in position until alphabetList.size) {
                arrList.add(alphabetList.get(i))
                answer = Math.max(answer, dfs(arrList, alphabetList, i + 1))
                arrList.removeAt(arrList.lastIndex)
            }
        }
        return answer
    }


}


fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    var (n, k) = br.readLine().split(" ").map { it.toInt() }
    var alphabetMap: HashMap<Char, Int> = HashMap()

    var useAlphabet: Array<HashSet<Char>> = Array(n) { HashSet() }
    for (i in 0 until n) {
        var inputLine: String = br.readLine()
        for (item in inputLine) {
            if (item in arrayOf('a', 'n', 't', 'i', 'c')) continue
            useAlphabet[i].add(item)
        }
    }
    var dic: WordDictionnary = WordDictionnary(n, k, useAlphabet)
    var answer = dic.canLearnWordNum()

    bw.write("${answer}\n")

    br.close()
    bw.close()
}