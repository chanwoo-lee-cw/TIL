import java.util.*

class Farm(n: Int, m: Int) {
    private var n = n       // 배추밭의 세로 길이
    private var m = m       // 배추밭의 가로 길이
    private var feild = Array(n) { Array(m) { false } }     // 배추가 심이져 있으면 true or false
    private var visited = Array(n) { Array(m) { false } }   // 이미 확인해본 지역인지 체크

    companion object {
        val way = arrayOf(arrayOf(-1, 0), arrayOf(1, 0), arrayOf(0, -1), arrayOf(0, 1))     // 배추벌래가 갈 수 있는 경로
    }

    /*
    field[y][x]의 위치에 배추를 심는다.
     */
    fun feed(x: Int, y: Int) {
        feild[y][x] = true
    }

    /*
    field[x][y]를 기준으로 인접한 배추의 위치를 찾는다.
     */
    fun findNearby(x: Int, y: Int) {
        var que: Queue<Array<Int>> = LinkedList()
        que.add(arrayOf(y, x))
        visited[y][x] = true
        while (!que.isEmpty()) {
            var curr = que.poll()
            for (next in way) {
                var nextY = next[0] + curr[0]
                var nextX = next[1] + curr[1]
                if (canMove(nextX, nextY))
                    continue
                que.add(arrayOf(nextY, nextX))
                visited[nextY][nextX] = true
            }
        }
    }

    /*
    배추 벌레가 움직일 수 있는 칸인지 체크한다.
     */
    fun canMove(x: Int, y: Int): Boolean {
        if (y < 0 || x < 0 || y >= n || x >= m)
            return true
        else if (!feild[y][x] || visited[y][x])
            return true
        return false
    }
}


fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val t: Int = br.readLine().toInt()
    repeat(t) {
        // 가로, 세로, 배추의 갯수
        var (m, n, k) = br.readLine().split(" ").map { it.toInt() }
        var farm: Farm = Farm(n, m)
        repeat(k) {
            var (x, y) = br.readLine().split(" ").map { it.toInt() }
            farm.feed(x, y)
        }
        var answer: Int = 0      // 해당 필드의 배추의 갯수
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (farm.canMove(j, i))
                    continue
                farm.findNearby(j, i)
                answer++
            }
        }
        bw.write("${answer}\n")
    }
    br.close()
    bw.close()
}