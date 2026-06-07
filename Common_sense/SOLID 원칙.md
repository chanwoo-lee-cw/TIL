# SOLID 원칙

객체 지향 프로그래밍(OOP)에서 가급적이면 지켜줘야 하는 5가지 원칙의 약자.
즉, 코드의 확장성과 재사용성을 높이고, 복잡도를 줄이기 위해 제안된 5가지 원칙이다.

---

## 단일 책임 원칙 (Single Responsibility Principle, SRP)

> **하나의 클래스는 단 하나의 변경 이유(책임)만을 가져야** 한다는 원칙

- 여기서 "책임"은 단순히 "기능 하나"가 아니라 변경의 이유 를 뜻한다.
- 예를 들면, 주문이 들어왔을 때 **재고를 차감하는 부분**과 **구매자에게 메일을 보내는 부분**을 분리하는 게 낫다. 이 둘은 변경을 요청하는 주체가 다르기 때문이다.
- 한 클래스가 다른 책임까지 지원하고 있으면, 한쪽 변경이 다른 쪽을 깨뜨릴 수 있고, 이 기능이 왜 여기 있는지 파악이 어려워 유지보수가 힘들어진다.

```kotlin
class OrderService {
    fun orderSubmit() {}
}

class OrderSubmitEmailSender {
    fun getConfig() {}
    fun sendEmail() {}
}
```

---

## 개방 폐쇄 원칙 (Open Closed Principle, OCP)

> 클래스는 **확장에 대해 열려 있어야 하며, 수정에는 닫혀** 있어야 한다는 원칙

- 기능 추가가 필요한 경우, 기존 코드는 최소로 건드리고 확장을 통해 손쉽게 구현할 수 있어야 한다.
- 용어
  - **확장에 열려있다** — 변경 사항이 생겼을 때, 유연하게 코드를 추가함으로써 큰 힘을 들이지 않고 기능을 확장할 수 있다.
  - **변경에 닫혀있다** — 새로운 변경 사항이 발생했을 때, 기존 객체를 직접 수정하는 것은 지양한다.
- 쉽게 말해, **추상화(인터페이스/추상 클래스)** 를 활용해 구현하라는 뜻이다.

### 안 좋은 예 — 분기가 늘어날 때마다 기존 코드를 수정해야 함

```kotlin
class ImageResizer {
    fun isOverSize() {
        if (width > image.width && height > image.height) {}
        else if (maxWidth > image.width && maxHeight > image.height) {}
        // 비율로 계산하는 공식을 새로 추가하려면 if문을 또 추가해야 하고,
        // 이 클래스를 계속 수정하게 되어 관리가 어려워진다.
    }

    fun resize() {
        if (width != null && height != null) {}
        else if (width != null || height != null) {}
        else if (maxWidth > image.width || maxHeight > image.height) {}
    }
}
```

### 좋은 예 — 새 전략을 추가해도 기존 코드는 안 바뀜

```kotlin
interface ImageResizer {
    fun match(): Boolean
    fun isOverSize(): Boolean
    fun resize()
}

class ImageResizerBySize : ImageResizer {
    override fun match() = (width != null && height != null)
    override fun isOverSize() = (width > image.width && height > image.height)
    override fun resize() { /* ... */ }
}

class ImageResizerByRatio : ImageResizer {
    override fun match() = (width != null || height != null)
    override fun isOverSize() = (width > image.width || height > image.height)
    override fun resize() { /* ... */ }
}

class ImageResizerByMaxSize : ImageResizer {
    override fun match() = (maxWidth != null || maxHeight != null)
    override fun isOverSize() = (maxWidth > image.width || maxHeight > image.height)
    override fun resize() { /* ... */ }
}
```

새로운 클래스를 추가하더라도 기존 코드는 전혀 바꿀 필요 없다.

```kotlin
class ImageResizeService(private val resizers: List<ImageResizer>) {
    fun resize() {
        resizers.first { it.match() }.resize()
    }
}
```

---

## 리스코프 치환 원칙 (Liskov Substitution Principle, LSP)

> **자식 클래스는 언제나 부모 클래스를 대체**할 수 있어야 한다는 원칙

- 업캐스팅된 부모의 메서드를 사용해도 문제없이 동작해야 한다는 원칙.
- 다형성을 안전하게 쓰기 위한 원칙이다.
- 핵심은 단순히 "컴파일이 되느냐"가 아니라 **행위적 계약(behavioral contract)** 을 지키느냐다.
  - 자식은 부모보다 사전 조건(precondition)을 강화하면 안 된다.
    - 예) 부모가 1000 이상의 값을 받을 때, 자식은 1500이상의 값을 가지면 안된다.
  - 부모보다 사후 조건(postcondition)을 약화하면 안 된다.
    - 예) 부모의 리턴이 정렬된 것을 리턴할 때, 자식이 종종 정렬 안된 것을 리턴하면 안된다.
  - 부모가 던지지 않던 **새로운 예외를 던지면 안 된다.
    - 예) 부모 클래스가 예외를 안 던지는게 약속된 클래스일때, 상속된 자식이 예외를 던지면 안된다.

```kotlin
fun main() {
    val col: Collection<Int> = listOf(1, 2, 3)
    printSize(col)
}

fun <T> printSize(col: Collection<T>) {
    // 어떤 Collection 구현체든 size로 크기를 확인할 수 있다
    println(col.size) 
}
```

### 예외 케이스 — 부모에 없던 예외를 던지는 건 LSP 위반

```kotlin
abstract class Bird {
    abstract fun fly()
    abstract fun eat()
}

class Eagle : Bird() {
    override fun fly() { TODO("Not yet implemented") }
    override fun eat() { TODO("Not yet implemented") }
}

class Swallow : Bird() {
    override fun fly() { TODO("Not yet implemented") }
    override fun eat() { TODO("Not yet implemented") }
}
```

모든 새는 날고 먹으니까 `Bird`에 `fly`와 `eat`을 넣었다.

하지만 **펭귄과 타조**는 날 수 없다. 이때 `fly()`에서 예외를 던지게 만들면, 이건 단순히 어색한 게 아니라 **부모에 없던 예외를 자식이 던지는 것 = 사후 조건 위반 = LSP를 깨는 것**이다. `Bird`로 업캐스팅해서 `fly()`를 호출하는 모든 코드가 잠재적으로 터진다.

**해결책 — 날 수 있는 능력을 별도 인터페이스로 분리**

```kotlin
interface Flyable {
    fun fly()
}

abstract class Bird {
    abstract fun eat()
}

class Eagle : Bird(), Flyable {
    override fun fly() { TODO("Not yet implemented") }
    override fun eat() { TODO("Not yet implemented") }
}

class Swallow : Bird(), Flyable {
    override fun fly() { TODO("Not yet implemented") }
    override fun eat() { TODO("Not yet implemented") }
}

class Penguin : Bird() {
    override fun eat() { TODO("Not yet implemented") }
}

class Ostrich : Bird() {
    override fun eat() { TODO("Not yet implemented") }
}
```

아래에서 언급할 **ISP**도 지키게 되고, 날 수 없는 새들도 문제없이 `Bird`로 취급할 수 있게 된다.


---

## 인터페이스 분리 원칙 (Interface Segregation Principle, ISP)

> 클라이언트는 자신이 사용하지 않는 인터페이스에 의존하지 않아야 한다는 원칙

- 큰 인터페이스 하나보다, **작고 구체적인 인터페이스 여러 개**로 나누는 것이 좋다.
- SRP가 **클래스의** 단일 책임이라면, **ISP는 인터페이스의** 단일 책임을 말하는 원칙이다.
- **클라이언트 관점**에서 봐야한다. 인터페이스에 기능이 많은게 중요한거 아니라, **클라이언트가 안 쓰는 메서드에 의존하게 되는 것**이 문제다. 그래서 분리 기준도 구현체가 아니라 **클라이언트별 용도**로 나눈다.
- 요컨대, **사용하지 않는 기능이 많은 인터페이스는 나쁜 설계**
- 분리 시점 :
  - 구현 클래스마다 **구현하지 않는(빈) 메서드가 늘어난다면** → 분리 시점.
  - 하나의 인터페이스가 **여러 역할(Roles)을 갖는 것처럼 보인다면** → 분리 시점.
  - 클라이언트 코드가 **필요 없는 기능까지 보게 되는 순간** → 분리 시점.
  - 다만 인터페이스는 한번 정하면 잘 바꾸지 않는 게 이상적이므로, 위와 같은 부분이 걸리기 시작한다면 분리한다.

### 안 좋은 예 — 안 쓰는 기능을 억지로 구현

```kotlin
interface Bird {
    fun eat(food: String)
    fun fly(distance: Int)
}

class Swallow : Bird {
    override fun eat(food: String) { TODO("Not yet implemented") }
    override fun fly(distance: Int) { TODO("Not yet implemented") }
}

class Penguin : Bird {
    override fun eat(food: String) { TODO("Not yet implemented") }

    override fun fly(distance: Int) {
        // 이런 식으로 사용하지 않는(혹은 의미 없는) 기능이 늘어나면 나쁜 인터페이스
        throw UnsupportedOperationException("펭귄은 날 수 없습니다.")
    }
}
```

### 좋은 예 — 용도별로 분리

```kotlin
interface Eatable {
    fun eat(food: String)
}

interface Flyable {
    fun fly(distance: Int)
}

class Swallow : Eatable, Flyable {
    override fun eat(food: String) { TODO("Not yet implemented") }
    fun fly(distance: Int) { TODO("Not yet implemented") }
}

class Penguin : Eatable {
    override fun eat(food: String) { TODO("Not yet implemented") }
}
```

---

## 의존 역전 원칙 (Dependency Inversion Principle, DIP)

> 고수준 모듈은 저수준 모듈에 의존하면 안 되고, 둘 다 **추상화에 의존**해야 한다는 원칙

- 구현 클래스를 직접 참조하지 말고, 인터페이스나 추상 클래스를 참조하라는 뜻이다.
- 핵심은 **"누가 추상화를 소유하느냐"의 역전(Inversion)** 이다.
  - 전통적으로 의존은 고수준 → 저수준 방향으로 흐른다.
  - 추상화(인터페이스)를 **고수준 쪽(도메인)이 정의**하고, 저수준(인프라)이 그걸 구현하게 만들면 의존 방향이 **인프라 → 도메인으로 역전**된다.
    - 즉, 고수준(비즈니스 규칙)은 주문이 있으면 재고를 차감한다.가 전부일 때, 저수준은 다양한 이유로 지속해서 변경하게 된다.
- 변경이 자주 일어나는 부분(인프라 구현)에 핵심 로직(도메인)이 끌려다니지 않게 만드는 것이 목적이다.

### DIP vs DI — 자주 헷갈리는 부분

- **DIP**: "고수준이 저수준에 의존하지 말고 추상화에 의존하라"는 **설계 원칙**.
- **DI(의존성 주입)**: 그 의존성을 외부에서 넣어주는 **방법**(생성자 주입 등). DI를 써도 추상화에 의존하지 않으면 DIP가 아니다.

### 예시 — 레이어 간 의존 역전

```kotlin
// === 도메인 레이어 (고수준) ===
// 인터페이스를 도메인이 "소유"한다는 점이 핵심
interface OrderRepository {
    fun save(order: Order)
}

class OrderService(
    private val orderRepository: OrderRepository, // 추상화에 의존 (DIP) + 주입받음 (DI)
) {
    fun submit(order: Order) {
        orderRepository.save(order)
    }
}

// === 인프라 레이어 (저수준) ===
// 도메인이 정의한 인터페이스를 구현 → 의존 방향이 인프라 → 도메인으로 역전된다
class JpaOrderRepository : OrderRepository {
    override fun save(order: Order) { /* DB 저장 */ }
}
```

`OrderService`(고수준)가 `JpaOrderRepository`(저수준)를 직접 참조하지 않고, 도메인에 있는 `OrderRepository`에만 의존한다. 구현체를 `JpaOrderRepository`에서 다른 것으로 바꿔도 도메인 코드는 바뀌지 않는다.

---

# 참고

- https://inpa.tistory.com/entry/OOP-💠-객체-지향-설계의-5가지-원칙-SOLID
- https://yoojin99.github.io/cs/Dependency-Inversion-Principle/