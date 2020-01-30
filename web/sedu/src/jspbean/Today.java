package jspbean;
import java.time.LocalDate;

public class Today {
	private int year;
	private int month;
	private int date;
	public Today() {
		year = LocalDate.now().getYear();
		month = LocalDate.now().getMonthValue();
		date = LocalDate.now().getDayOfMonth();
	}
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDate() {
		return date;
	}	
}
