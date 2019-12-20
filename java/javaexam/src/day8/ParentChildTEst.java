package day8;

class Parent {	//java.lang.Object

	int x=1, y=2;
	public String toString() {
		return "Parent 클래스의 객체";
	}
	
}

class Child extends Parent {
	int x =10;
	
	void printInfo() {
		int x =100;
		System.out.println(x);			//100  이번 매서드 내에 x가 선언 된거 있으면 그게 출력된다
		System.out.println(this.x);		//10 자신의 x, 클래스의 인스턴스 변수
		System.out.println(super.x);	//1 부모의 x
		System.out.println(y);			// 2 브모의 y매서드 내에도 함수 내에도 없어서
		System.out.println(this.y);		// 2 부모늬 y 클래스의 인스턴스 함수ㅗ 없어서 부모로 부터 상속 받은
		System.out.println(super.y);	// 2 부모늬 y
	}
	
	public String toString() {
		return "Child 클래스의 객체";
	}
	
}

public class ParentChildTEst {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parent p =new Parent();
		System.out.println(p.toString());
		System.out.println(p);
		System.out.println("출력1"+p);
		// 즉 자동적으로 참조된 주소, 를 풀력한다.
		// 객체에 대한 정보를 하나의 문자열로 출력하는 것
		Card c =new Card();
		System.out.println(c.toString());
		System.out.println("출력2"+c.toString());
		java.util.Date d = new java.util.Date();
		System.out.println(d.toString());
		Child e =new Child();
		System.out.println("출력3"+e);
		e.printInfo();
//		System.out.println(e.super,y);
//		요런건 안됨, 본인 클래스 안에서만 된다.
	}

}
