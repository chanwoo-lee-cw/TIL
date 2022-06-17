package main

import (
	"bufio"
	"fmt"
	"os"
)

func maxInt(a int, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

func main() {
	var t int
	var maxNum int = 0

	reader := bufio.NewReader(os.Stdin)
	fmt.Fscan(reader, &t)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	tesCase := make([]int, t)
	for i := 0; i < t; i++ {
		fmt.Fscan(reader, &tesCase[i])
		maxNum = maxInt(maxNum, tesCase[i])
	}

	numCnt := [][]int{{1, 0}, {0, 1}}
	for i := 2; i < maxNum+1; i++ {
		numCnt = append(numCnt, []int{numCnt[i-1][0] + numCnt[i-2][0], numCnt[i-1][1] + numCnt[i-2][1]})
	}

	for _, item := range tesCase {
		fmt.Fprintf(writer, "%d %d\n", numCnt[item][0], numCnt[item][1])
	}

}
