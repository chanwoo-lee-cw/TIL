package lotto;

public class LottoMachine {

	private int[] nums;

	public LottoMachine() {
		nums = new int[6];
	}

	public void createLottoNums() {

		for (int i = 0; i < 6; i++) {
			nums[i] = day6.MethodLab3.getRandom(20);
		}
	}

	public void checkLottoNums() throws DuplicateException {

		for (int i = 0; i < 6; i++) {
			for (int j = i; j < 6; j++) {
				if (i == j)
					continue;
				if (nums[i] == nums[j])
					throw new DuplicateException();
			}
		}

	}

	public int[] getNums() {
		return nums;
	}

}
