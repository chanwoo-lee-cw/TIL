package main

// 임시 저장 go 는 큐 구현 필요
import (
	"bufio"
	"container/list"
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

func (f *Field) addCabbage(y, x int) {
	f.field[y][x] = 1
}

func (f Field) get_cabbage_group() int {
	answer := 0
	visited := make([][]bool, f.n)
	for i := 0; i < f.n; i++ {
		visited[i] = make([]bool, f.m)
	}
	for i := 0; i < f.n; i++ {
		for j := 0; j < f.m; j++ {
			if f.field[i][j] == 1 {

			}
		}
	}
	return answer
}

func (f *Field) dfs(visited [][]bool, y int, x int) {
	queue := list.New()
	queue.PushBack([]int{y, x})
	visited[y][x] = true

	var curr []int
	for queue.Len() > 0 {
		curr = queue.Front()
		queue.Remove(curr)
		for _, item := range Way {
			nextY := curr[0] + item[0]
			nextX := curr[1] + item[1]
			if nextY < 0 || nextY >= f.n || nextX < 0 || nextX >= f.m {
				continue
			} else if visited[nextY][nextX] {
				continue
			}
		}
	}
}

func main() {
	var t int
	fmt.Scan(&t)

	{
		var m, n, k int
		reader := bufio.NewReader(os.Stdin)
		fmt.Fscanf(reader, "%d %d %d\n", &m, &n, &k)
		f := NewField(n, m, k)

		for i := 0; i < k; i++ {
			var y, x int
			fmt.Fscanf(reader, "%d %d\n", &x, &y)
			f.addCabbage(y, x)
		}
	}

}
