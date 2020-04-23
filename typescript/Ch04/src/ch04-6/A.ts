//클래스 매서드
// function 키워드로 만든 함수에는 this사용 가능
// 반면에 화살표로 만든 함수에는 this 사용 불가

// 매서드란?
// 타입 스크립트에서의 매서는 function으로 만든 함수 표현식을 담고 있는 속성
// A는 value와 method라는 두개의 속성을 가짐.
// method는 ()=> voic 타입의 함수 표현식을 설정한다.


export class A {
    value : number = 1

    // 매개변수는 () 로 없기 때문에 뒤에 function 부분 에도()여야한다.
    // 리턴 타입은 void
    method : () => void = function (this : any) : void {
        console.log(`value : ${this.value}`)
    }
}