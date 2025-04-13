# SOLID 원칙

객체 지향 프로그래밍(OOP)에서 가급적이면 지켜줘야했으면 하는 5가지 원칙의 약자
즉, 코드의 확장성과 재사용성을 높이고, 복잡도를 줄이기 위해 제안된 5가지 원칙이다.



## 단일 책임 원칙 (Single Responsibility Principle, SRP)

> **하나의 클래스(객체)는 단 하나의 책임만 가져야** 한다는 원칙

- 클래스는 언제나 하나의 기능을 가져야 하고, 가급적이면 각자 따로 나누는 것이 좋다는 원칙
- 예를 들면, 주문이 들어왔을 때 재고를 차감하는 부분과 구매자에게 메일을 보내는 부분을 분리하는게 낫다.
- 한 클래스가 다른 클래스의 기능을 지원한다면, 만약 이 클래스에 문제가 생기면 이 기능을 사용하는 다른 기능들도 문제가 생기 때문이기도 하고, 이 기능이 왜 이 클래스에 있는지 확인하기 어려워서 유지보수가 어려워진다.

```kotlin
class OrderService() {
    fun orderSubmit() {}
}


class OrderSubmitEmailSend() {
    fun getConfig() {}
    fun sendEmail() {}
}
```



## 개방 폐쇄 원칙 (Open Closed Principle, OCP)

> 클래스는 **확장에 대해 열려 있어야 하며, 수정에는 닫혀**있어야 한다는 원칙

- 기능 추가가 필요한 경우, 기존에 있는 기능은 최소로 수정하고, 확장을 통해 손쉽게 구현할 수 있어야 한다.
- 용어
  - 확장에 열려있다 - 변경 사항이 생겼을 때, 유연하게 코드를 추가함으로써 큰 힘을 들이지 않고 애플리케이션 기능을 확장 할 수 있다.
  - 변경에 닫혀있다 - 새로운 변경 사항이 발생했을때, 객체를 직접적으로 수정하는건 지양함
- 즉, 쉽게 말해 추상화, 인터페이스와 추상 클래스를 활용해 구현하라는 뜻이다.



```kotlin
class IamageResizer {
  fun isOverSize() {
    if(width > image.width && height > image.height) {}
    else if(maxWidth > image.width  && maxheight > image.height )
    // 여기에 비율로 계산하는 공식을 새로 추가하려면 if문을 새로 추가해야하고 관리하기 어려워진다.
  }
  
  fun resize() {
    if(width && height)
    else if (width || height)
    else if (maxWidth > image.width || maxheight > image.height )
  }
}
```



```kotlin
interface IamageResizerImpl {
  abstract fun isOverSize()
  
  abstract fun resize()
}


class IamageResizerBySize : IamageResizerImpl {
  	fun match() {
	    if(width && height) {}
    }
  	
    abstract fun isOverSize() {
	    if(width > image.width && height > image.height) {}
  	}
  
    fun resize() {
      ...
  	}
}

class IamageResizerByRatio : IamageResizerImpl {
  	fun match() {
	    if(width || height) {}
    }
  	
    abstract fun isOverSize() {
	    if(width > image.width || height > image.height) {}
  	}
  
    fun resize() {
      ...
  	}
}

class IamageResizerByMaxSize : IamageResizerImpl {
  	fun match() {
	    if(maxWidth || maxheight) {}
    }
  	
    abstract fun isOverSize() {
	    if(maxWidth > image.width || maxheight > image.height) {}
  	}
  
    fun resize() {
      ...
  	}
}
```







## 리스코프 치환 원칙 (Liskov Substitution Principle, LSP)

> **자식 클래스는 언제나 부모 클래스를 대체**할 수 있어야 한다는 원칙

- 쉽게 말하자면, 업캐스팅된 부모의 매소드를 사용해도 문제 없이 동작이 되어야 한다는 원칙
- 다형성을 이용하기 위한 원칙
- 예를 들면, 콜렉션을 상속 받아 만들어진 객체들은 뭐를 만들었던 간에 size로 크기를 확인할 수 있는 방식.

```kotlin
fun main() {
    val col: Collection<Int> = listOf(1,2,3)
    printSize(col)
}

fun <T> printSize(col : Collection<T>) {
    println(col.size)
}
```


### 예외 케이스
만약 예외 케이스가 자주 들어오게 된다면 어떻게 해야할까?

```kotlin
abstract class Bird {
    abstract fun fly();
    abstract fun eat();
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

모든 새는 날 수 있고, 먹을 수 있으니까. Bird 라는 추상 클래스 안에 `fly`와 `eat` 을 넣었다.

하지만, 만약 여기에 펭귄과 타조가 들어온다면 어떨까? 타조와 펭귄은 날 수가 없다.

하지만, 펭귄과 타조의 경우에 fly를 들어오게 된다면 Error를 발행하게 했을때는 나중에 문제가 생길 여지가 있다.

해결책

```kotlin
interface Flyable {
    fun fly();
}

abstract class Bird {
    abstract fun eat();
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

밑에서 언급할 **ISP**도 지키게 되고, 날 수 없는 새들도 문제 없이 Bird 로 취급할 수 있게 된다.



## 인터페이스 분리 원칙 (Interface Segregation Principle, ISP)

> 인터페이스는 언제나 사용하는 인터페이스만 의존해야한다는 규칙

- 큰 인터페이스 하나보다는, **작고 구체적인 인터페이스 여러 개**로 나누는 것이 좋다.
- 즉, 인터페이스는 각각 용도에 맞게 잘게 분리해야한다는 설계 원칙이다.
- SRP가 클래스의 단일 책임을 말다면, **ISP는 인터페이스의 단일 책임을 강조**.
  - 인터페이스를 사용하는 클라이언트를 기준으로 분리함으로써, **클라이언트의 목적과 용도에 적합한 인터페이스 만을 제공**하는 것이 목표.
- 요컨데, **사용하지 않는 기능이 많은 인터페이스는 나쁜 설계**
- 주의할 점으로는 **인터페이스는 가급적이면 한번 구성해 놓고 바꾸지 않는 것이 이상적**이다. 다만, 꼭 그런건 아니다. 필요하다 싶으면 분리하는게 정석
  - 이 인터페이스를 구현하는 클래스들마다 **구현하지 않는 기능이 늘어나고** 있다면 → 분리 시점.
  - 하나의 인터페이스가 **여러 책임(Roles)을 가지는 것처럼 보인다면** → 분리 시점.
  - 클라이언트 코드가 **필요 없는 기능을 보게 되는 순간** → 분리 시점.



### 예시

모든 사람은 자기 때문에 eat과 sleep이라는 기능을 넣었다.

```kotlin
interface Person {
    fun sleep()
    fun eat()
}


class Child :Person {
    override fun sleep() {}

    override fun eat() {}
}
```

하지만, 새로운 사람 카테고리에 직장인을 넣으려고 보니 직장인은 야근으로 자지 않는 경우가 많다.

이런식으로 쓰지않는 기능이 늘어난다면 안 좋은 인터페이스

```kotlin
import java.io.InvalidClassException

interface Person {
    fun sleep(time: Int)
    fun eat(food :String)
}


class Child : Person {
    override fun sleep(time: Int) {
        TODO("Not yet implemented")
    }

    override fun eat(food :String) {
        TODO("Not yet implemented")
    }
}


class Worker : Person {
    override fun sleep(time: Int) {
      	// 이런식으로 사용하지 않는 기능이 늘어난다면 나쁜 인터페이스
        throw InvalidClassException("Postgrad can't sleep")
    }

    override fun eat(food :String) {
        TODO("Not yet implemented")
    }
}
```





## 의존 역전 원칙 (Dependency Inversion Principle, DIP)

> 어떤 클래스를 사용하고 싶다면, 그 **클래스를 직접 참조하는게 아니라 상위 요소를 참조**하라는 원칙

- 구현 클래스를 직접 참조하는걸 비추천하고, 해당 인터페이스나 추상 클래스를 참조라하는 뜻이다.
- 즉, 구현보다는 추상화(인터페이스, 추상 클래스)에 의존하라는 뜻
  - 코드의 중요한 핵심 부분은 바뀌지 않아야한다.
  - 변화가 자주 일어나는 코드에서의 의존 관계를 없앤다. -> 즉, 자주 변경되는가를 따져서 추상화를 시켜야한다.



```kotlin
interface Person {
    fun sleep(time: Int)
    fun eat(food :String)
}


class Child : Person {
    override fun sleep(time: Int) {}

    override fun eat(food :String) {}
}

class Main{
    fun case1() {
        // 추천하지 않는 구조, 직접 넣는다.
        val child = Child()
    }

    fun case2(person: Person)  {
        // 추천되는 구조, 직접 넣는 대신 객체를 주입받아서 생성한다.
        val child = person
    }
}
```





# 참고

https://inpa.tistory.com/entry/OOP-💠-객체-지향-설계의-5가지-원칙-SOLID#

https://yoojin99.github.io/cs/Dependency-Inversion-Principle/