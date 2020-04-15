import {Person} from'./return-object'
// export type Person = {name: string, age : number}

// 이건 매개변수로 받은 객체를 Person객체에 넣는다는 뜻인듯.
const printPerson = ({name, age} : Person) : void => {
    console.log(`name : ${name}, age:${age}`)
}

printPerson({name:'Jack',age:10})