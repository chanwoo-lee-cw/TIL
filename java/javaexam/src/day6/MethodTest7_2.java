package day6;

public class MethodTest7_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result =0;
		for(int i=0;i<args.length;i++)
			result += Integer.parseInt(args[i]);
		System.out.print(result);
	}

}
