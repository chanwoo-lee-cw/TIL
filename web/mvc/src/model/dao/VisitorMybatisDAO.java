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
			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			String statement = "resource.VisitorMapper.selectVisitor";
			list = session.selectList(statement);
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
			// 검색어로 키워드 전달
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
			String statement = "resource.VisitorMapper.insertVisitor";
			if(session.insert(statement, vo) == 1)			
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
