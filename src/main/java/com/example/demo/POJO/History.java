package com.example.demo.POJO;

import java.util.Date;

public class History {
	
	private int historyId;
	private int userId;
	public String title;
	private Date tookDate;
	private Date dueDate;
	private Date returnDate;
	
	public History() {
		super();
	}

	public History(int historyId, int userId, String title, Date tookDate, Date dueDate, Date returnDate) {
		super();
		this.historyId = historyId;
		this.userId = userId;
		this.title = title;
		this.tookDate = tookDate;
		this.dueDate = dueDate;
		this.returnDate=returnDate;
	}

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getTookDate() {
		return tookDate;
	}

	public void setTookDate(Date tookDate) {
		this.tookDate = tookDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "History [historyId=" + historyId + ", userId=" + userId + ", title=" + title + ", tookDate=" + tookDate
				+ ", dueDate=" + dueDate + ", returnDate=" + returnDate +"]";
	}

}
