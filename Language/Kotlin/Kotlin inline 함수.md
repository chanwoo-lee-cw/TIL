# Kotlin inline 함수

## Sequence 함수

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

4. find()

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

5. flatMap()

중첩된 리스트를 평탄화 시키는 스트림
써본적은 없지만, 인상깊게 남은 시퀸스 함수라 정리

```kotlin
fun main() {
    val testList = listOf(
        listOf(1,2,3),
        listOf(2,3,4),
        listOf(3,4,5)
    )

    println(testList.flatMap { it })
}
```
```
[1, 2, 3, 2, 3, 4, 3, 4, 5]
```

6. filter()

시퀸스 함수가 나오면 필수적으로 나오는 기능.
콜렉션에서 조건에 맞는 요소만 남긴다.

```kotlin
fun main() {
    val testList = listOf(1,2,3,4,5,6,7,8,9,10)

    println(testList.filter { it % 2 ==0 })
}
```
```
[2, 4, 6, 8, 10]
```

7. distinct()

중복된 요소를 제거한다. 엄청 자주 쓰이긴 않지만, 종종 쓰이는 기능
set을 사용해서 바꿀때는 순서 보장이 필요 없는 경우고, distinct를 사용해 제거하는 경우는 순서 보장이 필요한 경우이다.
보통은 set을 쓰는게 더 보편적

```kotlin
fun main() {
    val testList = listOf(1, 2, 4, 2, 4, 6, 12, 4, 1, 4, 1, 5, 1, 3, 2)

    println(testList.distinct())
}
```
```
[1, 2, 4, 6, 12, 5, 3]
```

8. distinctBy()

객체 리스트에서 특정 값을 기준으로 중복을 제거하고 싶을때 사용한다.
각 중복 요소들의 첫번째 객체만 남긴다.

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

    println(students.distinctBy { it.grade })
}
```
```
[
    Student(grade=senior, name=Peter),
    Student(grade=junior, name=Parker),
    Student(grade=sophomore, name=Potter),
    Student(grade=freshman, name=Miles)
]
```

## Scope 함수


1. let()

가장 자주 쓰게되는 Scope 함수
객체가 null이 아닌 경우에 처리한다
Elvis 연산자와 사용한다.

```kotlin
data class Student(
    var grade: Int,
    val name: String,
)

fun main() {
    val student: Student? = Student(1, "Peter")

    student?.let { it.grade += 1 }
    println(student)
}
```
```
Student(grade=2, name=Peter)
```