// 제이 쿼리 같은 라이브러리는 객체의 메서드를 기어서 호출하는 방식의 코드 작성 가능
// 이것을 메서드 체인이라고 한다.

export class Calculator {
    constructor(public value : number = 0) {}
    add(value : number) {
        this.value += value
        return this
    }
    multiply (value:number) {
        this.value *= value
        return this
    }
}