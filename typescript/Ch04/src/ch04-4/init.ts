// 콜백 함수
const fcallback = (callback : () => void) : void => callback()

// 콜백 함수 매개변수로 받은 함수 표현식을 호출해 준다.
export const init = (callback:() => void) => {
    console.log('default initialization finished.')
    callback()
    console.log('all initialization finished')
}