package my.spring.springedu;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import vo.MyVO;
@Controller
public class TestModelController1 {
	// @ModelAttribute가 붙어 있는 것들은 시작할때 자동적으로 호출된다.
	// 컨트롤러 메소드 뿐만, 일반 메서드에도 지정가능
	// 일반 매서드에 붙으면, 객체에 생성되면 알아서 보관 된다.
	// 클라이언트가 호출하게 되면 각각의 매서드를 한번씩 호출되고 각자 보관
	// 1번 같은 경우는 TEST!!!라는 문자열을 v1이라는 이름으로 보관해준다.
	@ModelAttribute("v1")
	public String createString() {
		System.out.println("Creating an object automatically 1");
		return "TEST!!";
	}
	@ModelAttribute("v2")
	public int[] createArray() {
		System.out.println("Creating an object automatically 2");
		return new int[]{10, 20, 30, 40, 50};
	}
	@ModelAttribute("v3")
	public MyVO createVO() {
		System.out.println("Creating an object automatically 3");
		MyVO vo = new MyVO();
		vo.setMyColor("yellow");
		vo.setMyNumber(23);
		return vo;
	}	
	@ModelAttribute("v4")
	public Date createDate() {
		System.out.println("Creating an object automatically 4");		
		return new Date();
	}	
	@ModelAttribute("v5")
	public ArrayList<String> createList() {
		System.out.println("Creating an object automatically 5");
		ArrayList<String> list = new ArrayList<String>();
		list.add("ABC");
		list.add("XYZ");
		list.add("123");
		return list;
	}	
	@RequestMapping("/testmodel1")
	public String handle() {
		System.out.println("handle() Method Call");		
		return "modelResult";
	}
}



