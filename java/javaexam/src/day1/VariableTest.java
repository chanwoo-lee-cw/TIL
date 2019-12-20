package day1;

public class VariableTest {
	public static void main(String argsp[]) {
		System.out.println(1+2+3+4+5+10);	//25
		System.out.println(1+2+3+4+5-10);	//5
		System.out.println(1+2+3+4+5*10);	//60
		System.out.println(1+2+3+4+5/10);	//10
		
		System.out.println((1+2+3+4+5)+10);	
		System.out.println((1+2+3+4+5)-10);	
		System.out.println((1+2+3+4+5)*10);	
		System.out.println((1+2+3+4+5)/10);	
		
		int result = 1+2+3+4+5;
		System.out.println(result+10);	
		System.out.println(result-10);	
		System.out.println(result*10);	
		System.out.println(result/10);	
		
		char munja ='A';
		System.out.println(munja+munja);
		
		System.out.println(""+munja+munja);
	}
}
