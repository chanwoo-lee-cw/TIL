# Null

## Kotlin에서 nullable 변수

코틀린에서는 기본적으로 null이 들어갈 수 없게 되어 있다. -> `NullPointerException` 이 워낙 자주 발생하는 에러다 보니 해당 에러를 최대한 컴파일 시간에 잡을 수 있도록 하기 위해서 이다.

```kotlin
var number: Long? = null;

number = 1_000L;
```

하지만, null이 들어가야만 하는 변수가 몇개 있는데 이를 위해 `type?` 형식으로 지정하여 사용한다.





## null 체크

```kotlin
fun startWithA(str: String?) : Boolean {
  if(str == null) {
    return false
  }
  return str.startWith("A")
}
```

str이 A로 시작하지 않거나 null이면 False를 리턴한다.



반대로, 아예 null이 들어갈 수 없도록 할 수도 있는데

```kotlin
fun startWithANotNull(str: String) : Boolean {
  return str.startWith("A")
}
```

코틀린에서는 null이 가능한 타입을 완전히 다르게 취급하여,

String?인 변수를 String에 넣으려고 하면 에러가 발생한다.



예를 들면,

```kotlin
fun StrTypeCheck(str: String?): Boolean {
  // 이 코드는 컴파일 시간에 에러가 발생한다. 
  // 이유는 String?인 변수를 startWithANotNull의 String변수에 집어넣으려고 했기 때문에
  startWithANotNull(str)
}
```





## Safe Call

```kotlin
val str: String? = "Test"
str.length		// 에러
str?.length		// null이 아니면 실행하고, null이면 실행하지 않는다.
```

이렇게 확실하게 length를 지원하지 않는 형태면 컴파일 시간에 에러를 발생시켜서, 런타임 시간에 발생할 에러를 줄여준다.



## Elvis 연산자

```kotlin
fun startWithA(str: String?) : Boolean {
  return str?.startWith("A") ?: throw IllegalArgumentException("null은 A로 시작하는지 체크할 수 없습니다.")
}
```

null이 들어온다면 에러를 발생시킨다.

```kotlin
fun startWithA(str: String?) : Boolean {
  return str?.startWith("A") ?: false
}
```

null이 들어온다면 false를 리턴한다.



즉, 처리해야 하는 작업이 null이 들어왔을 경우에 ?: 뒤의 케이스를 실행한다.

```kotlin
fun TestCase(number: Long?): String {
  number ?: return 0
  ...
}
```

이런식으로 number가 숫자가 아니면 빨리 retrun 하는 방식으로도 사용할 수 있다.



## Not Null 단언

```kotlin
fun startWithA(str: String?) : Boolean {
  return str!!?.startWith("A")
}
```

안에 들어온 것이 null이 확실히 아닐 때 이런 케이스를 사용한다.

단, null이 들어온다면 NPE를 발생시키기 때문에 조심해서 써야 한다.