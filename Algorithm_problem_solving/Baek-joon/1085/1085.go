package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
)

/*
	param x,y is now positon.
	param w,h is rectangle size.
*/
type Rectangle struct {
	x, y int
	w, h int
}

/*
	Rectangle Generator/
*/
func newRectangle(x, y, w, h int) Rectangle {

	rectangle := Rectangle{}
	rectangle.x = x
	rectangle.y = y
	rectangle.w = w
	rectangle.h = h

	return rectangle
}

/*
	find and answer, after search nearest angle from x, y
*/
func findNearestAngle(rectangle Rectangle) int {
	var answer float64
	answer = math.Min(float64(rectangle.x), float64(rectangle.y))
	answer = math.Min(answer, float64(rectangle.w-rectangle.x))
	answer = math.Min(answer, float64(rectangle.h-rectangle.y))
	return int(answer)
}

func main() {
	var x, y, w, h int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanf(reader, "%d %d %d %d\n", &x, &y, &w, &h)

	rectangle := newRectangle(x, y, w, h)

	fmt.Println(findNearestAngle(rectangle))
}
