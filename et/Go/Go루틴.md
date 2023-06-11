## 1. 개요

go 루틴이란 일반적인 쓰레드보다 가볍게 동시성 처리를 구현하기 위해 GO에서 지원하는 간이 쓰레드.

### 1.1. go 루틴의 특징

1. 일반적은 OS 쓰레드 보다 가벼움 : 만약 특정 로직이 OS 쓰레드가 메모리를 1MB를 쓴다면 Go 루틴은 2KB정도를 사용함으로써 더 가볍게 쓸 수 있다.
2. 쉽게 사용 가능하다 : 사용 하고 싶은 함수에 go를 붙히는 것만으로도 사용 가능하고, Go 1.5 버전부터는 자동으로 사용하고 있지 않은 CPU 코어에 할당하여 작동한다.(이전 버전은 `runtime.GOMAXPROCS( _coreNumver )`을 통해 사용 가능한 코어의 수를 직접 지정해주어야 한다.)
3. 채널이라는 데이터를 공유하고 동기화를 위한 큐가 존재한다. : 채널을 통해 각 Go 루틴 간에 데이터 전달이 가능하다.

## 2. 예제

### 2.1. 기본적인 예제

```go
package main

import (
	"fmt"
)

func addNumber(a, b int, channel chan int) {
	channel <- a + b
}

func main() {
	a := 1
	b := 2
	// 채널 생성
	channel := make(chan int)
	// go 루틴 실행
	go addNumber(a, b, channel)

	// 결과를 받을 때까지 wait
	msg := <-channel
	fmt.Println(msg)
}
```

```go
// 출력값
3
```

위 예제는 가장 기본적인 go루틴 예제로, a+b를 계산한 다음에 그것을 채널을 통해 전달받아 출력하는 예제.

주의할 점으로는 결과 값을 받을 채널을 같이 매개변수로 전달해줘야한다.

`channel <- a + b` 으로 계산한 결과를 보내고, `msg := <-channel`으로 계산한 결과를 받는다.

해당 루틴은 비동기로 실행되기 때문에 서브 쓰레드를 실행 다음에`channel := make(chan int)`를 만나면 메인 고루틴이 블록된다.

```go
package main

import (
	"fmt"
)

func addNumber(a, b int) (channel chan int) {
	channel = make(chan int)
	go func() {
		defer close(channel)
		channel <- a + b
	}()
	return channel
}

func main() {
	a := 1
	b := 2
	// go 루틴 실행
	addChannel := addNumber(a, b)

	// 결과를 받을 때까지 wait
	msg := <-addChannel
	fmt.Println(msg)
}
```

동일한 로직이지만 변형하여 아예 channel을 하나의 매소드로 묶고 채널을 리턴으로 받을 수도 있다.

### 2.2. 채널을 통해 반복문을 돌리기

```go
package main

import (
	"fmt"
)

func addNumber(a, b int, channel chan int) {
	fmt.Printf("%d+%d\n", a, b)
	channel <- a + b
}

func main() {

	// 채널 생성
	channel := make(chan int)
	// go 루틴 실행
	for i := 0; i < 3; i++ {
		go addNumber(i, i+1, channel)
	}

	// 결과를 받을 때까지 wait
	for i := 0; i < 3; i++ {
		msg := <-channel
		fmt.Println(msg)
	}
}
```

```go
// 출력값
2+3
5
1+2
0+1
3
1
```

각  go루틴은 동시에 실행되므로 전송 순서가 실행 순서와 일치하지 않는다.


### 2.3. 작동이 안되는 go 루틴

```go
package main

import (
	"fmt"
)

func printMsg(msg string, channel chan int) {
	fmt.Println(msg)
}

func main() {
	// 채널 생성
	channel := make(chan int)
	// go 루틴 실행

	go printMsg("test", channel)
}
```

```go
//출력값
```

go 루틴이 작동하기 전에 메인 루틴이 종료가 됬으므로 아무것도 하지 않는다.

```go
package main

import (
	"fmt"
)

func printMsg(msg string, channel chan int) {
	fmt.Println(msg)
}

func main() {
	// 채널 생성
	channel := make(chan int)
	// go 루틴 실행

	go printMsg("test", channel)
	// 메인 쓰레드가 종료 되면 안 되므로 sleep
	time.Sleep(1)
}
```

```go
//출력값
test
```

### 2.4. 메인 루틴에서 go 루틴으로 msg를 보내는 경우

```go
package main

import (
	"fmt"
	"time"
)

func printNumber(channel chan int) {
	msg := <-channel
	// 받은 값을 출력
	fmt.Println(msg)
}

func main() {
	// 채널 생성
	channel := make(chan int)
	// go 루틴 실행
	go printNumber(channel)

	channel <- 10
	// 메인 쓰레드가 종료 되면 안 되므로 sleep
	time.Sleep(1)
}
```

```go
// 출력값
10
```

### 2.5. Go 루틴으로 메세지를 보낸 다음에 계속 수정하는 경우

```go
package main

import "fmt"

type IntBox struct {
	Number int
}

func convertNumber(intBox *IntBox, channel chan int) {
	channel <- intBox.Number
	intBox.Number = 0
	channel <- intBox.Number
}

func main() {
	var intBox *IntBox
	intBox = &IntBox{
		Number: 2,
	}
	// 채널 생성
	channel := make(chan int)
	// go 루틴 실행
	go convertNumber(intBox, channel)
	
	fmt.Println(<-channel)
	fmt.Println(<-channel)
	fmt.Println(intBox.Number)
}
```

```go
// 출력
2
0
0
```


### 2.6. Go 루틴으로 보낸 메세지를 iterate 로 출력

```go
package main

import "fmt"

func sendMsg(channel chan int) {
	for i := 10; i < 20; i++ {
		fmt.Printf("channel : %d\n", i)
		channel <- i
	}
	// close가 필수
	close(channel)
}

func main() {
	// 채널 생성
	channel := make(chan int, 1)
	// go 루틴 실행
	go sendMsg(channel)

	for i := range channel {
		fmt.Printf("main : %d\n", i)
	}
}
```

```go
// 출력
channel : 10
channel : 11
channel : 12
main : 10
main : 11
main : 12
channel : 13
channel : 14
channel : 15
main : 13
main : 14
main : 15
channel : 16
channel : 17
channel : 18
main : 16
main : 17
main : 18
channel : 19
main : 19
```

`range`를 채널에 사용하면, 채널이 `close` 되기 이전까지 값을 계속해서 받아올 수 있다.(테스트에 사용한 CPU는 4코어)

즉, 채널이 close가 되지 않으면 해당 **고루틴은 계속해서 블록 상태**로 멈추게 된다.

### 2.7. 데드락이 발생하는 예제

```go
package main

import "fmt"

func printNumber(channel chan int) {
	fmt.Println("test")
}

func main() {
	// 채널 생성
	channel := make(chan int)
	// go 루틴 실행
	go printNumber(channel)
	channel <- 10
}
```

```go
// 출력값
test
fatal error: all goroutines are asleep - deadlock!
```

go 루틴이 값을 받지 않고 종료 되었으므로 main 루틴이 waiting이 계속 걸리게 되서 에러 발생

```go
package main

func printNumber(channel chan int) {
	channel <- 10
}

func main() {
	// 채널 생성
	channel := make(chan int)
	// go 루틴 실행
	go printNumber(channel)
	channel <- 10
}
```

```go
// 출력값
fatal error: all goroutines are asleep - deadlock!
```

서로가 값을 받지 않고 모든 루틴이 sleep 상태에 빠지게 되서 데드락

```go
package main

import (
	"fmt"
)

func printNumber(channel chan int) {
	fmt.Println("this is printNumber func")
}

func main() {
	// 채널 생성
	channel := make(chan int)
	// go 루틴 실행
	go printNumber(channel)

	msg := <-channel
	fmt.Println(msg)
}
```

```go
// 출력값
this is printNumber func
fatal error: all goroutines are asleep - deadlock!
```

채널 버퍼가 비어있는데 값은 받으려고 대기하고 있기 때문에 데드락