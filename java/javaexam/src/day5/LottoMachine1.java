package day5;

public class LottoMachine1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] lotto = new int[6];
		int ran = 0;

		toto: for (int i = 0; i < 6; i++) {
			ran = (int) (Math.random() * 45) + 1;

			if (i == 0)
				lotto[0] = ran;
			else {
				for (int j = 0; j < i; j++) {
					if (lotto[j] == ran) {
						i--;
						continue toto;
					}
				}
				lotto[i] = ran;

			}
		}
		for (int i = 0; i < lotto.length; i++) {
			System.out.print(lotto[i] + " ");
		}
	}

}
