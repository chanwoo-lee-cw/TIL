package main

import (
	"fmt"
	"strings"
)

func main() {
	var dog []string

	dog = append(dog, `|\_/|`)
	dog = append(dog, `|q p|   /}`)
	dog = append(dog, `( 0 )"""\`)
	dog = append(dog, "|\"^\"`    |")
	dog = append(dog, `||_/=\\__|`)

	fmt.Println(strings.Join(dog, "\n"))
}
