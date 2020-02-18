package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class PagingControl {
	
	public int lineCnt; 	// 한 화면에 구성하는 글 개수
	public int pageCnt;  	// 한 화면의 페이지 개수
	public int postCnt; 	// 요구 사항을 만족하는 글의 전체 개수
	public int pgNum; 	    // 현재 페이지 넘버
	
	PagingControl(int total) {
		lineCnt = 10; 	// 한 화면에 구성하는 글 개수
		pageCnt = 5;  	// 한 화면의 페이지 개수
		postCnt = total; 	// 요구 사항을 만족하는 글의 전체 개수
		pgNum = 1; 	    // 현재 페이지 넘버
	}
	
	public int getPageCount( ) {
		return ((postCnt-1)/lineCnt)+1;
	}
	
	public int getPageStart( ) {
//		((현재 페이지넘버-1)/ 한 화면의 페이지 개수)*한 화면의 페이지 개수+1
		return ((pgNum-1)/pageCnt)*pageCnt+1;
	}
	
	public int getPageEnd( ) {
		//각 화면 별 시작 페이지 번호 + 한 화면의 페이지 개수 - 1 과 전체 페이지 개수 중에서 작은 값
		int start = getPageStart;
		return (start + pageCnt-1)<postCnt/lineCnt ? (start + pageCnt-1) : postCnt/lineCnt;
	}
	
	boolean isPreData( ) {
		boolean result = false;
		if(pgNum-3>0)
			result = true;
		return result;
		
	}
	
	boolean isNextData( ) {
		boolean result = false;
		if(pgNum+3<postCnt)
			result = true;
		return result;
		
	}
	
	int getWritingStart( ) {
		return getWritingEnd()-lineCnt+1;
		
	}
	
	int getWritingEnd() {
		return pgNum*lineCnt;
	}
	
	String returnAll() {
		return ""+getPageCount() + "\n" + getPageStart()+ "\n" +getPageEnd( )+ "\n" +isPreData( )+ "\n" +isNextData( )+ "\n" +getWritingStart( )+ "\n" +getWritingEnd();
	}

}
