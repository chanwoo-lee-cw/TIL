package my.spring.springedu;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vo.TeamMemberVO;
import vo.TeamVO;

@Controller
public class MyTeamController {
	
	@RequestMapping(value = "/myteam/json", produces = "application/json; charset=utf-8")
	@ResponseBody
	public TeamVO TeamConfigJson() {
		
		ArrayList<TeamMemberVO> list = new ArrayList<TeamMemberVO>();
		
		TeamMemberVO member1 = new TeamMemberVO();
		member1.setName("최성현");
		member1.setNicName("이 형 아는거 엄청 많다.");
		list.add(member1);
		
		TeamMemberVO member2 = new TeamMemberVO();
		member2.setName("민세미");
		member2.setNicName("날 돈까스로 기억하고 있음...");
		list.add(member2);
		
		TeamVO team = new TeamVO();
		team.setTeamName("Get IT View T");
		team.setTeamMember(list);
		
		return team;
	}
	
	@RequestMapping(value = "/myteam/xml", produces = "application/xml; charset=utf-8")
	@ResponseBody
	public TeamVO TeamConfigXML() {
		
		ArrayList<TeamMemberVO> list = new ArrayList<TeamMemberVO>();
		
		TeamMemberVO member1 = new TeamMemberVO();
		member1.setName("최성현");
		member1.setNicName("이 형 아는거 엄청 많다.");
		list.add(member1);
		
		TeamMemberVO member2 = new TeamMemberVO();
		member2.setName("민세미");
		member2.setNicName("날 돈까스로 기억하고 있음...");
		list.add(member2);
		
		TeamVO team = new TeamVO();
		team.setTeamName("Get IT View T");
		team.setTeamMember(list);
		
		return team;
	}
}
