package day9;

import day6.MethodLab3;

public class GuGuDan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num1 = MethodLab3.getRandom(20);
		int num2 = MethodLab3.getRandom(20);
		if (num1 < 10 && num2 < 10) {
			GuGuDanExpr dual = new GuGuDanExpr(num1, num2);
			System.out.print(num1 +  " * " + num2 + " = ");
			dual.printPart();
		} else if (num2 >= 10 && num1 < 10) {

			GuGuDanExpr single = new GuGuDanExpr(num1);
			single.printPart();
		} else {
			GuGuDanExpr.printAll();
		}
	}

}
