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

값을 얻거나 에러가 발생했을 때, 실패시 it 객체로 발생한 에러를 전달 받고, 마지막 람다값을 결과로 전달 받는다.
고전적인 방식의 try-catch 코드와 비슷하다.

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

```
123
```

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "123"
        str.toInt()
    }.getOrElse {
        321
    }
    println(result)		// Success(123) 이 아닌 내부 결과를 얻어온다.
}
```

```
123
```





### getOrNull()

값을 얻거나 실패하면 `null`을 반환.

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "hello"
        str.toInt()
    }.getOrNull()

    println(result)
}
```

```
null
```




### onSuccess {} / onFailure {}

실패와 성공 결과값을 나눠서 처리한다.

주로 성공했을 때나 실패했을때, 추가작업을 넣고 싶을때 사용한다.

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "123"
        str.toInt()
    }.onSuccess {value ->
        println(value)
    }.onFailure {exception ->
        println(exception)	// 123 출력
    }
  
  	println(result)			// Success(123) 출력
}
```
```
123
Success(123)
```

- 잘못 사용하는 예제

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

왜냐하면, onSuccess와 onFailure이 **반환하는 값은 블록**이지, 결과가 아니기때문이다.

- **주의 사항**

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "123"
        str.toInt()
    }.onSuccess { value ->
        println("Success: $value")
        // 예외를 던지면, 새로운 실패 상태가 됨
        throw RuntimeException("error")
    }.onFailure { exception ->
        println("Failure: $exception")
    }

    println(result)
}
```

```
Success: 123
Failure: java.lang.RuntimeException: error
Failure(java.lang.RuntimeException: error)
```

성공 블록 안에서 에러가 발생하는 경우 상태가 Error로 덮어 씌워지므로 주의하는 것이 좋다.



### fold {}

성공하고, 실패했을 때, **처리 + 결과 반환**을 하고 싶을 때 사용하는 체이닝.

성공했을 때와 실패했을 때 값을 다르게 처리하고 싶을때 주로 사용한다.

```kotlin
fun main() {
    val value = runCatching {
        "hello".toInt()
    }.fold(
        onSuccess = { it * 2 },
        onFailure = { -1 }		// default value
    )

    println(value)  // -1
}
```



### recover {}

에러 발생시, 회복하고  default 값을 선언해줄 때 사용

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "hello"
        str.toInt()
    }.recover {
        println(it)     // 에러를 프린드
        123 // exception 났을때 default 값이 된다.
    }

    println(result)
}
```

```
Success(123)
```



### recoverCatching {}

recover 과정에서 에러가 발생할 수 있을 때 사용하는 체이닝

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "hello"
        str.toInt()
    }.recoverCatching {
        println(it)     // 에러를 프린트
        "world".toInt() // exception 났을때 default 값이 된다.
    }.getOrNull()

    println(result)
}
```

```
null
```



### getOrDefault()

에러 발생시 default 값을 설정한다. 다른 작업 없이 오로지 default 값만 세팅

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "hello"
        str.toInt()
    }.getOrDefault(123)

    println(result)
}
```

```
123
```



### map() & mapCatching()

map : 성공한 경우 값을 변환한다.

mapCatching : `map()` 내에서도 예외가 발생할 수 있다면 `mapCatching()`을 에러 잡기 위해서 사용

비슷한 기능이니 묶어서 설명

```kotlin
fun main() {
    val result = runCatching {
        val str: String = "123"
        str.toInt()
    }.map{
      	it * 2
    }

    println(result)
}
```

```
246
```





## 비슷한 기능의 차이

### onSuccess vs fold vs getOrElse

- onSuccess / onFailure는 **로깅 등을 추가할 때 사용**하는것을 추천
- fold는 성공과 실패에 따라 **반환값을 다르게 설정**할 때 주로 사용.
- `getOrElse`는 **실패 시 기본값**을 제공할 때 사용한다.
- onSuccess / onFailure와 getOrElse는 같이 자주 사용하지만, fold 와 getOrElse는 같이 사용하지 않는다.

| 메서드         | 사용 목적                      | 반환값             |
| -------------- | ------------------------------ | ------------------ |
| `getOrElse {}` | 실패 시 기본값 설정            | 기본값             |
| `onSuccess {}` | 성공 시 작업 추가              | 원본 `Result` 유지 |
| `onFailure {}` | 실패 시 작업 추가              | 원본 `Result` 유지 |
| `fold {}`      | 성공/실패 시 각각 다른 값 반환 | 변환된 값          |

- 예제 1번

```kotlin
// onSuccess와 getOrElse
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

- 예제 2번

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



### getOrDefault() vs getOrElse {} vs recover {}

- getOrDefault()

오로지 default 값만 심플하게 할당한다
`getOrElse` 과 `recover` 은 람다라서 다른 로깅을 넣을 수 있거나 하는 것과는 다르게 오로지 실패시 default 값만 할당

```kotlin
fun main() {
    runCatching {
        "Hello".toString()
    }.getOrDefault(
        123
    )
}
```



- getOrElse {}

이름 그대로 getOrElse이다.

즉 내부의 결과를 얻어오거나 Else를 한다.

```kotlin
fun main() {
    val answer = runCatching {
        "Hello".toInt()
    }.getOrElse {
        123
    }

    println(answer)
}
```

```
123
```



- recover {}

에러에서 복구하고 runCatching 블록인 상태로 return 한다.

```kotlin
fun main() {
    val answer = runCatching {
        "Hello".toInt()
    }.recover {
        123
    }.onSuccess {
        println("it is success")
    }.onFailure {
        println("it is recover")
    }

    println(answer)
}
```

```
it is success
Success(123)
```



### getOrElse vs recover

이 2개의 차이는 위의 예제를 통해 비교하면 아래와 같다.

```kotlin
fun main() {
    val getOrElseAnswer = runCatching {
        "Hello".toInt()
    }.getOrElse {
        123		// 내부 결과 123을 얻어온다.
    }

    val recoverAnswer = runCatching {
        "Hello".toInt()
    }.recover {
        123		// Success(123)을 얻어온다
    }

    println(getOrElseAnswer)
    println(recoverAnswer)
}
```

```
123
Success(123)
```

즉, getOrElse 는 runCatching Block 결과를 가져오고, recover 은 runCatching 블록을 가져온다.
