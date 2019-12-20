package day8;

import day6.MethodLab3;

public class SaleryExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int month = MethodLab3.getRandom(12);
		int grade = MethodLab3.getRandom(4);

		SalaryExpr dobby;

		int resuelt;

		if (month % 2 == 0)
			dobby = new SalaryExpr(100);
		else
			dobby = new SalaryExpr();

		System.out.printf("%d월 %d등급의 월급은 %d입니다.", month, grade, dobby.getSalary(grade));
	}

}
