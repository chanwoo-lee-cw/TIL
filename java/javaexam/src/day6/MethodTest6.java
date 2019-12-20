package day6;

public class MethodTest6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("main() 수행시작");
		System.out.println(add(10,100));
		System.out.println(add(10,20,30));
		System.out.println(add(new int[]{10,20,30,40}));
		System.out.println(add(10,20,30,40));
		System.out.println(add(10,20,30,40,50,60,70));
		System.out.println("main() 수행끝");
	}
	
	static int add(int p1,int p2) {
		return p1+p2;
	}

	static int add(int p1,int p2, int p3) {
		return p1+p2+p3;
	}
	static int add(int... p) {
		int sum =0;
		for(int i=0;i<p.length;i++) sum += p[i];
		return sum;
	}
}
