package sample8;

public class Emp {
	
	// 상속 관계가 아니라 포함 관계까 된다.
	private String name;
	private int salary;
	
	public Emp() {
		super();
	}
	public Emp(String name, int salary) {
		super();
		this.name = name;
		this.salary = salary;
	}
	//emp를 매개변수로 받느다.
	@Override
	public String toString() {
		return "Name : " + name + ", Salary : " + salary + ", ";
	}
}
