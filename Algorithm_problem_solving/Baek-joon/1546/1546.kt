import java.io.IOException

fun main() {
    val n: Int = readlnOrNull()?.toIntOrNull() ?: throw IOException("에러 발생")
    val grades = readln().trim().split(" ").map { it.toFloat() }.toTypedArray()

    val grade = Grade(grades)
    grade.convertGrade()
    println(grade.average)
}

class Grade(
    val grades: Array<Float> = emptyArray()
) {
    init {
        if (grades.isEmpty()) throw IOException("에러 발생")
    }

    val n: Int = grades.size
    val maxGrade: Float
        get() = if (grades.isNotEmpty()) grades.max() else 0f

    val average: Float
        get() = if (grades.isNotEmpty()) grades.sum() / n else 0f

    fun convertGrade(): Unit {
        val maxScore = maxGrade

        for (i in 0..<n) {
            grades[i] = grades[i] * 100 / maxScore
        }
    }
}