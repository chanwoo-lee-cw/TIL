package day13;

import java.util.ArrayList;

public class ListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array = new int[] { 3, 4, 2, 5, 2, 3, 6, 7, 5, 7, 9 };

		CreateList chan = new CreateList();

		ArrayList<Integer> alpha = chan.convertLsit(array);

		for (int i = 0; i < alpha.size(); i++)
			System.out.println(alpha.get(i));
	}

}
