package com.example.demo.POJO;

public class Cart {
	
	private int cartId;
	private int userId;
	private String title;
	private String author;
	private int count;
	private float price;
	private int bookId;
	private float subTotal;
	public Cart() {
		super();
	}
	public Cart(int cartId, int userId, String title, String author, int count, float price, int bookId,float subTotal) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.title = title;
		this.author = author;
		this.count = count;
		this.price = price;
		this.bookId = bookId;
		this.subTotal=subTotal;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
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
	@Override
	public String toString() {
		return "cart [cartId=" + cartId + ", userId=" + userId + ", title=" + title + ", author=" + author + ", count="
				+ count + ", price=" + price + ", bookId=" + bookId + "]";
	}
	

}
