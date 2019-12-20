package day13;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HashMapLab1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<String, Integer> map = new HashMap<String, Integer>(5);
		Scanner in = new Scanner(System.in);
		String str = null;
		int num = 0;

		while (map.size() < 5) {
			System.out.print("나라이름을 입력하세요 : ");
			str = in.next();
			System.out.print("인구 수를 입력하세요 : ");
			num = in.nextInt();

			if (map.get(str) == null) {
				map.put(str, num);
				System.out.println("저장되었습니다.");
			} else
				System.out.println("나라명 " + str + "는 이미 저장되었습니다.");
		}
		System.out.println("5개가 모두 입력되었습니다.");
		System.out.print("입력된 데이터 : ");

		Iterator<String> iter = map.keySet().iterator();

		boolean falg = iter.hasNext();
		while (falg) {
			String key = iter.next();
			System.out.print(key + "(" + map.get(key) + ")");
			if (falg = iter.hasNext())
				System.out.print(",");
		}
	}

}
