# Companion

Kotlin에서는 static 대신 `Companion object`를 사용한다.



```kotlin
class Person(
    val name: String,
) {
    companion object {
        private const val default_intro: String = "안녕하세요."	// 컴파일 시점에 할당된다.
        private val default_end: String = "입니다."	// 런타인 시점에 할당된다.

        fun printInfo(name: String) {
            println(default_intro+name+default_end)
        }
    }
}


fun main() {
    Person.printInfo("김철수")
}
```



`static` 대신에 `companion object`라는 용어를 쓰는 이유는, 

1. Static은 클래스와 같이 사용되는 것이 아니라 독립적으로 사용되기 때문에 객체 지향에 어울리지 않기 때문, 반대로 `companion object` 클래스의 인스턴스와는 독립적으로 동작할 수 있지만 여전히 클래스의 일부로 취급되어서 정적 멤버를 사용할 수 있게 해준다.
   1. 즉, static은 같은 위치에 선언되어 있더라도 객체를 선언하고 접근을 하느냐 마느냐의 차이를 보여주는데, companion object는 물리적으로 분리된 공간에 있어서 객체 접근 방식의 차이를 보여준다.



## 객체로 사용

companion object 라는 이름답게 동반 객체도 하나의 객체로 간주된다. 때문에 이름도 붙힐 수 있고, interface를 구현할 수도 있다.

```kotlin
class Person(
    val name: String,
) {
    companion object Info: Log{
        private const val default_intro: String = "안녕하세요."	// 컴파일 시점에 할당된다.
        private val default_end: String = "입니다."	// 런타인 시점에 할당된다.

        fun printInfo(name: String) {
            println(default_intro+name+default_end)
        }

        override fun Logging() {
          	// Log를 상속하여 로깅을 출력하는 예제
            print("log")
        }
    }
}

fun main() {
  	// 두 결과는 같은 응답을 내놓는다.
    Person.printInfo("김철수")
    Person.Info.printInfo("김철수")
}
```

