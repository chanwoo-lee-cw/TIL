# object

코틀린에서 사용하는 싱글톤 객체
즉, 런타임 시간에 단 1개의 인스턴스만 생성되고, 모든 접근 시도를 이 인스턴스에게 연결해서 준다.


## 특징

- 메모리: object는 첫 접근 시점에 생성되며, 프로그램의 생명주기 동안 인스턴스는 유지한다.
- 생성자: 별도의 생성자를 정의할 수 없습니다. 즉, object는 파라미터를 가진 생성자를 갖지 않습니다.
- 확장: 다른 클래스나 인터페이스를 상속하거나 구현할 수 있습니다.

## 용도

### 싱글톤

응용 프로그램 전체에 하나의 클래스 인스턴스만 존재해야하는 경우, 예를 들면 DB 커넥션 풀로써 주로 사용된다.

```kotlin
// DB 커넥션 풀예제
object DatabaseConnectionPool {
    private val pool = mutableListOf<String>()

    init {
        pool.addAll(listOf("Conn1", "Conn2", "Conn3"))
    }

    fun getConnection(): String {
        return pool.removeFirstOrNull() ?: throw RuntimeException("No connections!")
    }

    fun releaseConnection(conn: String) {
        pool.add(conn)
    }
}
```

```kotlin
// Spring Mapper의 인스턴스를 생성
@Mapper
interface OrderMapper {
    fun toDto(source: OrderRequest): OrderDto

    companion object {
        val INSTANCE: OrderMapper = Mappers.getMapper(OrderMapper::class.java)
    }
}
```


### 팩토리 메소드 생성

companion object 사용해서 클래스에 연결된 클래스 수준의 함수와 속성을 정의하여, 이러한 인스턴스의 생성 및 관리를 단순화
아래에 서술할 companion object 방식중 하나이다.

```kotlin
class ShopOrder(
    id: Long,
    price: Float,
    qty: Long
) {
    companion object {
        fun makeOrder(
            id: Long,
            price: Float,
            qty: Long
        ): ShopOrder {
            return ShopOrder(
                id = id,
                price = price,
                qty = qty,
            )
        }
    }
}
```

### 기존 클래스의 동작 임시 수정 (익명 하위 객체)

새로 클래스를 만들지 않고 특정 상황에서만 임시 동작을 바꾸고 싶을 때 사용한다.
`object : class()` 또는 `object : interface()` 를 사용해 익명 하위 클래스를 생성.
즉, 기존 기능은 그대로 냅두고 **특별한 경우 한번만** 다른 기능으로 바꿔 쓰고 싶을 때 사용하는 기능.

```kotlin
open class Person {
    open fun sayHello() {
        println("Hello Eveyone")
    }
}

fun koreanSayHello() {
    val koreanSyaHello = object : Person() {
        override fun sayHello() {
            println("여러분 안녕하세요.")
        }
    }

    koreanSyaHello.sayHello()
}
```

### 타입 안전 설계 - 익명 객체로 일회성 인터페이스 구현

`object : interface {}` 형식으로 익명 구현 객체를 생성. 타입 안전하게 인터페이스를 구현
즉, 인터페이스를 구현하고 싶은데, 한번만 쓰는 클래스라서 굳이 클래스를 만들 필요는 없는 경우, 이 경우 즉석에서 익명 객체로 인터페이스 구현해서 넘겨주는 방식.
일회용 핸들러, 콜백, 람다 대체 등에 유용 (ex. 버튼 클릭 등)
아래의 예제의 경우에는 click 이벤트는 매 이벤트 마다 다른 이벤트가 호출되기 때문에 매 이벤트를 일일히 만들어 주기는 어렵기 때문이다.

```kotlin
// 클릭 이벤트가 발생했을 때 사용하는 인터페이스
interface ClickListener {
    fun onClick()
}
```
```kotlin
// ClickListener를 상속 받은 인스턴스를 매개변수로 받는 객체
fun setClickListener(listener: ClickListener) {
    listener.onClick()
}
```
```kotlin
// setClickListener 메서드에 상속 받은 인스턴스를 즉석에서 생성해서 인스턴스로 전달
setClickListener(object : ClickListener {
    override fun onClick() {
        println("버튼이 클릭됨!")
    }
})
```
람다와는 다른 점, 인터페이스에 함수 하나만 있을 때만 사용 가능
위의 예제 같은 경우는
```kotlin
// SAM (Single Abstract Method) 인터페이스일 경우
setClickListener { 
    // onClick 메소드
    println("람다로 클릭!") 
}
```
이렇게도 전달 가능.


# companion object

Kotlin에서는 static 대신 `companion object`를 사용한다.
이름 그대로 (클래스와) 동행하는 object 라는 뜻이다.


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

> Static은 클래스와 같이 사용되는 것이 아니라 독립적으로 사용되기 때문에 객체 지향에 어울리지 않기 때문, 반대로 `companion object` 클래스의 인스턴스와는 독립적으로 동작할 수 있지만 여전히 클래스의 일부로 취급되어서 정적 멤버를 사용할 수 있게 해준다.
> 즉, static은 같은 위치에 선언되어 있더라도 객체를 선언하고 접근을 하느냐 마느냐의 차이를 보여주는데, companion object는 물리적으로 분리된 공간에 있어서 객체 접근 방식의 차이를 보여준다.



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

## @JvmStatic

Java에서 Kotlin `companion object`를 사용하려면 `@JvmStatic`을 붙혀야한다.

```kotlin
class ShopOrder(
    id: Long,
    price: Float,
    qty: Long
) {
    companion object {
        @JvmStatic
        fun makeOrder(
            id: Long,
            price: Float,
            qty: Long
        ): ShopOrder {
            return ShopOrder(
                id = id,
                price = price,
                qty = qty,
            )
        }
    }
}
```
```java
public class OrderService {
    public void getOrder() {
        ShopOrder.makeOrder(1, 100.0f, 10);
    }
}
```

만약 쓰지 않는다면

```kotlin
class ShopOrder(
    id: Long,
    price: Float,
    qty: Long
) {
    companion object {
        fun makeOrder(
            id: Long,
            price: Float,
            qty: Long
        ): ShopOrder {
            return ShopOrder(
                id = id,
                price = price,
                qty = qty,
            )
        }
    }
}
```
```java
public class OrderService {
    public void getOrder() {
        ShopOrder.Companion.makeOrder(1, 100.0f, 10);
    }
}
```

혹은

```kotlin
class ShopOrder(
    id: Long,
    price: Float,
    qty: Long
) {
    // companion object에 이름을 붙힌다.
    companion object Factory {
        fun makeOrder(
            id: Long,
            price: Float,
            qty: Long
        ): ShopOrder {
            return ShopOrder(
                id = id,
                price = price,
                qty = qty,
            )
        }
    }
}
```
```java
public class OrderService {
    public void getOrder() {
        ShopOrder.Factory.makeOrder(1, 100.0f, 10);
    }
}
```

이유는 코틀린 코드를 디컴파일 해본다면

```java
public final class ShopOrder {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);

   public ShopOrder(long id, float price, long qty) {
   }

   public static final class Companion {
      @NotNull
      public final ShopOrder makeOrder(long id, float price, long qty) {
         return new ShopOrder(id, price, qty);
      }

      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
```

이런식으로 자바 클래스 안에 inner static 클래스로 Companion 객체를 생성하는 것을 볼 수 있다.

# static과의 차이

|             | Kotlin `object`                           | Java `static`                                      |
| ----------- | ----------------------------------------- | -------------------------------------------------- |
| 기본        | **싱글톤 객체**. 즉, 본질은 **객체**이다. | 클래스 내부의 **정적 멤버**                        |
| 메모리 위치 | 런타임에 객체로 존재함                    | 클래스 로딩 시 한 번만 할당되는 메모리 영역에 할당 |
| 확장성      | 상속, 인터페이스 구현 가능                | 불가능                                             |



## 참고

- https://www.inflearn.com/course/java-to-kotlin/dashboard
- https://kotlinlang.org/docs/java-to-kotlin-interop.html#static-fields
- https://velog.io/@kej_ad/Kotlin의-object-및-companion-object-와-Java의-static-의-차이점