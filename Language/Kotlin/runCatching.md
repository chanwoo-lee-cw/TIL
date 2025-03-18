# runCatching {}

## 의미

코틀린 공식 문서에서 언급하는 runCatching의 의미는 아래와 같다.

> 지정된 함수 블록을 호출하고, 실행이 성공하면 해당 블록이 반환하는 결과를 반환합니다. 실행 중 발생한 모든 Throwable 예외를 캐치하여 실패로 캡슐화합니다.

출처 : https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/run-catching.html

즉, 해당 블록 내에서 정상적으로 처리가 됬으면 처리된 결과를, 에러가 났으면 에러 결과를 포함한 실패 상태를 반환한다는 것이다.(프로그램이 멈추지 않는다.)

또한 함수형 스타일로 에러를 처리하고 여러가지 체이닝을 걸 수가 있다.

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "hello"
        str.toInt()
    }

    println(result)
}
```
```
Failure(java.lang.NumberFormatException: For input string: "hello")
```

## Chaining

### getOrElse {}

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "hello"
        str.toInt()
    }.getOrElse {
        println(it)     // 에러를 프린드
        123 // exception 났을때 default 값이 된다.
    }

    println(result)
}
```

에러가 발생했을 때, 실패시 it 객체로 발생한 에러를 전달 받고, 마지막 람다값을 결과로 전달 받는다.
고전적인 방식의 try-catch 코드와 비슷하다.


### onSuccess{} / onFailure{}

실패와 성공 결과값을 나눠서 처리한다.

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "123"
        str.toInt()
    }.onSuccess {value ->
        println(value)
    }.onFailure {exception ->
        println(exception)
    }
}
```
```
123
```


단, 이런식으로는 작동되지 않는다.

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "123"
        str.toInt()
    }.onSuccess {value ->
        value
    }.onFailure {exception ->
        exception
    }

    println(result)
}
```
```
Success(123)
```

왜냐하면, onSuccess와 onFailure이 반환하는 값은 블록이지, 결과가 아니기때문
이렇게 쓰고 싶다면, runCatching/getOrElse 로 처리하는게 맞다.(위의 getOrElse 참고)

```kotlin
// onSuccess/onFailure 2번
fun main() {
    val value = runCatching {
        "hello".toInt()
    }.onSuccess {
        println("success")
    }.onFailure {
        println("fail")
    }.getOrElse { -1 }

    println(value)
}
```

### fold

하지만, runCatching/getOrElse 방식으로는 실패했을때만 처리되는 블록을 만들 수 있는데,
그런 식으로 성공하고, 실패했을때만 추가적으로 처리하는 코드를 넣고 싶으면 fold를 처리하는게 낫다.
다만, 이런식으로 단순 계산 말고 추가적으로 복잡한 처리는 권장하지 않는데, 왜냐하면 사이트 이펙트가 발생하는 경우 잡지 못할 가능성이 크기 때문, 로깅을 넣고 싶다면 반대로 `onSuccess/onFailure 2번` 코드 참고해서 그런식으로 짜는게 낫다.

```kotlin
fun main() {
    val value = runCatching {
        "hello".toInt()
    }.fold(
        onSuccess = { it * 2 },
        onFailure = { -1 }
    )

    println(value)  // -1
}
```