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

```
[main @coroutine#1] START
[main @coroutine#2] Hello launch
[main] END
```

`launch`를 코루틴 빌더로 사용할 때, `CoroutineStart.LAZY` 옵션을 주어서 코루틴을 지연 시작으로 변경하고,  `job.start()`를 직접 호출해서 직접 시작한다.



### cancel

현재 돌아가고 있는 코루틴을 취소한다.

```kotlin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun testCoroutine(): Unit = runBlocking {
    val job = launch {
        (1..5).forEach {
            printWithThread(it)
            delay(500)
        }
    }
    delay(1_000L)
    job.cancel()
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
[main @coroutine#2] 1
[main @coroutine#2] 2
[main] END
```



### Join

제어하고 있는 코루틴이 끝날 때까지 대기한다.

Join을 사용하지 않는 예시에 대해 작성한다.

```kotlin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun testCoroutine(): Unit = runBlocking {
    val job1 = launch {
        printWithThread("Job 1 : START")
        delay(1_000)
        printWithThread("Job 1 : END")
    }
    val job2 = launch {
        printWithThread("Job 2 : START")
        delay(1_000)
        printWithThread("Job 2 : END")
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
[main @coroutine#2] Job 1 : START
[main @coroutine#3] Job 2 : START
[main @coroutine#2] Job 1 : END
[main @coroutine#3] Job 2 : END
[main] END
```

![join 미사용](https://velog.velcdn.com/images/alphanewbie/post/453cc353-ba60-4361-bd03-ebb9ac71ccbf/image.png)

각각 코루틴에 딜레이가 걸려 있지만, Job1과 Job2가 함께 출력되는데, Job1에서 1초를 대기하는 동안 Job2를 처리하기 때문이다.



```kotlin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun testCoroutine(): Unit = runBlocking {
    val job1 = launch {
        printWithThread("Job 1 : START")
        delay(1_000)
        printWithThread("Job 1 : END")
    }

  	// job1이 끝날 때까지 기다린다.
    job1.join()
    
    val job2 = launch {
        printWithThread("Job 2 : START")
        delay(1_000)
        printWithThread("Job 2 : END")
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
[main @coroutine#2] Job 1 : START
[main @coroutine#2] Job 1 : END
[main @coroutine#3] Job 2 : START
[main @coroutine#3] Job 2 : END
[main] END
```



![join 사용](https://velog.velcdn.com/images/alphanewbie/post/238ae9c0-2b1a-468d-96ca-653ddfeef7a8/image.png)

join을 호출함으로써, job1이 끝날 때까지 기다리기 때문에 Job1과 Job2가 순차적으로 실행된다.



### async

```kotlin
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


suspend fun echoValue(value: Int): Int {
    // 계산 시간 가정을 위한 1초
    delay(1_000)
    return value
}

fun testCoroutine(): Unit = runBlocking {
    val time = measureTimeMillis {
        val job1 = async { echoValue(1) }
        val job2 = async { echoValue(2) }
        printWithThread(job1.await() + job2.await())
    }
    printWithThread("소요 시간 : $time ms")
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
[main @coroutine#1] 3
[main @coroutine#1] 소요 시간 : 1044 ms
[main] END
```

`async()`는 `launch()`와 동일하게 코루틴을 실행하는 함수지만, 결과값을 리턴한다는 것이 다르다.

`suspend`를 붙인 함수를 호출함으로써 여러 외부 자원을 동시에 호출해야하는 경우에서 지연 시간을 최대한으로 줄일 수 있다.



단, 주의 사항은 `CoroutineStart.LAZY`를 사용하는 경우 계산 결과를 기다리기 때문에 코루틴으로 얻을 수 있는 장점이 줄어들게 된다.

```kotlin
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


suspend fun echoValue(value: Int): Int {
    // 계산 시간 가정을 위한 1초
    delay(1_000)
    return value
}

fun testCoroutine(): Unit = runBlocking {
    val time = measureTimeMillis {
        val job1 = async(start = CoroutineStart.LAZY) { echoValue(1) }
        val job2 = async(start = CoroutineStart.LAZY) { echoValue(2) }
        // 지연 된 작업을 실행시킨다.
        job1.start()
        job2.start()
        printWithThread(job1.await() + job2.await())
    }
    printWithThread("소요 시간 : $time ms")
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
[main @coroutine#1] 3
[main @coroutine#1] 소요 시간 : 1085 ms
[main] END
```

즉, `start()`를 통해 함수를 먼저 시작해줘야, 코루틴으로써 동시에 사용할 수 있다.



## 코루틴의 취소

```kotlin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun testCoroutine(): Unit = runBlocking {
    val job1 = launch {
        delay(1_000L)
        printWithThread("This is Job1")
    }
    val job2 = launch {
        delay(1_000L)
        printWithThread("This is Job2")
    }
    delay(100L)
    job1.cancel()
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
[main @coroutine#3] This is Job2
[main] END
```

코루틴은 취소할 수 있지만, `Job`객체의 `cancel()` 함수를 이용해 취소 가능하지만,

취소 대상인 코루틴도 `delay()` 함수나 `yield()`와 같은 **suspend 함수**를 사용해 대기 상태여야 취소가 가능하다. 



```kotlin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun testCoroutine(): Unit = runBlocking {
    val job = launch {
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while (i <= 5) {
            if (nextPrintTime <= System.currentTimeMillis()) {
                printWithThread("This is Job1 : print $i")
                nextPrintTime += 100L // 1초 후에 다시 출력되도록 한다.
                i++
            }
        }
    }
    delay(300L)
    job.cancel()
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
[main @coroutine#2] This is Job1 : print 1
[main @coroutine#2] This is Job1 : print 2
[main @coroutine#2] This is Job1 : print 3
[main @coroutine#2] This is Job1 : print 4
[main @coroutine#2] This is Job1 : print 5
[main] END
```

예상대로라면 3까지 출력되고 끝내야 했지만, 5까지 정상 출력되는 것을 볼 수가 있다.

즉, busy Waiting을 이용한 방식의 대기인 경우에는 중단이 되지 않는다.



### 멀티 쓰레드 코루틴 취소

```kotlin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException


fun testCoroutine(): Unit = runBlocking {
    val job = launch(Dispatchers.Default) {
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while (i <= 5) {
            if (nextPrintTime <= System.currentTimeMillis()) {
                printWithThread("${i++} 번째 출력!")
                nextPrintTime += 100L // 1초 후에 다시 출력되도록 한다.
            }
            if (!isActive) {
                throw CancellationException()
            }
        }
    }
    delay(100L)
    printWithThread("취소 시작")
    job.cancel()
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
[DefaultDispatcher-worker-2 @coroutine#2] 1 번째 출력!
[DefaultDispatcher-worker-2 @coroutine#2] 2 번째 출력!
[main @coroutine#1] 취소 시작
[main] END
```

코루틴의 취소 시키는 방법은 코루틴 스스로 본인의 상태를 확인해 취소 요청을 받았을때 `CancellationException`를 던지는 방법이 있다.

- `Dispatchers.Default`
  - 코루틴을 다른 쓰레드에서 동작하도록 할당한다는 뜻이다.
- `isActive`
  - 코루틴 블록 안에서 `isActive`라는 프로퍼티를 통해 해당 코루틴이 활성화되어 있는지 확인 할 수 있다.
  - 취소 신호를 전달하기 위해서 다른 쓰레드에서 동작하고 있어야 한다.



## 코루틴의 예외 처리

### launch

```kotlin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun testCoroutine() : Unit = runBlocking {
    printWithThread("START")
    val job = CoroutineScope(Dispatchers.Default).launch {
        throw IllegalArgumentException()
        printWithThread("This is Coroutine1")
    }
    delay(1_000L)
    printWithThread(job)
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
[main @coroutine#1] "coroutine#2":StandaloneCoroutine{Cancelled}@64c7c9b8
[main] END

BUILD SUCCESSFUL in 3s
3 actionable tasks: 2 executed, 1 up-to-date
Exception in thread "DefaultDispatcher-worker-1 @coroutine#2" java.lang.IllegalArgumentException
	at MainKt$testCoroutine$1$job$1.invokeSuspend(Main.kt:10)
	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
```

`launch` 함수는 예외가 발생하자마자, 해당 예외를 출력하고 코루틴이 종료가 되는 걸 확인할 수가 있다.

### async

```kotlin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun testCoroutine() : Unit = runBlocking {
    printWithThread("START")
    val job = CoroutineScope(Dispatchers.Default).async {
        throw IllegalArgumentException()
        printWithThread("This is Coroutine1")
    }
    delay(1_000L)
    printWithThread(job)
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
[main @coroutine#1] "coroutine#2":DeferredCoroutine{Cancelled}@d44fc21
[main] END
```



하지만, async 함수에서는 예외가 발생하지만, 해당 예외에 대해 처리하지 않는다.

이는 값을 반환하는 코루틴에 사용되기 때문에 예외 역시 반환 할 때 처리 할 수 있도록 설계된 것이다.



```kotlin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun testCoroutine() : Unit = runBlocking {
    printWithThread("START")
    val job = CoroutineScope(Dispatchers.Default).async {
        throw IllegalArgumentException()
        printWithThread("This is Coroutine1")
    }
    delay(1_000L)
    job.await()     // 이때 예외가 발생한다.
    printWithThread(job)
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
3 actionable tasks: 2 executed, 1 up-to-date
Exception in thread "main" java.lang.IllegalArgumentException
	at MainKt$testCoroutine$1$job$1.invokeSuspend(Main.kt:10)
	at _COROUTINE._BOUNDARY._(CoroutineDebugging.kt:46)
	at MainKt$testCoroutine$1.invokeSuspend(Main.kt:14)
Caused by: java.lang.IllegalArgumentException
	at MainKt$testCoroutine$1$job$1.invokeSuspend(Main.kt:10)
	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
```



## 참고 문헌

- [https://kotlinlang.org/docs/coroutines-guide.html](https://kotlinlang.org/docs/coroutines-guide.html)
- [https://www.inflearn.com/course/2시간으로-끝내는-코루틴/dashboard](https://www.inflearn.com/course/2시간으로-끝내는-코루틴/dashboard)