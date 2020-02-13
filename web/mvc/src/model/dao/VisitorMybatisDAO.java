package model.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.vo.VisitorVO;

public class VisitorMybatisDAO {	
	final String resource = "resource/mybatis-config.xml"; // src 폴더 기준
	public List<VisitorVO> listAll() {
		System.out.println("Mybatis 를 사용 DB 연동-listAll");
		List<VisitorVO> list = null;		
		SqlSession session = null;
		try {			
			InputStream inputStream = 
					Resources.getResourceAsStream(resource);
			//SQL 세션 객체를 생성하는 부분
			
			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
			// SQL 펙도리 빌더로 빌더로 객체 생성한다. sql 세션 뿐만 아니라 preparedstatement도 완료 되었다.
			
			session = sqlSessionFactory.openSession();
			String statement = "resource.VisitorMapper.selectVisitor";
			// id값과 네임 스페이스를 붙힌다.
			
			list = session.selectList(statement);
			// 명령문을 실행한다. select 를 실행한다.
			
			System.out.println(session.getClass().getName());		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	public List<VisitorVO> search(String keyword) {
		System.out.println("Mybatis 를 사용 DB 연동-search");
		List<VisitorVO> list = null;
		SqlSession session = null; 
		try {			
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			String statement = "resource.VisitorMapper.searchVisitor";
			// 네임 스페이스와 네이디 값
			list = session.selectList(statement, keyword);
			// id 값과 keyword를 보낸다.
			// 결과문이 얼마나 올지 예상이 안 감. List를 사용한다.
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;		
	}

	public boolean insert(VisitorVO vo) {
		System.out.println("Mybatis 를 사용 DB 연동-insert");
		boolean result = false;
		SqlSession session = null;
		try {
			InputStream inputStream = 
					Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession(true);
			// sql session 객체를 만들때 true를 준다.
			// true를 준다는 것은 자동  commit 모드가 활성화 된다.
			
			String statement = "resource.VisitorMapper.insertVisitor";
			if(session.insert(statement, vo) == 1)
				//
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null)
				session.close();
		}
		return result;
	}	
}
