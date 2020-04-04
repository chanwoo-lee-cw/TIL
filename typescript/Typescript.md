# 타입스크립트

### 타입 스크립트의 변수

1. 수 타입 -number
2. 불리언 타입 - boolean
3. 문자열 타입 - string
4. 객체 타입 - object



### 변수 선언

#### let

```typescript
let 변수이름 [= 초기값]

// 타입 주석
let 변수이름 : 타입 [= 초기값]
```

자바 스크립트의 var같은 경우

변수를 선언하고 값을  넣는다



#### const

```typescript
const 변수이름 = 초기값

// 타입주석
const 변수이름 : 타입 = 초기값
```

반드시 초기값 명시.

변숫값이 변하지 않는다는 암시



```typescript
let n : number = 1
let b : boolean = true
let s : string = 'hello'
let o : object = {}

// 타입 추론
// 타입이 선언 되 있지 않으면 알아서 추측해서 넣는다.

let n = 1
let b = true
let s = 'hello'
let o = {} // object로 판단
```



양 쪽 모두 자바스크립트와의 호환을 위해 타입 주석 부분을 생략할 수 있다.



#### any

```typescript
let a : any =0
```

타입과 무관하게 어떤 종류의 값이라도 저장 가능



#### undefined

```typescript
let u : undefined = undefined
u = 1 // 에러 발생
```

변수를 초기화 하지 않으면 undefined 값을 가진다.

타입인 동시에 값

하지만 undefined로 선언하면 다른 타입의 값 저장하려면 에러 발생





### 템플릿 문자열

변수에 담긴 값을 조합해 문자열을 만들 수 있게 한다.

```typescript
//예시
let count = 10, message = 'Your count'
let result = '${message} is ${count}'
console.log(result)
```



## 객체와 인터페이스

### 인터페이스 선언문

```typescript
interface 인터페이스 이름 {
    속성 이름[?]: 속성타입[,...]
}
```

### 선택 속성 구문

선속 속성 : 있어도 되고 없어도 되는 속성

```typescript
interface IPerson2 {
    name : string
    age : number
    etc2? : boolean // 선택 속성
}
let good1 : IPerson2 = {name:'Jack', age : 32}
let good2 : IPerson2 = {name:'Jack', age : 32, etc:true}
// 둘 다 정상 작동한다.
```



#### 익명 인터페이스

interface 키워드도 사용하지 않고 인터페이스 이름도 없는 인터페이스 생성 가능

```typescript
let ai: {
    name : string
    age : number
    etc2? : boolean // 선택 속성
} = {name:'Jack', age : 32}
```



### 객체와 클래스

#### 접근 제어자

- class, private, public, protected, implements, extend

```typescript
class 클래스 이름 {
    [private | protected | public] 속성 이름[?] : 속성 타입[...]
}
```



#### 생성자

```typescript
class Person2 {
    constructor (public name : string, public age? : number){}
}
let jack2 : Person2('Jack', 32)
```



```typescript
class Person3 {
    name : string
    age? : number
    constructor (name : string, age? : number){
        this.name = name; this.age = age
    }
}
let jack2 : Person2('Jack', 32)
// Person2와 동일하게 작동된다.
// 생정자의 매개변수에 접근 제어자를 붙히면 클래스에 선언 된 것처럼 동작
```



#### 인터페이스 구현

```typescript
class 클래스 이름 implements 인터페이스 이름 {
    ...
}
```

여타 언어의 인터페이스와 똑같이 물리적인 속성은 따로 정의해둬야 한다.



```typescript
interface IPerson4 {
    name : string
    age? : number
}
class Person4 implemnts IPerson4 {
    name : string
    age? : number
}
```



#### 추상클래스

```typescript
abstract class 클래스이름 {
    abstract 속성이름 : 속성타입
    abstract 매서드 이름() {}
}
```

추상 클래스도 여타 객체지향 언오와 똑같이 객체 생성 불가

```typescript
abstract class Aperson5 {
    abstract name : string
    constructor(pulbic age? number){}
}
```



#### 클래스의 상속

```typescript
class 상속 클래스 extends 부모 클래스
```

여타 객체 지향 언어와 똑같다

부모 클래스의 생성자를 super 로 호출 가능



#### static 속성

```
class 클래스 이름 {
	static 정적 속성 이름 : 속성 타입
}
```

