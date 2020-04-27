import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String[] line = in.nextLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int c = Integer.parseInt(line[1]);

		int[] house = new int[n];
		for (int i = 0; i < n; i++) {
			house[i] = in.nextInt();
		}
		Arrays.sort(house);

		int low = 1;
		int high = house[n - 1] - house[0];
		int mid;
		int cnt;
		int pre = 0;
		int ans = 0;
		while (low <= high) {
			mid = (high + low) / 2;
			cnt = 1;
			for (int i = 0; i < n; i++) {
				if (i == 0)
					pre = 0;
				else {
					if (house[i] - house[pre] >= mid) {
						cnt++;
						pre = i;
					}
				}
			}

			if (cnt >= c) {
				ans = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		System.out.print(ans);
	}
}