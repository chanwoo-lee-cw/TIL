package model.dao;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.vo.NewsVO;
import model.vo.NewsVO;
import model.vo.NewsVO;

class NewsDAOtest {

	@Test
	void test() {
		NewsDAO dao = new NewsDAO();
		List<NewsVO> list = dao.listAll(1);
		System.out.println(list.size());
		for (NewsVO vo : list)
			System.out.println(vo);
	}
	
//	@Test
//	void test1() {
//		NewsDAO dao = new NewsDAO();
//		NewsVO vo = dao.listOne(2);
//		System.out.println(vo);
//	}
	
//	@Test
//	void test2() {
//		System.out.println("삽입 테스트");
//		NewsDAO dao = new NewsDAO();
//		NewsVO vo = new NewsVO();
//		for(int i =0 ; i <100 ;i++) {
//		vo.setWriter("alpha");
//		vo.setTitle(i+" test");
//		vo.setContent("hegohome");
//		boolean result = dao.insert(vo);
//		}
//	}

//	@Test
//	void test3() {
//		System.out.println("업데이트 테스트");
//		NewsDAO dao = new NewsDAO();
//		NewsVO vo = new NewsVO();
//		vo.setId(2);
//		vo.setWriter("me");
//		vo.setTitle("do you wanna build snow man");
//		vo.setContent("do you wanna build snow man");
//		boolean result = dao.update(vo);
//		if(result)
//			System.out.println("업데이트 성공");
//		else
//			fail("업데이트 실패");
//	}
	
//	@Test
//	void test4() {
//		System.out.println("삭제 테스트");
//		NewsDAO dao = new NewsDAO();
//		boolean result = dao.delete(3);
//		if(result)
//			System.out.println("삭제 성공");
//		else
//			fail("삭제 실패");
//	}
	
//	@Test
//	void test() {
//		NewsDAO dao = new NewsDAO();
//		List<NewsVO> list = dao.search("1", "null");
//		System.out.println(list.size());
//		for (NewsVO vo : list)
//			System.out.println(vo);
//		list = dao.search("ㅁㄴㅇ", "listwriter");
//		System.out.println(list.size());
//		for (NewsVO vo : list)
//			System.out.println(vo);
//	}

}
