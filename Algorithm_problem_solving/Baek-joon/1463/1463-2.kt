import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    BufferedReader(InputStreamReader(System.`in`)).use { br ->
        val x = br.readLine().trim().toInt()
        NumberToOne(x).printResult()
    }
}

class NumberToOne(
    val number: Int,
) {
    fun <K> Map<K, Boolean>.getOrFalse(key: K): Boolean {
        return this[key] ?: false
    }

    fun printResult() {
        println(bfs())
    }

    fun bfs(): Int {
        val queue = ArrayDeque<NumberWithCount>()
        val visited = HashMap<Int, Boolean>()

        queue.add(
            NumberWithCount(1, 0)
        )
        visited[1] = true

        while (!queue.isEmpty()) {
            val current = queue.removeFirst()

            if (current.number > number) {
                continue
            }
            if (current.number == number) {
                return current.count
            }

            if (!visited.getOrFalse(current.number * 3)) {
                visited[current.number * 3] = true
                queue.add(NumberWithCount(current.number * 3, current.count + 1))
            }
            if (!visited.getOrFalse(current.number * 2)) {
                visited[current.number * 2] = true
                queue.add(NumberWithCount(current.number * 2, current.count + 1))
            }
            if (!visited.getOrFalse(current.number + 1)) {
                visited[current.number + 1] = true
                queue.add(NumberWithCount(current.number + 1, current.count + 1))
            }
        }
        return -1;
    }
}

data class NumberWithCount(val number: Int, val count: Int)