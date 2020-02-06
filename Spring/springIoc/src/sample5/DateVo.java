package sample5;
public class DateVo {
	private String name;
	private String birth;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return name + "'s birthday : " + birth;
	}
}
