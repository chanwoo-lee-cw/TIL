package day2;

public class SwitchTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int month = (int) (Math.random() * 12) + 1;

		switch (month) { // 식이 올 수 있다. 식 : 변수, 연산식, 리턴 값이 있는 매서드의 호출식(int,String)
		case 12:
		case 1:
		case 2:
			System.out.print(month + " : 겨울");
			break;
		case 3:
		case 4:
		case 5:
			System.out.print(month + " : 봄");
			break;
		case 6:
		case 7:
		case 8:
			System.out.print(month + " : 여름");
			break;
		case 9:
		case 10:
		case 11:
			System.out.print(month + " : 가을");
			break;
		}
	}
}
