package model.vo;

public class MeetingVO {

	private int id; 
	private String name; 
	private String title;
	private String meetingDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}
	public String toString() {
		return String.format("%5s%10s%20s%50s", this.id, this.name, this.title, this.meetingDate);
	}
	
	
}
