package main

type Deque struct {
	arr []int
}

func NewDeque() *Deque {
	deque := Deque{}
	deque.arr = []int{}
	return &deque
}

func (d Deque) front() int {
	return d.arr[0]
}

func (d Deque) back() int {
	return d.arr[len(d.arr)-1]
}

func (d Deque) popFront() (int, bool) {
	if len(d.arr) <= 0 {
		return 0, false
	}
	var output int
	output, d.arr = d.arr[0], d.arr[1:]
	return output, true
}

func (d Deque) popBack() (int, bool) {
	if len(d.arr) <= 0 {
		return 0, false
	}
	var output int
	d.arr, output = d.arr[:len(d.arr)-2], len(d.arr)-1
	return output, true
}

func (d Deque) insertFront(n int) {
	d.arr = append([]int{n}, d.arr...)
}

func (d Deque) insertBack(n int) {
	d.arr = append(d.arr, n)
}

func main() {
	d := NewDeque()

	d.insertBack(10)
	d.insertBack(9)
	d.insertBack(8)

	d.insertFront(10)
	d.insertFront(9)
	d.insertFront(8)
}
