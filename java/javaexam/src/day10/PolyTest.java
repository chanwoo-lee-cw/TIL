package day10;

public class PolyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printObjectInfo(new Object());
		printObjectInfo(new String("rksksek"));
		printObjectInfo("ABC");
		printObjectInfo(new java.util.Date());
		printObjectInfo(new java.io.File("c:\\temp"));
		printObjectInfo(new int[10]);
		printObjectInfo(new double[5]);
		printObjectInfo(new day7.Member());
		printObjectInfo(new Integer(100));
		printObjectInfo(100);		//java 5 AutoBoxing 구문
	}

	static void printObjectInfo(Object o) {
		if (o instanceof String) {
			System.out.println("문자열 객체 전달됨" + o.getClass().getName() + ((String)o).length());
		}
		System.out.println("전달된 객체의 클래스 명 :" + o.getClass().getName());
	}

}
