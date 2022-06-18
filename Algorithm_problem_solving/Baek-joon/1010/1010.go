package main

import (
	"fmt"
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
	var answer []string

	fmt.Scan(&t)

	com := newCombination(30)
	for i := 0; i < t; i++ {
		var a, b int
		fmt.Scan(&a, &b)
		answer = append(answer, strconv.Itoa(com.makeCombination(b, a)))
	}

	fmt.Println(strings.Join(answer, "\n"))
}
