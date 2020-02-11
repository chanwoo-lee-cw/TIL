package vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class XmlVO {
	private List<MyModel> list;
	public List<MyModel> getList() {
		return list;
	}
	public void setList(List<MyModel> list) {
		this.list = list;
	}	
}
