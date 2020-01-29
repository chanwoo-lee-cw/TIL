package model.vo;

public class ProductVO {

	private int applenum;
	private int bananannum;
	private int hallabongnum;

	public ProductVO() {
		applenum = 0;
		bananannum = 0;
		hallabongnum = 0;
	}

	public int getApplenum() {
		return applenum;
	}

	public void setApplenum(int num) {
		this.applenum = applenum + num;
	}

	public int getBananannum() {
		return bananannum;
	}

	public void setBananannum(int num) {
		this.bananannum = bananannum + num;
	}

	public int gethallabongnum() {
		return hallabongnum;
	}

	public void sethallabongnum(int num) {
		hallabongnum = hallabongnum + num;
	}

}
