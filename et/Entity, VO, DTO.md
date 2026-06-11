# Entity, VO, DTO

## 들어가며

개발을 하다보면 데이터 클래스로 만드는 3가지의 클래스가 나온다. Entity와 VO와 DTO를 쓰다보다보면 관성적으로 이 위치에선 Entity, 여기선 VO, 여기서는 DTO라는 이름의 객체를 생성해서 사용하게 된다.

셋 다 데이터를 담는 객체지만 도대체 왜 이름을 나눠 쓰고, 구별해서 쓰는 것인지에 대해 한번쯤 짚고 넘어가는게 좋을거 같아서 작성하게 되었다.



## Entity

>식별자(ID)로 구분되고, 시간에 따라 상태가 변하는 객체

엔티티는 고유한 식별자(보통 ID)로 구분되는 객체이다. 데이터 클래스 안의 다른 속성이 바뀌어도 ID가 같으면 같은 객체이고, 생성부터 소멸까지 상태가 변하는 생명주기를 갖는다.

```kotlin
@Entity
class Person(
    @Id @GeneratedValue
    val id: Long? = null,
  	var name: String,
  	var age: Int,
)
```

만약 상태가 `status = INIT`에서 `status = PAID`로 바뀌어도 동일한 주문이다. 동등성도 ID로만 판단한다. 이름과 나이가 같은 사람이 2명 있더라도 다른 사람인 것처럼, 값이 아니라 식별자의 동등성이 중요하다.

```kotlin
val person1 = Person(1, "Peter", 10)
val person2 = Person(1, "Peter", 11)

person1 == person2 // true

val person3 = Person(1, "Peter", 11)
val person4 = Person(2, "Peter", 11)

person3 == person4 // false
```

> [!IMPORTANT]
>
> 엔티티의 `equal`과 `hashCode`는 모든 필드가 아니라 식별자 기준으로 해야한다.



## VO(Value Object)

> 식별자 없이 값 자체가 정체성인 불변 객체

VO는 식별자가 없고, 가진 값들이 정체성인 객체를 말한다. 모든 속성이 같으면 같은 객체이고, 원칙적으로는 **불변 객체**이다.

```kotlin
@Embeddable
data class Money(
    val amount: Long,
    val currency: Currency,
) {
    operator fun plus(other: Money): Money {
        require(currency == other.currency) { "서로 다른 통화는 더할 수 없습니다." }
        return Money(amount + other.amount, currency)
    }
}
```

`Money(1000, KRW)`는 어디에서 만들고 사용하던 간에 언제나 동일한 1000원이다. `Money(5000, KRW)`객체를 만든다고 해도 1000원을 바꾸는게 아니라 5000원 객체를 새로 만드는 느낌이다.

### VO의 장점

1. **원시 타입의 과용으로 인한 컴파일 에러를 줄일 수 있다.**

```kotlin
fun charge(userId: Long, amount: Long) { ... }

// 옳은 코드
charge(1_000, 50_000)		// ID가 1000인 유저가 5만원을 결제하다.

// 에러가 있는 코드
charge(50_000, 1_000)		// ID가 50000인 유저가 1천원을 결제한다
```

두 값은 순서만 바뀌어도 대참사가 일어나게 된다.

```kotlin
@JvmInline 
value class User(val userId: Long)

@JvmInline 
value class Money(val amount: Long)

fun charge(user: User, amount: Money) { ... }

val user = User(1_000), 
val money = Money(50_000)

// 옳은 코드
charge(user, money)		// ID가 1000인 유저가 5만원을 결제하다.

// 에러가 있는 코드
charge(money, user)		// Error
```

아예 잘못된 데이터가 들어가는걸 컴파일 시점에서 막을 수 있다.

2. **검증 로직이 한곳에 모을 수 있다.**

```kotlin
@JvmInline
value class Email(val value: String) {
    init {
        require(value.contains("@")) { "잘못된 이메일 형식: $value" }
    }
}
```



## DTO(Data Transfer Object)

> 계층과 시스템 경계를 넘어 데이터를 옮기는 객체

Controller와 Service 사이처럼 시스템 혹은 계층간의 데이터 전달이 목적인 객체이다. 로직 없이 데이터를 담기만 한다.

```kotlin
data class CreateOrderRequest(
    val productId: Long,
    val quantity: Int,
)

data class OrderResponse(
    val orderId: Long,
    val status: String,
    val totalAmount: Long,
)
```



> [!NOTE]
>
> 단, valide 같은 검증 로직 자체는 허용되지만, 무언가를 처리하기 시작하는 비즈니스 로직은 넣지 않아야한다.