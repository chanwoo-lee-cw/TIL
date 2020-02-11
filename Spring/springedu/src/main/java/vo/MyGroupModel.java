package vo;

import java.util.ArrayList;

public class MyGroupModel {
	private String category;
	private ArrayList<MyModel> myModellist;
	public String getCategory() {
		return category;
	}
	public ArrayList<MyModel> getMyModellist() {
		return myModellist;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setMyModellist(ArrayList<MyModel> myModellist) {
		this.myModellist = myModellist;
	}
	@Override
	public String toString() {
		return "MyGroupModel [category=" + category + ", myModellist=" + myModellist + "]";
	}
	
}
