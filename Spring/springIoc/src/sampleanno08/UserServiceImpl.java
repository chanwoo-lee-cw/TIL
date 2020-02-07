package sampleanno08;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component("userService")   //기본값 : userServiceImpl
public class UserServiceImpl implements UserService{
	//or @Autowired
	@Resource
	// 리소스의 경우에는 동일한 이름의 객체가 있는지 찾는다.
	User member;
	
	public void setMember(User member) {
		this.member = member;
	}

	@Override
	public void addUser() {
		System.out.println("Added member : " + member.getUserName());
	}
}
