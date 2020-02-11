package service;

import org.springframework.stereotype.Service;

import vo.FriendVO;

@Service
public class FriendService {
	public FriendVO get(int num) {
		FriendVO vo = null;
		if (num == 10) {
			vo = new FriendVO();
			vo.setPhoneNum("010-1111-2222");
			vo.setName("Dooly");			
		}
		return vo;
	}
}
