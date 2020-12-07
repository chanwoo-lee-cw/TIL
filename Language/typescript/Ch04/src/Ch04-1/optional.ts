interface IAgeable {
    age? : number
}

function getAge(o : IAgeable) {
    return o!= undefined && o.age? o.age:0
}

// 역시 안된다. 수정된 문자인듯.
// console.log(getAge(undefined))
// console.log(getAge(null))
console.log(getAge({age:32}))