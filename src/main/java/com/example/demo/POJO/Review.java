package com.example.demo.POJO;

public class Review {
	private int reviewId;
	private int userId;
	private String body;
	private int bookId;
	private String name;
	private float rating;
	public Review() {
		super();
	}
	public Review(int reviewId, int userId, String body, int bookId,String name,float rating) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.body = body;
		this.bookId = bookId;
		this.name=name;
		this.rating=rating;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", userId=" + userId + ", body=" + body + ", bookId=" + bookId + ", name=" + name + ", rating=" + rating + "]";
	}

}
