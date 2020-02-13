package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.vo.VisitorVO;

public class VisitorMybatisDAO2 {
	final String resource = "resource/mybatis-config.xml"; // src 폴더 기준
	SqlSessionFactory sqlSessionFactory;

	public VisitorMybatisDAO2() {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			// SQL 세션 객체를 생성하는 부분

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			// SQL 펙도리 빌더로 빌더로 객체 생성한다. sql 세션 뿐만 아니라 preparedstatement도 완료 되었다.

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<VisitorVO> listAll() {
		System.out.println("Mybatis 를 사용 DB 연동-listAll-DAO2");
		List<VisitorVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		String statement = "resource.VisitorMapper.selectVisitor";
		// id값과 네임 스페이스를 붙힌다.

		list = session.selectList(statement);
		// 명령문을 실행한다. select 를 실행한다.

		System.out.println(session.getClass().getName());

		session.close();
		return list;
	}

	public List<VisitorVO> search(String keyword) {
		System.out.println("Mybatis 를 사용 DB 연동-search-DAO2");
		List<VisitorVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		String statement = "resource.VisitorMapper.searchVisitor";
		// 네임 스페이스와 네이디 값
		list = session.selectList(statement, keyword);
		// id 값과 keyword를 보낸다.
		// 결과문이 얼마나 올지 예상이 안 감. List를 사용한다.

		session.close();
		return list;
	}

	public boolean insert(VisitorVO vo) {
		System.out.println("Mybatis 를 사용 DB 연동-insert-DAO2");
		boolean result = false;
		SqlSession session = sqlSessionFactory.openSession(true);
		// sql session 객체를 만들때 true를 준다.
		// true를 준다는 것은 자동 commit 모드가 활성화 된다.

		String statement = "resource.VisitorMapper.insertVisitor";
		if (session.insert(statement, vo) == 1)
			result = true;

		session.close();
		return result;
	}
}
