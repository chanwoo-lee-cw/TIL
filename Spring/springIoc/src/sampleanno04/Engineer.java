package sampleanno04;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// 객체 명을 명시 안했으므로 클래스 이름의 engineer라는 이름으로 생성된다.
public class Engineer {
	@Autowired
	@Qualifier("emp1")
	// Autowired되는 변수명을 직접 명시 할 수 있다.
	//@Resource(name="emp1")
	private Emp emp;
	private String dept;
	
	public Engineer() {
		super();
	}
	@Autowired
	// @Autowired는 변수에도 세터매서드이던간데 일반 매서드에도 가능하다. 예를 들면 아규먼트로 dept라는 스트링 객체를 전달한다.
	public void ddd(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return emp.toString()+"\n"+ "and works at the "+dept+" department.";
	}
}








