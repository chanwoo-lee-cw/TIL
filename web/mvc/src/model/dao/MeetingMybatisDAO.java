package model.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.vo.MeetingVO;

public class MeetingMybatisDAO implements MeetingDAO {
	final String resource = "resource/mybatis-config.xml"; // src 폴더 기준
	SqlSessionFactory sqlSessionFactory;
	
	public MeetingMybatisDAO() {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<MeetingVO> listAll() {
		// TODO Auto-generated method stub
		System.out.println("Mybatis 를 사용 DB 연동-listAll ");
		List<MeetingVO> list = new ArrayList<>();
		SqlSession session = sqlSessionFactory.openSession();
		String statement = "resource.MeetingMapper.selectMeeting";

		list = session.selectList(statement);

		System.out.println(session.getClass().getName());

		session.close();
		return list;
	}

	@Override
	public boolean insert(MeetingVO vo) {
		// TODO Auto-generated method stub
		
		System.out.println("Mybatis 를 사용 DB 연동-insert ");
		boolean result = false;
		SqlSession session = sqlSessionFactory.openSession(true);
		// sql session 객체를 만들때 true를 준다.
		// true를 준다는 것은 자동 commit 모드가 활성화 된다.

		String statement = "resource.MeetingMapper.insertMeeting";
		if (session.insert(statement, vo) == 1)
			result = true;

		session.close();
		return result;
	}

	@Override
	public List<MeetingVO> search(String keyword) {
		// TODO Auto-generated method stub
		
		System.out.println("Mybatis 를 사용 DB 연동-search ");
		List<MeetingVO> list = new ArrayList<>();
		SqlSession session = sqlSessionFactory.openSession();
		String statement = "resource.MeetingMapper.searchMeeting";

		list = session.selectList(statement,keyword);

		System.out.println(session.getClass().getName());

		session.close();
		return list;
	}

	@Override
	public boolean delete(int eNo) {
		// TODO Auto-generated method stub
		
		System.out.println("Mybatis 를 사용 DB 연동-delete ");
		boolean result = false;
		SqlSession session = sqlSessionFactory.openSession(true);
		// sql session 객체를 만들때 true를 준다.
		// true를 준다는 것은 자동 commit 모드가 활성화 된다.

		String statement = "resource.MeetingMapper.deleteMeeting";
		if (session.insert(statement, eNo) == 1)
			result = true;

		session.close();
		return result;
	}
	
	
	public boolean update(MeetingVO vo) {
		System.out.println("Mybatis 를 사용 DB 연동-update ");
		boolean result = false;
		SqlSession session = sqlSessionFactory.openSession(true);
		// sql session 객체를 만들때 true를 준다.
		// true를 준다는 것은 자동 commit 모드가 활성화 된다.

		String statement = "resource.MeetingMapper.updateMeeting";
		if (session.insert(statement, vo) == 1)
			result = true;

		session.close();
		return result;
	}
	

}
