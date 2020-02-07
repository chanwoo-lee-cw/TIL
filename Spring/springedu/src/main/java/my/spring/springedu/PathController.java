package my.spring.springedu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class PathController {
	@RequestMapping
	     (value="/character/detail/{name}/{number}")
	///character와 detail은 정적(정해져 있고)
	// name과 number은 동적이다.
	 public String getAllBoards(@PathVariable("number") int num, 
	                             @PathVariable String name, Model model){
		// 직접 만들면 또 선언되고 또 선언 되지만 @PathVariable로 전달하면 연결해 준다. -> 그리고 자동으로 request에 보관해준다.
		// Model은 한번 생성된 것을 연결해 주는 방법으로 재활용 한다.
		 System.out.println(model.getClass().getName());
	     if(name.equals("kakao")) {
	    	 if (num == 1) 
	    		 model.addAttribute("imgname", "ryan");
	    	 else if (num == 2) 
	    		 model.addAttribute("imgname", "muzicon");
	    	 else if (num == 3) 
	    		 model.addAttribute("imgname", "apeach");
	    	 else if (num == 4) 
	    		 model.addAttribute("imgname", "jayg");
	    	 else if (num == 5) 
	    		 model.addAttribute("imgname", "frodoneo");
	    	 else if (num == 6) 
	    		 model.addAttribute("imgname", "tube");
	     } else if (name.equals("line")) {
	    	 if (num == 1) 
	    		 model.addAttribute("imgname", "brown");
	    	 else if (num ==2) 
	    		 model.addAttribute("imgname", "james");
	    	 else if (num == 3) 
	    		 model.addAttribute("imgname", "cony");
	    	 else if (num == 4) 
	    		 model.addAttribute("imgname", "edward");
	    	 else if (num == 5) 
	    		 model.addAttribute("imgname", "leonard");
	    	 else if (num == 6) 
	    		 model.addAttribute("imgname", "moon");
	    	 else if (num == 7) 
	    		 model.addAttribute("imgname", "sally");
	    	 else if (num == 8) 
	    		 model.addAttribute("imgname", "jessica");
	     }
	     return "detailView";
	 }
}
