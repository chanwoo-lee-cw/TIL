package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type combination struct {
	combination [][]int
}

// newCombination return new combination struct
func newCombination(n int) *combination {
	com := combination{}
	com.combination = make([][]int, n+1)
	for i := 1; i < n+1; i++ {
		com.combination[i] = make([]int, i+1)
	}
	return &com
}

// makeCombination returns the number of cases where n is subtracted from r
// Must be n > r and n > 0
func (com *combination) makeCombination(n int, r int) int {
	if com.combination[n][r] != 0 {
		return com.combination[n][r]
	} else if n == r || r == 0 {
		com.combination[n][r] = 1
		return 1
	} else {
		com.combination[n][r] = com.makeCombination(n-1, r) + com.makeCombination(n-1, r-1)
		return com.combination[n][r]
	}
}

func main() {
	var t int
	var testList [][]int
	var answer []string

	reader := bufio.NewReader(os.Stdin)

	fmt.Fscan(reader, &t)
	// empty reader buffer
	reader.ReadString('\n')

	// declare 2-dimensional array
	testList = make([][]int, t)
	com := newCombination(30)
	for i := 0; i < t; i++ {
		testList[i] = make([]int, 2)
		fmt.Fscanf(reader, "%d %d\n", &testList[i][0], &testList[i][1])
		answer = append(answer, strconv.Itoa(com.makeCombination(testList[i][1], testList[i][0])))
	}

	// empty buffer
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	fmt.Fprint(writer, strings.Join(answer, "\n"))
}
