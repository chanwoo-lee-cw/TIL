import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		int w, h;

		Scanner in = new Scanner(System.in);

		String[] line;

		while (true) {
			line = in.nextLine().split(" ");

			w = Integer.parseInt(line[0]);
			h = Integer.parseInt(line[1]);
			int matrix[][] = new int[h][w];
			if (w == 0 && h == 0)
				break;
			for (int i = 0; i < h; i++) {
				line = in.nextLine().split(" ");
				for (int j = 0; j < w; j++) {
					matrix[i][j] = Integer.parseInt(line[j]);
				}
			}
			int cnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (matrix[i][j] == 1) {
						cnt++;
						search(matrix, i, j, w, h);
					}
				}
			}
			System.out.println(cnt);
			cnt=0;
		}
	}

	private static void search(int[][] matrix, int i, int j, int w, int h) {
		if (0 <= i && i < h && 0 <= j && j < w) {
			if(matrix[i][j]==1) {
				matrix[i][j]=0;
				search(matrix, i-1, j-1, w, h);
				search(matrix, i, j-1, w, h);
				search(matrix, i-1, j, w, h);
				search(matrix, i+1, j+1, w, h);
				search(matrix, i, j+1, w, h);
				search(matrix, i+1, j, w, h);
				search(matrix, i-1, j+1, w, h);
				search(matrix, i+1, j-1, w, h);
			}
			else return;
		} else
			return;
	}

}