package test;

public class GoodsTest {
	public static void main(String[] args) {
		// "PRD-0001", "듀크컴퓨터", 10000, 'N' 으로 객체 생성
		// "PRD-0002", "턱시컴퓨터", 20000 으로 객체 생성
		// 생성된 객체 각각 getGoodsInfo()를 호출하고 리턴결과를 출력합니다.

		Goods no1 = new Goods("PRD-0001", "듀크컴퓨터", 10000, 'N');
		Goods no2 = new Goods("PRD-0002", "턱시컴퓨터", 20000);

		System.out.println(no1.getGoodsInfo());
		System.out.println(no2.getGoodsInfo());
	}
}
