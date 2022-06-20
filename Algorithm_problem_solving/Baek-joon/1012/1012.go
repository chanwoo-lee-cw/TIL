package main

import (
	"bufio"
	"fmt"
	"os"
)

var Way = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

type Field struct {
	n, m, k int
	field   [][]int
}

func NewField(n, m, k int) *Field {
	f := Field{}
	f.n = n
	f.m = m
	f.k = k
	f.field = make([][]int, n)
	for i := 0; i < n; i++ {
		f.field[i] = make([]int, m)
	}
	return &f
}

/*
addCabbage set 1 at y,x
addCabbage y,x의 값을 1로 세팅한다
*/
func (f *Field) addCabbage(y, x int) {
	f.field[y][x] = 1
}

/*
get_cabbage_group return the number adjacent cabbage group
*/
func (f Field) get_cabbage_group() int {
	answer := 0 // 배추 그룹 묶의 수
	visited := make([][]bool, f.n)
	for i := 0; i < f.n; i++ {
		visited[i] = make([]bool, f.m)
	}
	for i := 0; i < f.n; i++ {
		for j := 0; j < f.m; j++ {
			if f.field[i][j] == 1 {
				f.dfs(visited, i, j)
				answer++
			}
		}
	}
	return answer
}

/*
dfs changes all adjacent 1 to 0 for 1 present at the location of (y,x).
dfs는 (y,x)의 위치에 존재하는 배추와 인접하고 있는 모든 배추를 모두 0으로 바꾼다.
*/
func (f *Field) dfs(visited [][]bool, y int, x int) {
	var queue [][]int // go는 queue가 존재하지 않아서 리스트로 선언
	visited[y][x] = true
	queue = append(queue, []int{y, x})

	var curr []int
	for len(queue) > 0 {
		curr, queue = queue[0], queue[1:] // 첫 배열만 빼고, 남은 부분으로 다시 배열 선언
		f.field[curr[0]][curr[1]] = 0
		for _, item := range Way {
			nextY := curr[0] + item[0]
			nextX := curr[1] + item[1]
			if nextY < 0 || nextY >= f.n || nextX < 0 || nextX >= f.m {
				continue
			} else if visited[nextY][nextX] || f.field[nextY][nextX] == 0 {
				continue
			} else {
				queue = append(queue, []int{nextY, nextX})
				visited[nextY][nextX] = true
			}
		}
	}
}

func main() {
	var t int
	fmt.Scan(&t)
	reader := bufio.NewReader(os.Stdin)

	for i := 0; i < t; i++ {
		var m, n, k int
		//fmt.Scanf("%d %d %d", &m, &n, &k)
		fmt.Fscanf(reader, "%d %d %d\n", &m, &n, &k)
		f := NewField(n, m, k)

		for j := 0; j < k; j++ {
			var y, x int
			//fmt.Scanf("%d %d", &x, &y)
			fmt.Fscanf(reader, "%d %d\n", &x, &y)
			f.addCabbage(y, x)
		}

		fmt.Println(f.get_cabbage_group())
	}

}
