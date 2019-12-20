package day8;

public class SalaryExpr {

	int bonus;

	SalaryExpr() {
		this(0);
	}

	SalaryExpr(int bonus) {
		this.bonus = bonus;
	}

	int getSalary(int grade) {
		switch (grade) {
		case 1:
			return bonus + 100;
		case 2:
			return bonus + 90;
		case 3:
			return bonus + 70;
		case 4:
			return bonus + 60;
		}
		return 0;
	}

}