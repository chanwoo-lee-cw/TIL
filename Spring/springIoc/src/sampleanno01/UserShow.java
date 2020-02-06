package sampleanno01;

public class UserShow {
	private User user;

	public UserShow() {		
		super();
		System.out.println("Constructor Call(no-args)");
	}
	public UserShow(User user) {
		super();
		this.user = user;
		System.out.println("Constructor Call(User-args)");
	}
	
	public void setUser(User user1) {
		System.out.println("Setter Call by Autowire");
		this.user = user1;
	}

	@Override
	public String toString() {
		return "UserShow [user=" + user + "]";
	}

}
