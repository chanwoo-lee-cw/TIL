package day7;

class Product {
	String name;
	int balance;
	int price;

	Product() {
		this("듀크인형",5,10000);
//		this.name = "듀크인형";
//		this.balance = 5;
//		this.price = 10000;
	}
	Product(String name, int balance, int price) {
		this.name = name;
		this.balance = balance;
		this.price = price;
	}
	String getName() {
		return name;
	}
	int getBalance() {
		return balance;
	}
	int getPrice() {
		return price;
	}

}

public class ProductTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Product[] prod = new Product[5];
		
		prod[0]= new Product("고양이사료",2,8000);
		prod[1]= new Product("토끼 사료",5,15000);
		prod[2]= new Product("크릴 새우",10,30000);
		prod[3]= new Product("강아지사료",2,10000);
		prod[4]= new Product();
		printProd(prod);
	}
	
	static void printProd(Product[] prod) {
		for(Product data : prod)
			System.out.printf("%s \t %d \t %,d원\n",data.getName(),data.getBalance(),data.getPrice());
	}

}
