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
	return d.arr[d.size()-1]
}

func (d Deque) size() int {
	return len(d.arr)

}

func (d *Deque) popFront() (int, bool) {
	if d.size() <= 0 {
		return 0, false
	}
	var output int
	output, d.arr = d.arr[0], d.arr[1:]
	return output, true
}

func (d *Deque) popBack() (int, bool) {
	if d.size() <= 0 {
		return 0, false
	}
	var output int
	d.arr, output = d.arr[:d.size()-2], d.size()-1
	return output, true
}

func (d *Deque) insertFront(n int) {
	d.arr = append([]int{n}, d.arr...)
}

func (d *Deque) insertBack(n int) {
	d.arr = append(d.arr, n)
}

func (d Deque) findIndex(n int) int {
	output := -1
	for idx, item := range d.arr {
		if item == n {
			return idx
		}
	}
	return output
}

func (d *Deque) rotateLeft(n int) {
	var left, right []int
	left, right = d.arr[:n], d.arr[n:]
	d.arr = append(right, left...)
}

func (d *Deque) rotateRight(n int) {
	var left, right []int
	left, right = d.arr[:d.size()-n], d.arr[d.size()-n:]
	d.arr = append(right, left...)
}

func main() {
	d := NewDeque()

	d.insertBack(10)
	d.insertBack(9)
	d.insertBack(8)

	print(d.findIndex(8))

	d.insertFront(10)
	d.insertFront(9)
	d.insertFront(8)

	d.rotateLeft(2)
	d.rotateRight(3)

	print()
}
