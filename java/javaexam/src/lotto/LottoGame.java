package lotto;
import day13.*;

public class LottoGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LottoMachine2 toto = new LottoMachine2();

		
		toto.createLottoNums();

//		try {
//			toto.checkLottoNums();
			int[] temp = toto.getNums();
			System.out.print("(");
			for (int i = 0; i < 10; i++) {
				System.out.print(temp[i]);
				if (i < 9)
					System.out.print(",");
			}
			System.out.print(")");
//		} catch (DuplicateException e) {
//			System.out.println(e.getMessage());
//		}
	}

}
