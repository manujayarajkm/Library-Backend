package com.example.demo.Data;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.POJO.Book;
import com.example.demo.POJO.Hire;

@Repository
public class BookDAOImpl implements BookDAO {
	
	
	private final static String BOOK_STATUS="SELECT * FROM books where available_nos>0 ORDER BY 1 desc";
	private final static String GET_NEW_BOOKS="select * from books order by 1 desc limit 3";
	private final static String CHECK_BOOK="SELECT * FROM books where title=?";
	private final static String ADD_BOOK="INSERT INTO books (title,author,genre,price,cover) values(?,?,?,?,?)";
	private final static String DELETE_BOOK="DELETE from books where book_id=?";
	private final static String BOOK_RESERVE="Select available_nos from books where book_id=?";
	private final static String BOOK_RESERVE_TITLE="Select available_nos from books where title=?";
	private final static String ADD_BOOKNO="update books set available_nos=? where title=?";
	private final static String SEARCH_BOOKS="select *from books where title=?";
	private final static String GET_BOOK="SELECT * FROM books where book_id=?";

	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public BookDAOImpl() {
		super();
	}

	public BookDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public String addNewBook(String title, String author, String genre, float price, String cover)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		try{
			int availbe_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{title},Integer.class );
			availbe_nos++;
			int rowCount2=jdbcTemplate.update(ADD_BOOKNO,availbe_nos,title);
			if(rowCount2>0){
				return "Book count has been updated";
			}
			else{
				return "Sorry soomething's broken";
			}
		}catch(EmptyResultDataAccessException e){
			int rowCount=jdbcTemplate.update(ADD_BOOK,title,author,genre,price,cover);
			int rowCount2=jdbcTemplate.update(ADD_BOOKNO,1,title);
			if(rowCount2>0){
				return "New book has been added to the shelf";
			}
			else{
				return "Sorry soomething's broken";
			}
		}
		
	}

	@Override
	public String bookRemove(int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int rowCount=jdbcTemplate.update(DELETE_BOOK,bookId);
		if(rowCount>0){
			return "Book removed from the shelf";
		}
		else{
			return "Sorry soomething's broken";
		}
		
	}

	@Override
	public List<Book> getAllBooks() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Book> book=jdbcTemplate.query(BOOK_STATUS,new Object[]{},new BookMapper());
		return book;

	}
	
	@Override
	public int bookReserve(int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int availbe_nos;
		try{
		availbe_nos=jdbcTemplate.queryForObject(BOOK_RESERVE,new Object[]{bookId},Integer.class );
		}catch(EmptyResultDataAccessException e){
			availbe_nos=0;
			return availbe_nos;
		}
		return availbe_nos;
		
	}

	@Override
	public List<Book> getNewBooks() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Book> book=jdbcTemplate.query(GET_NEW_BOOKS,new Object[]{},new BookMapper());
		return book;
		
	}

	@Override
	public List<Book> searchBooks(String title) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("from dao "+title);
		List<Book> book=jdbcTemplate.query(SEARCH_BOOKS,new BookMapper(),title);
		System.out.println(book);
		return book;
	}

	@Override
	public List<Book> getSingleBook(int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Book> book=jdbcTemplate.query(GET_BOOK,new Object[]{bookId},new BookMapper());

		return book;
	}

}
