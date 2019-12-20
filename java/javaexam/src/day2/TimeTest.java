package day2;

public class TimeTest {
	public static void main(String[] args) {

		int time = 32150;

		System.out.print(time / 3600 + "시간" + (time % 3600) / 60 + "분" + (time % 3600) % 60 + "초");
	}

}
