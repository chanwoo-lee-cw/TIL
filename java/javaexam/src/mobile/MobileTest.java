package mobile;

public class MobileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Mobile ltype = new Ltab("Ltab", 500, "ABC-01");
		Mobile otype = new Otab("Otab", 1000, "XYZ-20");

		printTitle();
		printMobile(ltype);
		printMobile(otype);

		System.out.printf("s\n[ 10분 충전 ]\n");

		ltype.charge(10);
		otype.charge(10);

		printTitle();
		printMobile(ltype);
		printMobile(otype);

		System.out.printf("\n[ 5분 통화 ]\n");

		ltype.operate(5);
		otype.operate(5);

		printTitle();
		printMobile(ltype);
		printMobile(otype);
	}

	public static void printMobile(Mobile mobile) {

		System.out.println(mobile.printMobile());
	}

	public static void printTitle() {

		System.out.printf("%-10s%10s%15s\n", "Mobile ", "Battery", "OS");
		System.out.printf("---------------------------------------\n");
	}
}
