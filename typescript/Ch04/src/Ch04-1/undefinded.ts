interface INameable {
    name : string
}

function getName(o: INameable) {
    // 만약 undefined가 매개변수로 전달 된다면 'unknown name'을 리턴한다.
    return o != undefined ? o.name : 'unknown name'
}

// 책에선 된다고 하는데 안된다. 수정되었나봐.
// let n = getName(undefined)
// console.log(n)
console.log(getName({name : 'Jack'}))