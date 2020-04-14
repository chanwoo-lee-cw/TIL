type stringNumberFunc = (a: string,b: number) => void
// 타입은 매개변수를 (a: string,b: number)로 받고 리턴값이 void인 함수를 선언하겠다고 사전에 선언하는것
// 즉 함수의 시그니쳐, 함수의 구조를 선언해주는 역활
// 잘못된 매개변수를 선언하는 것을 막아주는 역활

let f : stringNumberFunc = function(a : string, b : number):void{}
let g : stringNumberFunc = function(x : string, y : number):void{}
// 매개변수를 선언 안 해 주어도 사용할땐 매개변수를 사용해야 한다.
let h : stringNumberFunc = function() {}
// 근데 그렇다고 매개변수를 안 넣으면 못씀
//let h : stringNumberFunc = function() {console.log(`name:${a}, age:${b}`)}
h('alpha',3)