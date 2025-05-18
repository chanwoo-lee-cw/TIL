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

### by this::someProperty

> 같은 클래스 안의 다른 프로퍼티에 위임한다.

```kotlin
class Person {
    var realName: String = "Peter"

    var name: String by this::realName
}
```

위의 이 예제이다. `Person` 객체 내부의 realName 를 읽고 쓴다는 뜻이다.



### by ::topLevelProperty

> 파일의 최상위 수준(top-level)에 선언된 변수에 위임한다.

```kotlin
var globalCount: Int = 0

class Counter {
    var count: Int by ::globalCount
}

```

현재 스코프 내부에 있는 `var` 형태의 top-level 변수에만 위임할 수 있다.

조건이 몇개가 있는데,

1. **var 프로퍼티에만 사용할 수 있다.** : `val`이 아닌 `var`인 이유는 위임받은 프로퍼티 값을 변경할 수 있어야 하기 때문이다.
2. **같은 파일 내부거나 import로 참조된 프로퍼티만 가능하다.** : `::변수명`으로 직접 참조할 수 있어야 하므로, **같은 파일**이거나 `import`로 참조되어야 한다.
3. **`top-level` 즉, 클래스 밖에 선언되어야 한다.** : 클래스/객체 내부가 아닌 패키지 수준의 변수여야한다.

물론, 전역 변수를 var로 선언해야하는 만큼, 한정된 상황(테스트 상황)같은 케이스에만 사용된다.



### by anotherInstance::prop

> 다른 클래스 인스턴스의 프로퍼티에 위임한다.



```kotlin
class PersonInfoDto {
    var country: String = "KR"
}

class PersonDto(infoDto: PersonInfoDto) {
    var userName: String = "Peter"
    var country: String by infoDto::country
}
```



클래스 내의 프로퍼티를 선언하되, 실제 값은 다른 인스턴스가 가진 프로퍼티의 값을 직접 읽고 쓸 수 있도록 위임한다.

클래스 내 프로퍼티는 포장하는 역할만 한다.



```kotlin
class PersonInfoDto {
    var country: String = "KR"
}

class PersonDto(infoDto: PersonInfoDto) {
    var userName: String = "Peter"
    var country: String by infoDto::country
}


fun main() {
    val personInfoDto = PersonInfoDto()
    val personDto = PersonDto(personInfoDto)

    println("Person country ${personDto.country}")
    personDto.country = "US"
    println("Person country ${personDto.country}")
    println("Info country ${personDto.country}")
}
```

```
Person country KR
Person country US
Info country US
```

즉, PersonDto내의 프로퍼티가 아닌 PersonInfoDto의 프로퍼티를 설정 및 수정하는 역할을 말한다.



다만, 주의할 점이 있다.

- 동기화 문제 : 위임 객체가 변격이 되면 위임 프로퍼티도 같이 수정되야 하므로 상태 관리가 어렵다.
- 디버깅의 어려움 : 어떤 객체가 어떤 값을 가지고 있는지 알아보기 어렵다.
- Var 프로퍼티만 사용한다. : 역시 getter setter가 필요하므로 by 가 공유하는 동일한 문제이다.



## Storing properties in a map

흔히 쓰이는 사례는 맵에 속성 값을 저장하는 것입니다. 

맵 인스턴스 자체를 위임된 속성의 위임자로 사용하는 방식으로, 이러한 방식은  JSON 구문 분석이나 기타 동적 작업 수행과 같은 애플리케이션에서 자주 사용된다.

```kotlin
class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

fun main() {
    val userMap = mapOf(
        "name" to "John Doe",
        "age"  to 25,
        "class" to 3,
    )

    val userDto = User(userMap)
    println(userDto.name)
    println(userDto.age)
}

// 출처 : https://kotlinlang.org/docs/delegated-properties.html#storing-properties-in-a-map
```

```
John Doe
25
```

맵을 클래스 형태로 위임해서 캐스팅 코드 없이 쉽게 처리할 수 있다.

```kotlin
// 일반적인 방식
val name = map["name"] as String
val age = map["age"] as Int
```

이런 방식 대신 가독성 좋게 처리할 수 있다.



이것은 읽기 전용 맵 대신 MutableMap을 사용하는 경우 var의 속성에도 적용됩니다:






# 참고
- https://intotherealworld.tistory.com/36
- https://pluu.gitbooks.io/kotlin/content/d074-b798-c2a4-c640-c624-be0c-c81d-d2b8/delegated-properties.html
- https://kotlinlang.org/docs/delegated-properties.html#delegating-to-another-property