package com.example.demo.Data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.POJO.Book;
import com.example.demo.POJO.Hire;
import com.example.demo.POJO.History;
import com.example.demo.POJO.Notifications;
import com.example.demo.POJO.Purchase;
import com.example.demo.POJO.Review;
import com.example.demo.POJO.Cart;

public interface TransactionDAO {
	
	public String bookBorrowal(int bookId,int userId)throws SQLException,ClassNotFoundException;
	public List<Hire> getAllHires()throws SQLException,ClassNotFoundException;
	public String bookReturn(int hireId) throws SQLException,ClassNotFoundException;
	public List<Hire> getMyHires(int userId)throws SQLException,ClassNotFoundException;
	public List<History> getMyHistory(int userId)throws SQLException,ClassNotFoundException;
	public List<Notifications> getMyNotifications(int userId)throws SQLException,ClassNotFoundException;
	public String addNotifications(String matter,int userId) throws SQLException,ClassNotFoundException;
	public String removeNotifications(int NotificationId) throws SQLException,ClassNotFoundException;
	public String checkDuplicate(int bookId,String matter,int userId) throws SQLException,ClassNotFoundException;
	public List<Notifications> getAcknowledgedNotifications(int userId)throws SQLException,ClassNotFoundException;
	public String addReview(int bookId,int userId,String body,float rating)throws SQLException,ClassNotFoundException;
	public List<Review> getReviews(int bookId)throws SQLException,ClassNotFoundException;
	public String sendMail(int userId,String email)throws SQLException,ClassNotFoundException;
	public String resetPassword(int userId,int tempCode,String passwordNew)throws SQLException,ClassNotFoundException;
	public int getUserId(String userName)throws SQLException,ClassNotFoundException;
	public String updatePassword(int userId,String oldPassword,String passwordNew)throws SQLException,ClassNotFoundException;
	public String imageUpload(MultipartFile file,String name)throws SQLException,ClassNotFoundException,IOException;
	public String updateProfile(int userId,String name,String email,Double phone)throws SQLException,ClassNotFoundException;
	public String addToCart(int bookId,int userId,String title,String author,float price)throws SQLException,ClassNotFoundException;
	public List<Cart> cartReview(int userId)throws SQLException,ClassNotFoundException;
	public String RemoveFromCart(int cartId,int count,int bookId)throws SQLException,ClassNotFoundException;
	public String incrementItem(int cartId,int bookId)throws SQLException,ClassNotFoundException;
	public String decrementItem(int cartId,int bookId)throws SQLException,ClassNotFoundException;
	public String updatePurchase(int userId,int billId)throws SQLException,ClassNotFoundException;
	public int getMaxBillId()throws SQLException,ClassNotFoundException;
	public String clearPurchase(int userId)throws SQLException,ClassNotFoundException;
	public List<Purchase> purchaseHistory(int userId)throws SQLException,ClassNotFoundException;






 

}
