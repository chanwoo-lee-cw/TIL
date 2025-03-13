# Kotlin inline 함수

## Sequence 함수

### 1. map()

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

### 2. groupBy()

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


### 3. distinct()

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

### 4. find()

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

### 5. flatMap()

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

### 6. filter()

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

### 7. distinct()

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

### 8. distinctBy()

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


### 1. let {}

가장 자주 쓰게되는 Scope 함수
**객체의 null 체크**와 함께 사용하고 싶을때 주로 사용한다.
객체에 접근할 때 `it`을 사용해서 접근한다.
람다의 마지막 값을 반환한다.
주로, Elvis 연산자와 사용한다.


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

### 2. run {}

객체를 `this`로 참조하고, 람다의 마지막 값을 반환한다.
람다의 마지막 반환한다.
어떤 값을 계산할 필요가 있거나 여러개의 지역 변수의 범위를 제한하고 싶을때 사용한다.
즉, 람다로 주로 사용하기 굉장히 좋은 scope함수

```kotlin
data class Student(
    var grade: Int,
    val name: String,
)

fun main() {
    val student = Student(grade = 1, name = "Peter")
    printStudent(student)
}

fun printStudent(student: Student) = student.run { println("name is ${this.name}, grade is ${this.grade}") }
```
```
name is Peter, grade is 1
```

### 3. apply {}

객체 그 자체를 반환할 일이 있을때 사용한다.
**초기화 시에나 validate 할때 주로 사용**하게 된다.

```kotlin
data class Student(
    var grade: Int,
    val name: String,
)

fun main() {
    // 결과가 끝난후에 업데이트 된 Student 객체를 그대로 반환
    val student = Student(grade = 1, name = "Peter").apply {
        this.grade += 1
    }
    println(student)
}
```
```
Student(grade=2, name=Peter)
```


### 4. with {}


Non-nullable 수신 객체이고 결과가 필요하지 않은 경우에만 with를 사용한다
범위 내에서는 해당 객체를 선언할 필요가 없기 때문에, 객체명을 지나치게 자주 호출해야하는 경우에 쓰기 좋다.

```kotlin
data class Student(
    var grade: Int,
    val name: String,
)

fun main() {
    val student = Student(grade = 1, name = "Peter")
    with(student) {
        println(grade)
        println(name)
    }
}
```
```
1
Peter
```

### 5. also {}

객체를 it으로 참조하고, 객체 자체를 반환한다.
전달 받은 객체를 변경할 일이 없거나, 로깅을 할 때 유용하다.

```kotlin
fun main() {
    val numbers = mutableListOf(1, 2, 3).also {
        println("초기 리스트: $it")
    }.apply {
        add(4)
    }
    println(numbers)
}
```
```
초기 리스트: [1, 2, 3]
[1, 2, 3, 4]
```


### etc. Scope함수의 주요 용도

> - let → 객체 변환, null 체크 후 작업이 필요할 때
> - run → 객체를 구성한 후 결과를 반환할 때
> - with → 특정 객체에 대해 여러 작업을 수행해야 할 때
> - apply → 객체를 설정하고, 다시 그 객체를 반환해야 할 때
> - also → 부가적인 작업(로깅, 디버깅)이 필요할 때

ChatGPT는 각각 스코프 함수의 용도를 이렇게 정리했다.
특히, `run`과 `with`, `apply`와 `also`는 용도가 비슷하니 확실하게 구분지어서 사용하는 편이 더 나을거 같다.