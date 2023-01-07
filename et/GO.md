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
