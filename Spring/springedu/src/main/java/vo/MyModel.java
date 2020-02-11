package vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyModel {
	private String flowerName;
	private int num;
	private String id;
	
	public String getFlowerName() {
		return flowerName;
	}
	public void setFlowerName(String flowerName) {
		this.flowerName = flowerName;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "MyModel [flowerName=" + flowerName + ", num=" + num + ", id=" + id + "]";
	}	
	
}
