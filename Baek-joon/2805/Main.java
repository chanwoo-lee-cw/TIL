import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String[] line = in.nextLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		int[] tree = new int[n];
		line = in.nextLine().split(" ");
		for (int i = 0; i < n; i++) {
			tree[i] = Integer.parseInt(line[i]);
		}
		line = null;

		Arrays.sort(tree);

		int low = 0;
		int high = tree[n - 1];
		int ans = 0;
		while (high >= low) {
			long sum = 0;
			int mid = (low + high) / 2;
			for (int i = lower_bound(tree, 0, n, mid); i < n; i++) {
				sum += tree[i] - mid;
			}
			if (sum >= m) {
				ans = mid;
				low = mid + 1;
			} else
				high = mid - 1;

		}
		System.out.print(ans);
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
		return e;
	}
}