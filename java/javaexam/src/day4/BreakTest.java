package day4;

public class BreakTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		boolean flag = false;
		gugudan : for (int dan = 1; dan <= 9; dan++) {
			for (int num = 1; num <= 9; num++) {
				if (dan * num > 30) {
//					flag = true;
					break gugudan;		//이런식으로 반복문에 이름 붙혀도 됨.
				}
				System.out.print(dan + "X" + num + "=" + dan * num + "\t");
			}
			System.out.println();
//			if (flag)
//				break;
		}
		System.out.println("구구단 출력 종료!!");
	}

}
