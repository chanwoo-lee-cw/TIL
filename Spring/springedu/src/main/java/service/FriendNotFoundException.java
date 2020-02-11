package service;
public class FriendNotFoundException extends Exception {
	public FriendNotFoundException() {
		
	}
	public FriendNotFoundException(String message) {
		super(message);
	}
}
