package com.example.demo.POJO;

import java.util.Date;

public class Purchase {
	
	private int purchaseId;
	private int userId;
	private String title;
	private String author;
	private int count;
	private float price;
	private int bookId;
	private float subTotal;
	private Date purchaseDate;
	private int billId;
	public Purchase() {
		super();
	}
	public Purchase(int purchaseId, int userId, String title, String author, int count, float price, int bookId,
			float subTotal, Date purchaseDate, int billId) {
		super();
		this.purchaseId = purchaseId;
		this.userId = userId;
		this.title = title;
		this.author = author;
		this.count = count;
		this.price = price;
		this.bookId = bookId;
		this.subTotal = subTotal;
		this.purchaseDate = purchaseDate;
		this.billId = billId;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", userId=" + userId + ", title=" + title + ", author=" + author
				+ ", count=" + count + ", price=" + price + ", bookId=" + bookId + ", subTotal=" + subTotal
				+ ", purchaseDate=" + purchaseDate + ", billId=" + billId + "]";
	}

}
