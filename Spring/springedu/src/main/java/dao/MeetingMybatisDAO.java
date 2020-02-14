package dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.MeetingDAO;
import vo.MeetingVO;

@Repository
public class MeetingMybatisDAO implements MeetingDAO {
	@Autowired
	SqlSession session = null;

	public List<MeetingVO> listAll() {
		// TODO Auto-generated method stub
		System.out.println("Mybatis 를 사용 DB 연동-listAll ");
		List<MeetingVO> list = new ArrayList<>();

		String statement = "resource.MeetingMapper.selectMeeting";

		list = session.selectList(statement);

		System.out.println(session.getClass().getName());
//		System.out.print(list.toString());
//		 
		return list;
	}

	public boolean insert(MeetingVO vo) {
		// TODO Auto-generated method stub

		System.out.println("Mybatis 를 사용 DB 연동-insert ");
		boolean result = false;

		// sql session 객체를 만들때 true를 준다.
		// true를 준다는 것은 자동 commit 모드가 활성화 된다.

		String statement = "resource.MeetingMapper.insertMeeting";
		if (session.insert(statement, vo) == 1)
			result = true;

//		 
		return result;
	}

	public List<MeetingVO> search(String keyword) {
		// TODO Auto-generated method stub

		System.out.println("Mybatis 를 사용 DB 연동-search ");
		List<MeetingVO> list = new ArrayList<>();

		String statement = "resource.MeetingMapper.searchMeeting";

		list = session.selectList(statement, keyword);

		System.out.println(session.getClass().getName());

//		 
		return list;
	}

	public boolean delete(int eNo) {
		// TODO Auto-generated method stub

		System.out.println("Mybatis 를 사용 DB 연동-delete ");
		boolean result = false;

		// sql session 객체를 만들때 true를 준다.
		// true를 준다는 것은 자동 commit 모드가 활성화 된다.

		String statement = "resource.MeetingMapper.deleteMeeting";
		if (session.insert(statement, eNo) == 1)
			result = true;

//		 
		return result;
	}

	public boolean update(MeetingVO vo) {
		System.out.println("Mybatis 를 사용 DB 연동-update ");
		boolean result = false;

		// sql session 객체를 만들때 true를 준다.
		// true를 준다는 것은 자동 commit 모드가 활성화 된다.

		String statement = "resource.MeetingMapper.updateMeeting";
		if (session.insert(statement, vo) == 1)
			result = true;

		 
		return result;
	}

}
