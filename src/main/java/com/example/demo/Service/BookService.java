package com.example.demo.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.example.demo.POJO.Book;
import com.example.demo.POJO.Hire;

public interface BookService {

	public String addNewBook(String title,String author,String genre,float price,String cover)throws SQLException,ClassNotFoundException;
	public String bookRemove(int bookId) throws SQLException,ClassNotFoundException;
	public List<Book> getAllBooks()throws SQLException,ClassNotFoundException;
	public int bookReserve(int bookId)throws SQLException,ClassNotFoundException;
	public List<Book> getNewBooks()throws SQLException,ClassNotFoundException;
	public List<Book> searchBooks(String title)throws SQLException,ClassNotFoundException;
	public List<Book> getSingleBook(int bookId)throws SQLException,ClassNotFoundException;

	
}
