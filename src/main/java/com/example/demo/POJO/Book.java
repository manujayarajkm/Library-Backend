package com.example.demo.POJO;

import java.util.Arrays;

public class Book {
	
	private int bookId;
	private String title;
	private String author;
	private String genre;
	private float price;
	private int avilableNo;
	private String cover;
	private float rating;
	private int countRating;
	private int countReview;
	private int userId;
	private int count;
	
	public Book() {
		super();
	}

	public Book(int bookId, String title, String author, String genre, float price, int avilableNo, String cover,float rating,int countReview,int countRating,int userId,int count) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.price = price;
		this.avilableNo = avilableNo;
		this.cover = cover;
		this.rating=rating;
		this.countRating=countRating;
		this.countReview=countReview;
		this.userId=userId;
		this.count=count;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAvilableNo() {
		return avilableNo;
	}

	public void setAvilableNo(int avilableNo) {
		this.avilableNo = avilableNo;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getCountRating() {
		return countRating;
	}

	public void setCountRating(int countRating) {
		this.countRating = countRating;
	}

	public int getCountReview() {
		return countReview;
	}

	public void setCountReview(int countReview) {
		this.countReview = countReview;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", genre=" + genre + ", price="
				+ price + ", avilableNo=" + avilableNo + ", cover=" +cover + ", rating=" +rating + ", countRating=" +countRating + ", countReview=" +countReview + "]";
	}
	
	

}
