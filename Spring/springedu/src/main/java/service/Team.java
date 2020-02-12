package service;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import vo.TeamMemberVO;
import vo.TeamVO;

@Repository
public class Team {

	
	Team() {
		ArrayList<TeamMemberVO> list = new ArrayList<TeamMemberVO>();
		
		TeamMemberVO member1 = new TeamMemberVO();
		member1.setName("최성현");
		member1.setNicName("이 형 아는거 진짜 많네");
		list.add(member1);
		
		TeamMemberVO member2 = new TeamMemberVO();
		member2.setName("민세미");
		member2.setNicName("코가인상깊음");
		list.add(member2);
		
		TeamVO team = new TeamVO();
		team.setTeamName("Get IT View T");
		team.setTeamMember(list);
	}
	
}
