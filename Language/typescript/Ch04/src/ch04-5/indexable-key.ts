export type KeyValueType = {
    [key : string]: string
}

// 키에 키 값을 넣고 , value에 값을 넣어 자동으로 JSON형태로 만들어 반환한다.
export const makeObject = (key:string, value:string) : KeyValueType => ({[key] : value})

// 위에랑 똑같은 문장이다.
// export const makeObject= (key:string,value:string) : KeyValueType => {
//     const KeyValueType = {[key] :value}
//     return KeyValueType
// }

console.log(makeObject('name', `Jack`))
console.log(makeObject(`firstName`, `Jane`))