package com.example.demo.POJO;

public class User {
	
	private int userId;
	private String name;
	private String email;
	private double phone;
	private String remarks;
	private String active;
	private String userName;
	private String password;
	
	public User() {
		super();
	}

	public User(int userId, String name, String email, double phone, String remarks,
			String active,String userName,String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.remarks = remarks;
		this.active = active;
		this.userName=userName;
		this.password=password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getPhone() {
		return phone;
	}

	public void setPhone(double phone) {
		this.phone = phone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", phone=" + phone +  ", remarks=" + remarks + ", active=" + active + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
	

}
