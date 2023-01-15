# Go

## 변수 선언

### 변수 선언

변수는 var 선언후 = 으로 값을 할당. 혹은 const를 통해 상수로 선언

혹은 := 으로 그대로 값을 할당하여 사용, 이경우 var 같은 선언자는 사용하지 않는다.

변수를 여러개 한꺼번에 선언하는 경우 () 으로 묶어서 선언 가능

```go
package main

func main() {
	num1 := 1
	var num2 = 2
	const PIE = 3.14
	var num3 int
	var (
		num4           = 4
		num5       int = 5
		num6       int
		num7, str1 = 6, "test"
	)
	num3 = 3
	num6 = 6
}
```

### 배열 선언

```go
package main

func main() {
	var arr1 []int
	var arr2 = []int{1, 2}
	var arr3 []int
	arr3 = []int{2, 3}
	arr4 := []int{3, 4}

	var arr5 [][]int
	arr5 = [][]int{{4, 5}, {5, 6}}
	var arr6 []int
	arr6 = append(arr6, 6)
	var arr7 [][]int
	for i := 0; i < 2; i++ {
		arr7 = append(arr7, []int{})
	}
	arr7[0] = append(arr7[0], 1)
}
```

## for

### for 문 레이블링

```go
func main() {
	var test [][]string = [][]string{{"val1", "val2"}, {"val3", "val4"}}
outer:
	for i, vals := range test {
		if i == 1 {
			break outer
		}
		println(i, vals[0])
	}
}
```

## 구조체

```go
package main

type Person struct {
	name string
}

func (person *Person) setName(name string) {
	person.name = name
}

func (person Person) printName() {
	println(person.name)
}

func main() {
	var person1 Person
	person2 := Person{}
	person1.setName("jim")
	person2.setName("beti")
	person1.printName()
	person2.printName()
}
```


## 에러 처리

```go
package main

import "log"

func test(raise_error bool) int {
	var val int
	val = 1
	log.Fatalln("This program needs exactly one argument")  // error raise
	return val
}

func main() {
	answer := test(true)
	println(answer)
}
```

```go
// error raised
2023/01/03 14:33:26 This program needs exactly one argument
```

```go
package main

import (
	"errors"
	"fmt"
)

func test(raise_error bool) (int, error) {
	var val int
	val = 1
	//log.Fatalln("This program needs exactly one argument")
	if raise_error {
		return 0, errors.New("test")
	}
	return val, nil
}

func main() {
	answer, err := test(true)
	if err != nil {
		// err 메세지 출력
		// 위의 log.Fatalln("This program needs exactly one argument") 주석이면 
		// 주석 : test
		// 주석 해제 : This program needs exactly one argument
		fmt.Println(err)
	} else {
		fmt.Println(answer)
	}
}
```

## 함수

### defer

특정 문장 혹은 함수를 나중에 (defer가 호출된 부분이 종료되기 직전에) 실행한다. finally 처럼 clean-up 작업을 위해 사용

```go
package main

import (
	"context"
	"fmt"
)

func doDbSomething(ctx context.Context) {
	tx, err := db.Begin(ctx, nil)

	defer func() {
		// 함수가 끝난 이후에 
		if err == nil {
			if err != nil {
				err = tx.Commit()
			} else {
				tx.Rollback()
			}
		}
	}()
}

func main() {
	answer, err := test(true)
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(answer)
	}
}
```