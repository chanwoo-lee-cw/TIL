// function printMe(name : string, age: number): void {
//     console.log(`name:${name}, age:${age}`)
// }
let printMe: (name : string, age : number) => void = function(name:string,age:number):void{console.log(`name:${name}, age:${age}`)}
let play = printMe('alpha',3)
// 함수 시그니처 이런 식으로 함수를 사용하겠다고 선언하는 즉, 인터페이스 같은 것 같다.

console.log(play)