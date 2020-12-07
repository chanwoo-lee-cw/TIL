export class B {
    // 파이썬 처럼 생성자에서 선언하면 자동으로 초기화 해준다.
    constructor(public value: number = 1) {}
    methood() : void {
        console.log(`value: ${this.value}`)
    }
}