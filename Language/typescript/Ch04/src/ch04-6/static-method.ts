// 매소드 앞에 static을 붙혀서 정적 메서드의 형태로 호출한다.

export class C {
    static whoAreYou() : string {
        return `I'm class C`
    }
}

export class D {
    static whoAreYou() : string {
        return `I'm class D`
    }
}

// 스태틱 메소드는 역시 그냥 클래스명으로도 호출 가능
console.log(C.whoAreYou())
console.log(D.whoAreYou())