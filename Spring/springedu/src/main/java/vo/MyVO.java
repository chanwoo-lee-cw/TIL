package vo;

public class MyVO {
	private int myNumber;
	private String myColor;
	public int getMyNumber() {
		return myNumber;
	}
	public void setMyNumber(int myNumber) {
		this.myNumber = myNumber;
	}
	public String getMyColor() {
		return myColor;
	}
	public void setMyColor(String myColor) {
		this.myColor = myColor;
	}
	@Override
	public String toString() {
		return "MyVO [myNumber=" + myNumber + ", myColor=" + myColor + "]";
	}	
}
