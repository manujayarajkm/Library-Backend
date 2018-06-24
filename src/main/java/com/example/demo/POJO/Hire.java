package com.example.demo.POJO;

import java.time.LocalDate;
import java.util.Date;

public class Hire {
	
	private int hireId;
	private int bookId;
	private int userId;
	public String name;
	public String title;
	private Date tookDate;
	private Date dueDate;
	
	public Hire() {
		super();
	}

	public Hire(int bookId, int userId, Date tookDate, Date dueDate,int hireId,String name,String title) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.tookDate = tookDate;
		this.dueDate = dueDate;
		this.hireId=hireId;
		this.name=name;
		this.title=title;
	}

	public int getHireId() {
		return hireId;
	}

	public void setHireId(int hireId) {
		this.hireId = hireId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getTookDate() {
		return tookDate;
	}

	public void setTookDate(Date date1) {
		this.tookDate = date1;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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

	@Override
	public String toString() {
		return "Hire [hireId=" + hireId + ", bookId=" + bookId + ", userId=" + userId + ", tookDate=" + tookDate
				+ ", dueDate=" + dueDate + "]";
	}

	

}
