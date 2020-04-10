import java.util.Arrays;
import java.util.Scanner;

public class HW {
	
	//알고리즘 HW#1
	//201311746 이찬우

	public static void main(String agrs[]) {
		
		Scanner scan=new Scanner(System.in);
		
		int n = scan.nextInt();
		
		for(int j=0;j<n;j++) {
			
			int a = scan.nextInt();
			int [] A = new int[20000];
			
			for(int i=0;i<a;i++) {
				A[i]=scan.nextInt();
			}
		
			int high = search_Square(A,0,6);
		
			int high_width =0;
			int high_max_width=0;
			for(int i=0;i<A.length;i++) {
				if(A[i]>=high) high_width++;
				else
				{
					if(high_max_width<high_width) high_max_width = high_width;
					high_width=0;
				}
			}
			int high_square = high * (high_max_width);
			System.out.println(high_square);
		}

	}
	
	static int search_Square(int A[], int a, int b){
	   if(a==b) return A[a];
	   int min = (a+b)/2;
	   int left = search_Square(A,a, min);
	   int right = search_Square(A,min+1, b);
	   int low, high;
	   if(left < right) {
		   low = left;
		   high = right;
	   }
	   else {
		   low = right;
		   high = left;
	   }
	   int low_width = 0;
	   int low_max_width = 0;
	   for(int i=a;i<=b;i++) {
		   if(A[i]>=low) low_width++;
		   else 
		   {
			   if(low_max_width<low_width) {
				   low_max_width=low_width;
			   }
			   low_width=0;
		   }
	   }
	   if(low_max_width<low_width) low_max_width =low_width;
	   int low_square = low * low_max_width;
	   int high_width = 0;
	   int high_max_width = 0;
	   for(int i=a ; i<=b ; i++) {
		   if(A[i]>=high) high_width++;
		   else 
		   {
			   if(high_max_width<high_width) {
				   high_max_width=high_width;
			   }
			   high_width=0;
		   }
	   }
	   if(high_max_width<high_width) high_max_width = high_width;
	   int high_square = high * (high_max_width);
	   if(high_square>low_square) {
//		   System.out.println(high_max_width+"*"+high+"="+ high_square+" \t"+low_square);
		   return high;
	   }
	   else {
//		   System.out.println(high_max_width+"*"+high+"="+ high_square+" \t"+low_square);
		   return low;
	   }
	}
}
