// 이 의미는 (a:number)를 매개변수로 받고  (arg0: number) 넘버형을 매개변수로 받고 number을 리턴하는 함수 컨시스턴트를 리턴하는것을 호출한다는 뜻인데 
// 이 함수 컨시스턴트는 b를 매개변수로 받고 a+b의 값을 숫자형으로 리턴한다는 뜻이다.
export const highAdd = (a:number) : (arg0: number) => number => (b:number) :number => a+b
// 이것과 같은 것
export type NumberToNumberFunc = (arg0 : number) => number
export const dupleAdd = (a: number) : NumberToNumberFunc => {
    const _add: NumberToNumberFunc = (b:number) : number => {
        return a+b
    }
    return _add
}

// console.log(dupleAdd(1)(2))
// const highResult = highAdd(1)(2)
// console.log(highResult)

// // 이런식으로 매개변수를 일부만 주고 함수처럼 사용이 가능하다
// const beta = highAdd(1)
// console.log(beta(5))
// console.log(beta(3))

// const alpha = highAdd(1)
// console.log(beta(5))
// console.log(beta(3))