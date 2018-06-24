package com.example.demo.POJO;

import java.util.Date;

public class Notifications {
	
	private int notificationId;
	private Date notificationDate;
	private String matter;
	private int userid;
	private int bookId;
	private Date ackDate;
	public Notifications() {
		super();
	}
	public Notifications(int notificationId, Date notificationDate, String matter, int userid,int bookId,Date ackDate) {
		super();
		this.notificationId = notificationId;
		this.notificationDate = notificationDate;
		this.matter = matter;
		this.userid = userid;
		this.bookId=bookId;
		this.ackDate=ackDate;
	}
	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public Date getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Date getAckDate() {
		return ackDate;
	}
	public void setAckDate(Date ackDate) {
		this.ackDate = ackDate;
	}
	@Override
	public String toString() {
		return "Notifications [notificationId=" + notificationId + ", notificationDate=" + notificationDate
				+ ", matter=" + matter + ", userid=" + userid + ", bookid=" + bookId + ", ackDate=" + ackDate + "]";
	}

}
