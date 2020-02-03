package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.MeetingVO;
import model.vo.NewsVO;

public class NewsDAO implements NewsDAOinterface {

	private Connection connectDB() throws SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean insert(NewsVO vo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = true;
		try {
			conn = connectDB();
			pstmt = conn.prepareStatement("insert into news values (news_seq.nextval, ?, ?, ?, sysdate, 0)");
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return result;
	}

	@Override
	public boolean update(NewsVO vo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = true;
		try {
			conn = connectDB();
			pstmt = conn.prepareStatement(
					"update news set writer = ?, title = ?, content = ? where id = ?");
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}

		return result;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = true;
		try {
			conn = connectDB();
			pstmt = conn.prepareStatement("delete from news where id = ?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			close(conn, pstmt, null);
		}
		return result;
	}

	@Override
	public List<NewsVO> listAll() {
		// TODO Auto-generated method stub
		List<NewsVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = connectDB();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id, writer, title, content, to_char(writedate, "
					+ "'yyyy\"년\" mm\"월\" dd\"일\"') AS \"day\", cnt from news");
			NewsVO vo;
			while (rs.next()) {
				vo = new NewsVO();
				vo.setId(Integer.parseInt(rs.getString("id")));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWritedate(rs.getString("day"));
				vo.setCnt(Integer.parseInt(rs.getString("cnt")));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		return list;
	}

	@Override
	public NewsVO listOne(int id) {
		// TODO Auto-generated method stub
		NewsVO searchnews = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = connectDB();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id, writer, title, content, to_char(writedate, "
					+ "'yyyy\"년\" mm\"월\" dd\"일\"') AS \"day\", cnt from news where id=" + id);
			if (rs.next()) {
				searchnews = new NewsVO();
				searchnews.setId(Integer.parseInt(rs.getString("id")));
				searchnews.setWriter(rs.getString("writer"));
				searchnews.setTitle(rs.getString("title"));
				searchnews.setContent(rs.getString("content"));
				searchnews.setWritedate(rs.getString("day"));
				searchnews.setCnt(Integer.parseInt(rs.getString("cnt"))+1);

				stmt.executeQuery("Update news set cnt="+searchnews.getCnt()+" where id="+id);
			} else {
				throw new SQLException();
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, stmt, rs);
		}
		return searchnews;
	}

	@Override
	public List<NewsVO> listWriter(String writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NewsVO> search(String key, String searchType) {
		List<NewsVO> list = new ArrayList<>();
		if(searchType.contentEquals("listwriter")) {
			list = listWriter(key);
		}
		else {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
		}
		
		return null;
		
	}

}
