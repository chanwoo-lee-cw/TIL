# Kotlin inline 함수

1. map()

변환을 위해서는 가장 자주 쓰게 되는 함수
컬렉션 인자를 다른 값 혹은 타입으로 변환할 때 사용.
주로 DB에서 조회해온 model을 DTO로 변환할 때 자주 사용하게 된다.
```kotlin
data class Person(
    val age: Int,
    val name: String,
)

fun main() {
    val personList = mutableListOf<Person>()
    personList.add(Person(10, "Peter"))
    personList.add(Person(20, "Parker"))
    personList.add(Person(14, "Potter"))

    val personInfos = personList.map { item -> "${item.name}: ${item.age}" }
    for (personInfo in personInfos) {
        println(personInfo)
    }
}

```
```
Peter: 10
Parker: 20
Potter: 14
```

2. groupBy()

컬렉션 인자들을 묶어서 Map형태로 만들어준다.

```kotlin
data class Student(
    val grade: String,
    val name: String,
)

fun main() {
    val students = mutableListOf<Student>()
    students.add(Student("senior", "Peter"))
    students.add(Student("junior", "Parker"))
    students.add(Student("sophomore", "Potter"))
    students.add(Student("sophomore", "Harry"))
    students.add(Student("freshman", "Miles"))

    val gradeToStudent = students.groupBy { item -> item.grade }
    println(gradeToStudent)
}
```
```
{
    senior=[Student(grade=senior, name=Peter)],
    junior=[Student(grade=junior, name=Parker)],
    sophomore=[Student(grade=sophomore, name=Potter), Student(grade=sophomore, name=Harry)],
    freshman=[Student(grade=freshman, name=Miles)]
}
```


3. distinct()

중복 제거를 위해서 사용
DB에서 조회해 오는 경우 자주는 안 쓴다.

```kotlin
fun main() {
    val numbers = arrayOf(1, 2, 3, 4, 2, 2, 2, 5, 6, 2, 1, 4, 6, 1, 2)

    println(numbers.distinct())
}
```
```
[1, 2, 3, 4, 5, 6]
```

1. find()

일치하는 첫번째 값을 얻어오는 함수.
주로 Enum타입에서 원하는 조건을 가져오기 위해서 사용했다.
```kotlin
enum class Grade(
    val number: Int
) {
    FRESHMAN(1),
    SOPHOMORE(2),
    JUNIOR(3),
    SENIOR(4)
}

fun main() {
    println(Grade.entries.find { it.number == 2 })
}
```
```
SOPHOMORE
```