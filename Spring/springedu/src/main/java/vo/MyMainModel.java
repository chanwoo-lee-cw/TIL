package vo;

import java.util.ArrayList;

public class MyMainModel {
	private String year;
	private ArrayList<MyGroupModel> grouplist;
	public String getYear() {
		return year;
	}
	public ArrayList<MyGroupModel> getGrouplist() {
		return grouplist;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setGrouplist(ArrayList<MyGroupModel> grouplist) {
		this.grouplist = grouplist;
	}
	@Override
	public String toString() {
		return "MyMainModel [year=" + year + ", grouplist=" + grouplist + "]";
	}	
	
}
