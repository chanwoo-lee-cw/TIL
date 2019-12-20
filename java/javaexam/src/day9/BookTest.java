package day9;

class Book {

	String title;
	String author;
	private int price;

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
		this.price = Math.abs(price);
	}

	Book() {
		this("자바의 정석", "남궁성", 30000);
	}

	Book(String title, String author, int price) {
		this.title = title;
		this.author = author;
		setPrice(price);
	}

	String getBookInfo() {
		return title + "\t" + author + "\t" + price;
	}
}

public class BookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Book book = new Book();
		book.setPrice(20000);
		
		System.out.println(book.getPrice());

	}

}
