import {B} from './B'

// A와 구조는 똑같지만, 이건 B로 생성자로 디폴트 값이 1이였으니 2로 얼마든디 바꾸는 것이 가능하다
let b : B = new B(2)
b.methood()