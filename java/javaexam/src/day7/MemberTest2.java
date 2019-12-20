package day7;

class Book {
	String title;
	String author;
	int price;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	Book() {
		this("자바의 정석", "남궁성", 30000);
	}

	Book(String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}

	String getBookInfo() {
		return title + "\t" + author + "\t" + price;
	}
}

public class MemberTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Book book1 = new Book();
		Book book2 = new Book("Do it! 점프 투 파이썬", "박응용", 18800);
		Book book3 = new Book("알고리즘 문제 해결 전략", "구종만", 45000);
		Book book4 = new Book("퍼즐로 배우는 알고리즘 with 파이썬 ", "Srini Devadas", 18000);
		Book book5 = new Book("자바로 배우는 핵심 자료구조와 알고리즘", "앨런 B. 다우니", 144000);

		System.out.println(book1.getBookInfo());
		System.out.println(book2.getBookInfo());
		System.out.println(book3.getBookInfo());
		System.out.println(book4.getBookInfo());
		System.out.println(book5.getBookInfo());

	}

}
