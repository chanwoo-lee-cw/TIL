package day6;

public class MethodTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("main() 수행시작");
		printMyname(10,'#');
		System.out.println("main() 수행끝");
	}
	static void printMyname(int num, char deco) {
		for(int i=1;i<=num;i++)
			System.out.println(deco+"me");
	}

}
