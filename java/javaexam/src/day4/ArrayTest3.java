package day4;

public class ArrayTest3 {

	public static void main(String[] args) {

		int a1[] = { 3, 10, 2, 9, 5, 11, 12, 1 };

		int max = a1[0];

		for (int i = 0; i < a1.length; i++)
			if (a1[i] > max)
				max = a1[i];

		System.out.println("최대값 :" + max);

		int min = a1[0];

		for (int i = 0; i < a1.length; i++)
			if (a1[i] < max)
				min = a1[i];

		System.out.print("최대값 :" + min);

	}

}
