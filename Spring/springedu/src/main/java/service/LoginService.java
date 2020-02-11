package service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public boolean login(String id, String passwd) {
		if (passwd.equals("@1234") && id.equals("spring")) {
			return true;
		}
		else {
		return false;
		}
	}

}
