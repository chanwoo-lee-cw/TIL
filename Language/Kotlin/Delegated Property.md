# Delegated Property

> Delegated Property(위임 프로퍼티)란, 특정 객체에 대한 프로퍼티의 set과 get 메서드의 호출을 위임하는 매커니즘을 제공한다.
> 즉, 접근자에 대한 기능을 위임한 프로퍼티라는 뜻이다.

파이썬에서는 `@value.setter`와 `@property` 를 사용하는 것과 작동상의 기능은 비슷하지만, 외부 객체에 위임한다와 내부 객체에서 로직을 작성하는 차이가 있다.

가장 대표적인 위임 객체로는
1. lazy
2. observable property

정도가 있다.

## lazy란?

> 변수를 처음 사용할 때, 초기화 하는 기능

즉, 변수를 객체가 할당되거나 그럴때 초기화 하는 것이 아닌, 나중에 초기화 시킨다는 뜻이다.

```kotlin
val answer: Int by lazy {
    println("Computing the answer to the Ultimate Question of Life, the Universe, and Everything")
    42
}

println("What is the answer?")
println(answer)
println("Come again?")
println(answer)
// https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/lazy.html 참고
```
```
What is the answer?
Computing the answer to the Ultimate Question of Life, the Universe, and Everything
42
Come again?
42
```

answer 객체가 처음으로 실행될 때 "Computing the answer to the Ultimate Question of Life, the Universe, and Everything" 문장이 실행되고, 42라는 값을 얻어오고
다음에 불러올때는 해당 문장이 실행되지 않는다.

즉, 변수를 사용하는 시점까지 해당 값을 초기화 하지 않는 다는 뜻이다.



## Observable properties

**Delegates.observable()**은 초기값과 수정을 위한 두 가지 인수를 가진다.

핸들러는 할당이 완료 된 후에 속성이 할당 될 때마다 호출 됩니다. 핸들러에는 할당되는 속성(prop), 이전 값(old), 새 값(new)의 매개변수를 가진다.



```kotlin
import kotlin.properties.Delegates

class User {
    var userName: String by Delegates.observable("<no name>") {
            prop, old, new ->
        println("${prop.name} : $old -> $new")
    }
}

fun main() {
    val user = User()
    user.userName = "first"
    user.userName = "second"
}
```

```
userName : <no name> -> first
userName : first -> second
```

즉, 유저 네임이 디폴트 값인 `<no name>` 으로 세팅되었고,  바뀔 때마다 로그를 찍는다.



## Delegating to another property

속성은 게터와 세터를 다른 속성으로 위임할 수 있다. 이러한 위임은 최상위 속성과 클래스 속성 모두에서 사용할 수 있다.

- 최상위 속성
- 같은 클래스의 멤버 또는 확장 속성
- 다른 클래스의 멤버 또는 확장 속성

문서의 내용은 이런데 잘 이해가 안가서 예시를 들어본다.



```kotlin
class Person {
    var name: String = "Peter"
}
```

기존의 Person이라는 객체의 name이라는 객체가 있었는데, 해당 객체의 이름 필드를 리펙토링 해야될 필요가 있다.

```kotlin
class Person {
    var userName: String = "Peter"

    var name: String
        get() = userName
        set(value) {
            userName = value
        }
}
```

그럴때 생각할 수 있는 내용이, 이런식으로 기존에 있던 name 필드를 덮어 씌우는 것이다.

이걸 요약한게

```kotlin
class Person {
    var realName: String = "Peter"

    var name: String by this::realName
}
```

이것이다.

즉, name 객체를 수정시 realName를 같이 수정하겠다는 뜻이다. 이 경우에 `@Deprecated`를 사용해 해당 부분 수정을 안내하는 방법도 있다.

```kotlin
class MyClass {
    var newName: Int = 0

    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var name: Int by this::newName
}
```












# 참고
- https://intotherealworld.tistory.com/36
- https://pluu.gitbooks.io/kotlin/content/d074-b798-c2a4-c640-c624-be0c-c81d-d2b8/delegated-properties.html
- https://kotlinlang.org/docs/delegated-properties.html#delegating-to-another-property