package sampleanno04;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Engineer {
	@Autowired
	@Qualifier("emp1")
	//@Resource(name="emp1")
	private Emp emp;
	private String dept;
	
	public Engineer() {
		super();
	}
	@Autowired
	public void ddd(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return emp.toString()+"\n"+ "and works at the "+dept+" department.";
	}
}








