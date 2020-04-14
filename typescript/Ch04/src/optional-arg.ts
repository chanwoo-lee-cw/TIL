function fn(arg1: string, arg?:number) {console.log(`arg:${arg}`)}
// 물음표 변수는 선택적 매개변수
// 안 넣고 싶으면 안 넣어도 된다.

fn('hello',1)
fn('hello')