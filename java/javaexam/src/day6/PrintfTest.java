package day6;
public class PrintfTest {
	public static void main(String[] args) {
		System.out.printf("테스트입니당\n");		// %n
		System.out.printf("%d %x %o %c\n", 100, 100, 100, 100);
		System.out.printf("%#x %#X %#o\n", 100, 100, 100);		//x가 대문자냐 소문자에 따라 0x가 대문자일지 소문자 일지 결정,o는 8진수
		System.out.printf("%c %c %c %c\n", '가', 'A', '!', '3' );	
		System.out.printf("%b\n", true);
		System.out.printf("%f %e\n", 100.0, 100.0);		//%e는 지수형태로 출력
		System.out.printf("%.2f\n", 123.5678);
		System.out.printf("|%s|\n", "자바");
		System.out.printf("|%10s|\n", "자바");		//15글자를 출력하고 싶을때는 그냥 10 무시되고 출력된다
		System.out.printf("|%-10s|\n", "자바");
		System.out.printf("%,d원\n", 1000000);		// 큰 숫자는 %d를 하면 알아서 100 단위로 끊는다.			
	}
}








