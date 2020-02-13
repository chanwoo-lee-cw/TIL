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

public class MeetingJDBCDAO implements MeetingDAO {

	@Override
	public List<MeetingVO> listAll() {
		// TODO Auto-generated method stub
		List<MeetingVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest",
				"jdbctest");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select id,name,title, to_char(meetingDate, "
						+ "'yyyy\"년\" mm\"월\" dd\"일\"') AS \"day\" from meeting");) {
			MeetingVO vo;
			while (rs.next()) {
				vo = new MeetingVO();
				vo.setId(Integer.parseInt(rs.getString("id")));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setMeetingDate(rs.getString("day"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insert(MeetingVO vo) {
		// TODO Auto-generated method stub
		boolean result = true;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest",
				"jdbctest");
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into meeting values (meeting_seq.nextval, ? ,?, to_date(?, 'yyyy-mm-dd\"T\"hh24:mi' ))");) {
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getMeetingDate());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<MeetingVO> search(String keyword) {
		// TODO Auto-generated method stub
		List<MeetingVO> list = new ArrayList<>();
		String rf="select id, name, title, to_char(meetingDate, "
				+ "'yyyy\"년\" mm\"월\" dd\"일\"') AS \"day\" from meeting "
				+ "where title like '%"+keyword+"%'";
		System.out.print(rf);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest", "jdbctest");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(rf);) {
			
			MeetingVO vo;
			while(rs.next()) {
				vo = new MeetingVO();
				vo.setId(Integer.parseInt(rs.getString("id")));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setMeetingDate(rs.getString("day"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean delete(int eNo) {
		// TODO Auto-generated method stub
		
		boolean result = true;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest",
				"jdbctest");
				PreparedStatement pstmt = conn.prepareStatement(
						"delete from meeting where id = ?");) {
			pstmt.setInt(1, eNo);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	public boolean update(MeetingVO vo) {
		boolean result = true;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbctest","jdbctest");
				PreparedStatement pstmt = conn.prepareStatement(
						"update meeting set " + 
						"name = ?, " + 
						"title = ?, " + 
						"meetingdate = to_date(?, 'yyyy-mm-dd\"T\"hh24:mi' ) " + 
						"where id = ?");){
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getMeetingDate());
			pstmt.setInt(4, vo.getId());
			pstmt.executeUpdate();			
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	

}
