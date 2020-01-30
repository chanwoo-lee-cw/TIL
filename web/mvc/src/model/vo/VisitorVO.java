package model.vo;

public class VisitorVO {
	private String name;
	private String writeDate;
	private String memo;
	public String getName() {
		return name;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "VisitorVO [name=" + name + ", writeDate=" + writeDate + ", memo=" + memo + "]";
	}	
}
