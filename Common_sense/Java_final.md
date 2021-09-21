# final

> the `final` keyword is only assign entity one time

`final`키워드는 엔티티를 단 한번만 할당합니다. 두번 이상 할당하려고 하면 에러가 뜨게 된다.

## variable

> To create constant variables

It is used to declare a value that is assigned only once, Immutable value such as a constant
Namely, the `final value` is set to `read-only`

한 번만 할당된 값을 상수 같은 변하지 않는 값으로 선언하는 데 사용됩니다.
즉, `final value`는 `read-only`로 설정됩니다.

### Detail

When a variable is declared with finalword, its value can't be modified constant. This means that you must initialize a final variables. If the final variable is a reference, the variable cannot be re-bound to reference another object, but internal state of object pointed by the reference variable can be changed.

## method

> Prevent Method Overriding

final method create the method that is no longer overriding.

Final method는 자식 클래스에서 오버라이딩을 하려고 할때 컴파일 오류가 발생한다.

## class

> Prevent inheritance

The final class limits the inheritance of the class.

Final 클래스는 클래스의 상속을 제한한다.

