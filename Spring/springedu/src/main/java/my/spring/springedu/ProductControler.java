package my.spring.springedu;


import vo.ProductVO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("prodList")
public class ProductControler {

	@ModelAttribute("prodList")
	public ProductVO zeroProd() { 
		System.out.println("0 과일 바구니 생성");
		return new ProductVO();
	}
	
	@RequestMapping(value="/product")
	public String prodCnt (@ModelAttribute("prodList")ProductVO vo) {
		
		return "productView";
	}

//	@RequestMapping(value="/product")
//	public String prodCnt (@ModelAttribute("prodList")ProductVO vo,
//			@ModelAttribute("pid")String pid,
//			SessionStatus s,
//			HttpServletResponse response) {
//		switch (pid) {
//		case "p000" :
//			// json활용 1
//			s.setComplete();
//			String str = "{\"msg\" : \"장바구니가 비어졌어요!\"}";
//			try {
//				response.setContentType("text/json; charset=utf-8");
//				response.getWriter().print(str);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return null;
//		case "p001" :
//			vo.setApplenum(1);
//			break;
//		case "p002" :
//			vo.setBananannum(1);
//			break;
//		case "p003" :
//			vo.sethallabongnum(1);
//			break;
//		}
//		return "productView";
//	}

	@RequestMapping(value="/productdel")
	public String prodCnt (SessionStatus s) {
		// json활용 2
		s.setComplete();
		return "productdel";
	}
	

}
