package model.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.vo.VisitorVO;

class VisiterDAOTest {

	// inotation 구분으로 어떤 기능을 하는지 알려준다
	// test라는 이노테이션이 붙어 있는 것은 run 할때 모두 호출한다.
	@Test
	void test() {
		VisitorDAO dao = new VisitorDAO();
		List<VisitorVO> list = dao.listAll();
		System.out.println(list.size());
		for (VisitorVO vo : list)
			System.out.println(vo);
	}
	
	@Test
	void test1() {
		System.out.println("검색 기능 테스트");
		VisitorDAO dao = new VisitorDAO();
		List<VisitorVO> list = dao.search("집");
		System.out.println(list.size());
		for (VisitorVO vo : list)
			System.out.println(vo);
	}
	
	@Test
	void test2() {
		System.out.println("삽입 테스트");
		VisitorDAO dao = new VisitorDAO();
		VisitorVO vo = new VisitorVO();
		vo.setName("희동이");
		vo.setMemo("~~~~집에 보내줘요~~~~");
		boolean result = dao.insert(vo);
		if(result)
			System.out.println("삽입 성공");
		else
			fail("삽입 실패");
	}

}
