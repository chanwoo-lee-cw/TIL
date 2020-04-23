# 에러 목록



#### 'this' implicitly has type 'any' because it does not have a type annotation.

ch04-6에서 생긴 에러

```typescript
export class A {
    value : number = 1
    method : () => void = function () : void {
        console.log(`value : ${this.value}`)
    }
}
```

`${this.value}` : 부분에 빨간 줄이 그어지더니 저런 에러가 떴다.

아마 원인은 this를 못찾은게 원인, 즉, 파이썬이 self를 매개변수로 주듯이

this : any를 매개변수로 주니 해결이 되었다.

해결 된 이후의 코드

```typescript
export class A {
    value : number = 1
    method : () => void = function (this : any) : void {
        console.log(`value : ${this.value}`)
    }
}
```

