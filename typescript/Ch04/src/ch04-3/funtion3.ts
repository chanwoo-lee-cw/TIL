// 함수는 변하지 않으므로 const형식으로 선언하는게 바람직
// = 말고 ->로 함수를 함수 몸체를 선언 할 수 있다.

// 실행문 방식 몸통
// 결과를 reutrun 해야지만 알려준다.
const arrow1 = (a: number, b: number): number => {return a+b}
// 표현문 방식 몸통
// 함수 내부에 있는 일을 그냥 알려준다.
// 한 문장 짜리 싱행하는덴 이게 더 좋다.
const arrow2 = (a: number, b: number): number => a+b