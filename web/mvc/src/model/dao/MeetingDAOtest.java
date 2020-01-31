package model.dao;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.vo.MeetingVO;


class MeetingDAOtest {

	@Test
	void test() {
		MeetingJDBCDAO dao = new MeetingJDBCDAO();
		List<MeetingVO> list = dao.listAll();
		System.out.println(list.size());
		for (MeetingVO vo : list)
			System.out.println(vo);
	}
	
	@Test
	void test2() {
		System.out.println("삽입 테스트");
		MeetingJDBCDAO dao = new MeetingJDBCDAO();
		MeetingVO vo = new MeetingVO();
		vo.setName("나는");
		vo.setTitle("집에 가고 싶다.");
		vo.setMeetingDate("2020-10-20T15:20");
		boolean result = dao.insert(vo);
		if(result)
			System.out.println("삽입 성공");
		else
			fail("삽입 실패");
	}
	
	@Test
	void test3() {
		MeetingJDBCDAO dao = new MeetingJDBCDAO();
		List<MeetingVO> list = dao.search("집");
		System.out.println(list.size());
		for (MeetingVO vo : list)
			System.out.println(vo);
	}
	
	@Test
	void test4() {
		MeetingJDBCDAO dao = new MeetingJDBCDAO();
		boolean result = dao.delete(2);
		if(result)
			System.out.println("삭제 성공");
		else
			fail("삭제 실패");
	}

}
