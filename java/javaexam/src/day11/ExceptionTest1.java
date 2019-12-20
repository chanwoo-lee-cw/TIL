package day11;

public class ExceptionTest1 {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		System.out.println("수행시작");
		try {
			int num1 = Integer.parseInt(args[0]);
			int num2 = Integer.parseInt(args[1]);
			int result = num1/num2;
			System.out.println("연산 결과 : "+result);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("프로그램 아규먼트를 2개 전달하세요!!");
		}catch(ArithmeticException e) {
			e.printStackTrace();
			System.out.println("프로그램 아규먼트는 0 이 아닌 값을 전달하세요");
			return;
		}catch(Exception e) {
			System.out.println("프로그램 아규먼트는 숫자로 전달하세요");
		}finally {
			System.out.println("항상수행");
		}
		
		System.out.print("수행끝");
	}

}
