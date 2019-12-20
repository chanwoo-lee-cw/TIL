package day4;

public class CharacterTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char v1 = '가';
		char v2 = '\uAC00';
		char v3 = 44032;

		System.out.println(v1);
		System.out.println(v2);
		System.out.println(v3);	//가 가 나옴

		System.out.println((int) v1); // 44032 한글 가의 코드 값. 아스키 코드가 아니다.
		System.out.println((int) v2);

		System.out.println((double) v1);
		System.out.println((double) v2);

	}

}
