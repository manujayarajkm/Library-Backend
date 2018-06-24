package com.example.demo.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Data.TransactionDAO;
import com.example.demo.POJO.Book;
import com.example.demo.POJO.Cart;
import com.example.demo.POJO.Hire;
import com.example.demo.POJO.History;
import com.example.demo.POJO.Notifications;
import com.example.demo.POJO.Purchase;
import com.example.demo.POJO.Review;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionDAO transactionDAO;

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}

	public void setTransactionDAO(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	public TransactionServiceImpl(TransactionDAO transactionDAO) {
		super();
		this.transactionDAO = transactionDAO;
	}

	@Override
	public String bookBorrowal(int bookId, int userId)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.bookBorrowal(bookId, userId);
	}

	@Override
	public List<Hire>getAllHires() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.getAllHires();
	}

	@Override
	public String bookReturn(int hireId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.bookReturn(hireId);
	}

	@Override
	public List<Hire> getMyHires(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.getMyHires(userId);
	}

	@Override
	public List<History> getMyHistory(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.getMyHistory(userId);
	}

	@Override
	public List<Notifications> getMyNotifications(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.getMyNotifications(userId);
	}

	@Override
	public String addNotifications(String matter, int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.addNotifications(matter, userId);
	}

	@Override
	public String removeNotifications(int NotificationId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.removeNotifications(NotificationId);
	}

	@Override
	public String checkDuplicate(int bookId, String matter, int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.checkDuplicate(bookId, matter, userId);
	}

	@Override
	public List<Notifications> getAcknowledgedNotifications(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.getAcknowledgedNotifications(userId);
	}

	@Override
	public String addReview(int bookId, int userId, String body,float rating) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.addReview(bookId,userId,body,rating);
	}

	@Override
	public List<Review> getReviews(int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.getReviews(bookId);
	}

	@Override
	public String sendMail(int userId,String email) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.sendMail(userId,email);
	}

	@Override
	public String resetPassword(int userId, int tempCode, String passwordNew)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.resetPassword(userId, tempCode, passwordNew);
	}

	@Override
	public int getUserId(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.getUserId(userName);
	}

	@Override
	public String updatePassword(int userId, String oldPassword, String passwordNew)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.updatePassword(userId, oldPassword, passwordNew);
	}

	@Override
	public String imageUpload(MultipartFile file,String name) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return transactionDAO.imageUpload(file,name);
	}

	@Override
	public String updateProfile(int userId, String name, String email, Double phone)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.updateProfile(userId, name, email, phone);
	}

	@Override
	public String addToCart(int bookId,int userId, String title, String author, float price)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.addToCart(bookId,userId, title, author, price);
	}

	@Override
	public List<Cart> cartReview(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.cartReview(userId);
	}

	@Override
	public String RemoveFromCart(int cartId, int count, int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.RemoveFromCart(cartId, count, bookId);
	}

	@Override
	public String incrementItem(int cartId,int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.incrementItem(cartId,bookId);
	}

	@Override
	public String decrementItem(int cartId,int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.decrementItem(cartId,bookId);
	}

	@Override
	public String updatePurchase(int userId, int billId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.updatePurchase(userId, billId);
	}

	@Override
	public int getMaxBillId() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.getMaxBillId();
	}

	@Override
	public String clearPurchase(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.clearPurchase(userId);
	}

	@Override
	public List<Purchase> purchaseHistory(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionDAO.purchaseHistory(userId);
	}

}
