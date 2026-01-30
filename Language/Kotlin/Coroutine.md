# Coroutine

## 코루틴이란?

> 함수처럼 호출되지만 중간에 실행을 멈추고(suspend), 나중에 중단했던 지점부터 다시 실행(resume)할 수 있는 '협력적인 루틴'

스레드(Thread)와 달리 경량이며, 비동기 작업을 처리할 때 코드를 순차적인 코드처럼 작성하게 만들어 가독성을 높이고, 스레드 오버헤드 없이 효율적인 동시성을 구현하는 데 사용된다.



## 코루틴과 쓰레드

프로세스 :  컴퓨터에서 실행되고 있는 프로그램을 의미한다.

쓰레드 : 프로세스보다 작은 개념으로 프로세스에 소속되어 여러 코드를 동시에 실행할 수 있도록 해준다.

즉, 한 프로세스가 여러 개의 쓰레드를 가지고 있으면 멀티 쓰레드 환경이라고 한다.

코루틴 : 쓰레드보다 작은 개념이다. 코루틴은 중단되었다가 재개될 수 있기 때문에, 앞부분은 1번 쓰레드에 배전되고, 뒷 부분은 2번 쓰레드에 배정될 수 있다.



### Context Switching

- 프로세스
  - 프로세스는 각각의 독립된 메모리 영역을 가지고 있기 때문에, Context Switching시 힙과 스택 영역이 모두 교체되어야 한다.
  - 가장 무거운 Context Switching 
- 스레드
  - 프로세스내에 스택 영역을 갖고 있지만, 힙 영역을 공유하고 있기 때문에 실행이 변경되면 스택 영역만 교체된다.
  - 프로세스보단 가벼운 Context Switching
- 코루틴
  - 두 코루틴이 같은 쓰레드 위에서 실행될 수 있다.
  - 동일한 쓰레드에서 코루틴이 실행되면, 메모리를 공유하므로 Context Switching이 적다.
  - 여러 개의 코루틴이 한개의 스레드 위에서 번갈아 실행될 수 있기 때문에, 적은 쓰레드로도 동시성을 확보할 수 있다.
  - 쓰레드와는 다르게 코루틴 작업 흐름이 쓰레드에 종속되어있지 않는다.



## 코루틴의 핵심 특징

- 비동기 코드를 동기 구조처럼 작성한다.
  - 일반 함수처럼 작성하지만, 중단 및 재실행이 가능하므로 비동기 환경에서도 직관적인 코드 흐름 유지가 가능하다.
- 스레드를 블로킹하지 않는다.
  - `delay()` 같은 중단 포인트도 스레드를 차단하지 않고, 다른 코루틴을 실행한다.
- 경량성
  - 스레드 대신 코루틴 오브젝트 단위로 스케쥴링해 비용이 적다.



## **코루틴** **빌더와** Job

### runBlocking

`runBlocking` 함수는 새로운 코루틴을 만들고 루틴과 코루틴을 이어주는 역할을 한다. 이렇게 코루틴을 만드는 함수를 **코루틴 빌더**라고 한다.

단, `blocking`이라는 이름대로 해당 코루틴이 완료될 때까지 스레드를 블락시킨다. 

```kotlin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun testCoroutine() : Unit = runBlocking {
    printWithThread("START")
    launch {
        delay(2_000L)	// End가 출력되기까지 2초를 기다리는 동안 쓰레드가 블락된다.
        printWithThread("LAUNCH END")
    }
}

fun main() {
    testCoroutine()
    printWithThread("END")
}

fun printWithThread(str: Any?) {
    println("[${Thread.currentThread().name}] $str")
}
```

```
[main @coroutine#1] START
[main @coroutine#2] LAUNCH END
[main] END
```



### Job

`launch`는 `runBlocking`와 다르게 만들어진 코루틴을 결과로 반환하고, 이 객체를 이용해 코루틴을 제어할 수 있다.

```kotlin
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun testCoroutine(): Unit = runBlocking {
    printWithThread("START")
  	// launch라는 코루틴 빌더를 변수로 할당한다.
    val job = launch(start = CoroutineStart.LAZY) {
        printWithThread("Hello launch")
    }
    delay(2_000L)
    job.start()
}

fun main() {
    testCoroutine()
    printWithThread("END")
}

fun printWithThread(str: Any?) {
    println("[${Thread.currentThread().name}] $str")
}
```

`launch`를 코루틴 빌더로 사용할 때, `CoroutineStart.LAZY` 옵션을 주어서 코루틴을 지연 시작으로 변경하고,  `job.start()`를 직접 호출해서 직접 시작한다.





## 참고 문헌

- [https://kotlinlang.org/docs/coroutines-guide.html](https://kotlinlang.org/docs/coroutines-guide.html)
- [https://www.inflearn.com/course/2시간으로-끝내는-코루틴/dashboard](https://www.inflearn.com/course/2시간으로-끝내는-코루틴/dashboard)