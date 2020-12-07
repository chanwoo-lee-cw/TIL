import {highAdd,dupleAdd,NumberToNumberFunc} from './high-order'

let fn : NumberToNumberFunc = dupleAdd(1)
let fn2 : NumberToNumberFunc = highAdd(1)

console.log(fn(1))
console.log(fn2(2))