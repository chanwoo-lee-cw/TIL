import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		int[] arr = new int[n];
		String[] line = in.nextLine().split(" ");

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(line[i]);
		}

		line[0] = in.nextLine();

		int m = Integer.parseInt(line[0]);
		int[] check = new int[m];
		line = in.nextLine().split(" ");

		for (int i = 0; i < m; i++) {
			check[i] = Integer.parseInt(line[i]);
		}
		Arrays.sort(arr);
		for (int i = 0; i < m; i++) {
			System.out.print(upper_bound(arr, 0, n, check[i])-lower_bound(arr, 0, n, check[i]) + " ");
		}

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
			if (arr[m] < check)
				s = m + 1;
			else
				e = m;
		}
		return e + 1;
	}
}