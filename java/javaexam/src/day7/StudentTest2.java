package day7;

class StudentNew {
	String name;
	int age;
	String subject;
	StudentNew(String p1,int age) {
		name = p1;
		this.age =age;
		subject = "자바";
	}
	StudentNew(String p1,int age,String p3) {
		name = p1;
		this.age =age;
		subject = p3;
	}
	void printStudentInfo() {
		System.out.println(name+"학생은 나이가 "+age+"입니다.");
	}
	void study() {
		System.out.println(name+"학생은 "+subject+"과목을 학습합니다.");
	}
	void study(int hour) {
		System.out.println(name+"학생은 "+subject+"과목을 학습합니다.");
	}
}
public class StudentTest2 {
	public static void main(String[] args) {
		StudentNew st1 = new StudentNew("핸드폰", 10, "귀가");
		System.out.println(st1);
		System.out.println(st1.age);
		st1.study(2);
		StudentNew st2 = new StudentNew("둘리", 100, "scalar");
		System.out.println(st2.age);
		st2.study(2);
		StudentNew st3 = new StudentNew("핸드폰", 10);
		System.out.println(st3);
		System.out.println(st3.age);
		st3.study(2);
		
	}
}
