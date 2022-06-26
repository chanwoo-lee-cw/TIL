package main

import (
	"bufio"
	"fmt"
	"os"
	"time"
)

func main() {
	var hour, minute int
	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanf(reader, "%d %d", &hour, &minute)
	wantWake := time.Date(2000, 1, 1, hour, minute, 0, 0, time.UTC)
	needWake := wantWake.Add(-time.Minute * 45)
	fmt.Println(needWake.Hour(), needWake.Minute())
}
