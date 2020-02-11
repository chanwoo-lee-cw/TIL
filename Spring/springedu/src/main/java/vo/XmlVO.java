package vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
// 반드시 이 엘레먼트가 선언되어 있어야 한다.
// 첫글자를 소문자로 바꾼 형태로 root element로 삼는다.
// 직접 이름을 줄수도 있음. @XmlRootElement(name="XXXX")라고 하면 루트 엘레맨트가 원하는 이름으로 지정된다.
public class XmlVO {
	private List<MyModel> list;
	public List<MyModel> getList() {
		return list;
	}
	public void setList(List<MyModel> list) {
		this.list = list;
	}	
}
