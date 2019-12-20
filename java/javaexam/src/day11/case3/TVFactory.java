package day11.case3;

public class TVFactory {	// 객체 생성을 대신 해주는 것을 관례적으로 facrory라고 함
	public static TV getTV(String beanName){
		TV obj = null;
		if(beanName.equals("samsung")){
			obj = new SamsungTV();
		} else if(beanName.equals("lg")){
			obj = new LgTV();
		}
		return obj;
	}

}
