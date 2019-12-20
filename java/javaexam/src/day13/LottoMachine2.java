package day13;
import java.util.HashSet;
import java.util.Iterator;

public class LottoMachine2 {

	private HashSet<Integer> nums; 

	public LottoMachine2() {
		nums = new HashSet<>();
	}

	public void createLottoNums() {

		for (int i = 0; i < 10; i++) {
			if(!nums.add(day6.MethodLab3.getRandom(10,30))) i--;
		}
	}

//	public void checkLottoNums() throws DuplicateException {
//
//		for (int i = 0; i < 6; i++) {
//			for (int j = i; j < 6; j++) {
//				if (i == j)
//					continue;
//				if (nums[i] == nums[j])
//					throw new DuplicateException();
//			}
//		}
//
//	}

	public int[] getNums() {
		int num[] = new int[10];
		Iterator<Integer> iter = nums.iterator();
		int i=0;
		while(iter.hasNext()) {
			num[i++]=iter.next();
		}
		return num;
	}

}
