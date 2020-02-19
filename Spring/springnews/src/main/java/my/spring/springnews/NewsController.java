package my.spring.springnews;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import dao.NewsDAO;
import dao.PagingControl;
import vo.NewsVO;

@SessionAttributes("paging")
@Controller
public class NewsController {
	
	@Autowired
	NewsDAO newsdao = null;
	
	@ModelAttribute("paging")
	public PagingControl zeroProd() { 
		int result = 1;
		return new PagingControl(result);
	}

	public List<NewsVO> listAll(PagingControl paging) {
		System.out.println("here");
		List<NewsVO> list = null;
		list = newsdao.listAll(paging);
		return list;
	}

	@RequestMapping("/newsmain")
	public ModelAndView newsMain(
			@RequestParam(value="pagenum", defaultValue="0")int pagenum,
			@ModelAttribute("paging") PagingControl paging) {
		ModelAndView mav = new ModelAndView();
		

		paging.pgNum=pagenum;

		mav.addObject("list", listAll(paging));
		mav.setViewName("news");

		return mav;
	}

	@RequestMapping("/listOne")
	public ModelAndView newsView(int newsid) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("column", newsdao.listOne(newsid));
//		mav.addObject("list", listAll());
		mav.setViewName("news");

		return mav;
	}

	@RequestMapping("/delete")
	public ModelAndView newsDel(int newsid) {
		ModelAndView mav = new ModelAndView();

		boolean result = newsdao.delete(newsid);
		if (result)
			System.out.println("삭제 성공");
		else
			System.out.println("삭제 실패");

//		mav.addObject("list", listAll());
		mav.setViewName("news");

		return mav;
	}

	@RequestMapping("/search")
	public ModelAndView newsSearch(String searchkeyword, String searchtype) {
		ModelAndView mav = new ModelAndView();
		List<NewsVO> list = null;
		
		if (searchtype.equals("제목")) {
			list = newsdao.search(searchkeyword, "null");
		} else if (searchtype.equals("작가")) {
			list = newsdao.search(searchkeyword, "listwriter");
		}

		mav.addObject("list", list);
		mav.setViewName("news");

		return mav;
	}

	@RequestMapping("/writer")
	public ModelAndView newsSearch(String searchkeyword) {
		ModelAndView mav = new ModelAndView();
		List<NewsVO> list = null;

		list = newsdao.search(searchkeyword, "listwriter");

		mav.addObject("list", list);
		mav.setViewName("news");

		return mav;
	}

	@RequestMapping(value = "/insert")
	public ModelAndView newsSearch(NewsVO vo) {
		ModelAndView mav = new ModelAndView();
		boolean result = newsdao.insert(vo);
		if (result)
			System.out.println("삽입 성공");
		else
			System.out.println("삽입 실패");

//		mav.addObject("list", listAll());
		mav.setViewName("news");

		return mav;
	}

	@RequestMapping(value = "/update")
	public ModelAndView newsUpdate(NewsVO vo) {
		ModelAndView mav = new ModelAndView();
		boolean result = newsdao.update(vo);
		if (result)
			System.out.println("업데이트 성공");
		else
			System.out.println("업데이트 실패");

//		mav.addObject("list", listAll());
		mav.setViewName("news");

		return mav;
	}

}
