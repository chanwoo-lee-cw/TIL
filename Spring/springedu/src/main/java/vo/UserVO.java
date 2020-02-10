package vo;

public class UserVO {
	private String userName;
	private String address;
	private String[] favoriteFruit;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getFavoriteFruit() {
		return favoriteFruit;
	}

	public void setFavoriteFruit(String[] favoriteFruit) {
		this.favoriteFruit = favoriteFruit;
	}

	@Override
	public String toString() {
		return "UserVO [userName=" + userName + ", address=" + address + ", favoriteFruit=" + favoriteFruit + "]";
	}
}
