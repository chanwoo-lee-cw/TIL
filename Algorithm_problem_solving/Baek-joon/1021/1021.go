package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

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
	var n int
	var arr string
	var numList []string

	deque := NewDeque()
	reader := bufio.NewReader(os.Stdin)

	arr, _ = reader.ReadString('\n')
	arr = strings.Trim(arr, "\n")
	numList = strings.Split(arr, " ")

	n, _ = strconv.Atoi(numList[0])

	arr, _ = reader.ReadString('\n')
	arr = strings.Trim(arr, "\n")
	numList = strings.Split(arr, " ")

	for i := 0; i < n; i++ {
		deque.insertBack(i + 1)
	}

	answer := 0
	for _, item := range numList {
		num, _ := strconv.Atoi(item)
		index := deque.findIndex(num)

		if float32(index) < float32(deque.size())/2.0 {
			deque.rotateLeft(index)
			answer += index
		} else {
			deque.rotateRight(deque.size() - index)
			answer += deque.size() - index
		}
		deque.popFront()
	}
	print(answer)
}
