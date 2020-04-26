import java.util.Scanner;

public class Main {
	public static int MAX(int a,int b) {
		if(a>b) {
			return a;
		}
		else {
			return b;
		}
	}
	public static void main (String args[]) {
		
		int maximum=0;
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		int[] glass = new int[10001];
		int[] dp1 = new int[10001];
		int[] dp2 = new int[10001];
		
		for (int i =0;i<n;i++) {
			glass[i]=in.nextInt();
		}
		
		for (int i =0;i<n;i++) {
			if(i==0) {
				dp1[i]=dp2[i]=glass[i];
			}
			else if(i==1) {
				dp1[i]=glass[i];
				dp2[i]=dp1[i-1]+glass[i];
			}
			else {
				dp2[i]=dp1[i-1]+glass[i];
				dp1[i]=MAX(dp1[i-2],dp2[i-2])+glass[i];
			}
		}
		System.out.println(MAX(dp1[n-1],dp2[n-1]));
	}
	
}