export type Person = {name: string, age : number}

// export const makePerson =(name :string, age : number = 10) :Person => {
//     // 이름이 같은 객체가 있다면 자동적으로 넣어준다.
//     const person = {name, age}
//     return person
// }

// 위의 문장을 간결하게 한 형식 {} 객체를 객체로 인식하게 하려면 ()로 둘러 쌓아줘야 한다. 아니면 복합 실행문으로 본다.
export const makePerson = (name : string, age : number = 10) :Person => ({name,age})

console.log(makePerson('jack'))
console.log(makePerson('jack',33))