import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String[] line = in.nextLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int h = Integer.parseInt(line[1]);
		line = null;

		int[] stalagmite = new int[n / 2];
		int[] stalactite = new int[n / 2];
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0)
				stalagmite[i / 2] = in.nextInt();
			else
				stalactite[i / 2] = h - in.nextInt();
		}

		Arrays.sort(stalagmite);
		Arrays.sort(stalactite);

		int[] breaker = new int[h + 1];
		

		for (int i = 1; i <= h; i++) {
			breaker[i] = n / 2 - lower_bound(stalagmite, 0, n / 2, i) + lower_bound(stalactite, 0, n / 2, i);
		}

		Arrays.sort(breaker);
		
		if(n==0) 
			System.out.print(h);
		else {
			int temp = upper_bound(breaker, 0, n / 2, breaker[1]);
			int temp2 = lower_bound(breaker, 0, n / 2, breaker[1]);
			System.out.printf("%d %d", breaker[1], upper_bound(breaker, 0, n / 2, breaker[1])-temp2);
		}

	}

	static void printArray(int[] p2) {
		for (int data : p2)
			System.out.printf("%4d", data);
		System.out.printf("\n");
	}

	private static int upper_bound(int[] arr, int s, int e, int check) {

		int m;
		while (e - s > 0) {
			m = (s + e) / 2;
			if (arr[m] <= check)
				s = m + 1;
			else
				e = m;
		}
		return e + 1;
	}

	private static int lower_bound(int[] arr, int s, int e, int check) {

		int m;
		while (e - s > 0) {
			m = (s + e) / 2;
			if (m == e)
				return e;
			if (arr[m] < check)
				s = m + 1;
			else
				e = m;
		}
		return e + 1;
	}
}