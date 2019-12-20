package day11;

public class TVTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//	     SaleTV, RentalTV 클래스의 객체를 각각 생성한다.
//	     SaleTV 객체는 채널을 두 개 높인다.
//	     RentalTV 객체는 채널을 세 개 내린다.
//	     각 객체에 대하여 printAllTV()을 호출한다.
//	     Rentable 타입의 객체인 RentalTV 객체는 printRentalTV() 를 호출한다.

		SaleTV sale = new SaleTV(300000,"SALETV-1",40,1);
		RentalTV rental = new RentalTV(100000,"RENTALTV-10",42,1);
		
		for(int i=0;i<2;i++)
			sale.channelUp();
		for(int i=0;i<3;i++)
			rental.channelDown();
		
		printAllTV(sale);
		printAllTV(rental);
		
		printRentalTV(rental);
	}
	
	static void printAllTV(TV tv) {
	     // 아규먼트로 전달된 객체의 toString() 을 호출하여 각 상품의 정보를 출력한다.
	     // 아규먼트로 전달된 객체의 play() 를 호출한다.    
	     // tv 변수에 전달된 객체가 SaleTV 타입이면 sale() 메서드를 호출한다.  
		
		System.out.println(tv.toString());

		if(tv instanceof SaleTV)
			((SaleTV) tv).play();
		if(tv instanceof RentalTV)
			((RentalTV) tv).play();
		if(tv instanceof SaleTV)
			((SaleTV) tv).sale();
	}
	static void printRentalTV(Rentable tv) {
	     // 아규먼트로 전달된 Rentable 객체의 rent() 메서드를 호출한다.
		tv.rent();
	}


}
