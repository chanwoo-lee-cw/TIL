package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.vo.LottoVO;

@WebServlet("/lotto2")
public class LottoServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		HttpSession session = request.getSession();
		if(session.getAttribute("cnt") == null) {
			session.setAttribute("cnt", new int [1]);
		}
		int [] session_v = (int[])session.getAttribute("cnt");
		session_v[0]+=1;
		int picknum = Integer.parseInt(num);
		int rannum = (int)(Math.random()*6)+1;
		System.out.println("입력한 숫자 : "+picknum + "\n"+ "랜덤 숫자 : "+rannum);
		
		LottoVO vo = new LottoVO();
		LocalDateTime ctime = LocalDateTime.now();
		DateTimeFormatter formatter = 
				                  DateTimeFormatter.ofPattern("hh시 mm분");	
		if(session_v[0]>=4) {
			vo.setMsg("더 이상 응모할 수 없어요...ㅠㅠ 브라우저를 재기동한 후에 응모하세요");			
		} else {	
			if(picknum==rannum) {
				vo.setMsg(ctime.format(formatter) + "에 당첨!! 추카추카~");
				vo.setImgUrl("/images/happyOnion.png" );				
				session_v[0]=10;
			} else {
				vo.setMsg(ctime.format(formatter) + "에 당첨 실패!! 아쉽아쉽~");
				vo.setImgUrl("/images/sadOnion.png" );
				vo.setLinkDisplay(true);				
			}
		}	
		request.setAttribute("lotto", vo);
		RequestDispatcher impossible = request.getRequestDispatcher("/jspexam/lottoView.jsp");
		impossible.forward(request, response);
	}
}








