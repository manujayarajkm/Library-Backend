package com.example.demo.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Data.BookDAO;
import com.example.demo.POJO.Book;
import com.example.demo.POJO.Hire;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookDAO bookDAO;

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	public String addNewBook(String title, String author, String genre, float price, String cover)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return bookDAO.addNewBook(title, author, genre, price, cover);
	}

	@Override
	public String bookRemove(int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return bookDAO.bookRemove(bookId);
	}

	@Override
	public List<Book> getAllBooks() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return bookDAO.getAllBooks();
	}

	@Override
	public int bookReserve(int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return bookDAO.bookReserve(bookId);
	}

	@Override
	public List<Book> getNewBooks() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return bookDAO.getNewBooks();
	}

	@Override
	public List<Book> searchBooks(String title) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return bookDAO.searchBooks(title);
	}

	@Override
	public List<Book> getSingleBook(int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return bookDAO.getSingleBook(bookId);
	}
	
	
	
	

}
