package day2;

public class SwitchTest4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int score = (int) (Math.random() * 101);

		switch (score / 10) {
		case 10:
		case 9:
			System.out.println(score + ": A등급");
			break;
		case 8:
			System.out.println(score + ": B등급");
			break;
		case 7:
			System.out.println(score + ": c등급");
			break;
		case 6:
			System.out.println(score + ": D등급");
			break;
		default:
			System.out.println(score + ": F등급");

		}
		System.out.println("수행종료");

	}
}
