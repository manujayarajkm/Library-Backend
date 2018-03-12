package com.example.demo.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.POJO.Book;

public class BookMapper implements RowMapper<Book>{
	
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		   // TODO Auto-generated method stub
			  
			  Book bookRow=new Book();
			  bookRow.setBookId(rs.getInt(1));
			  bookRow.setTitle(rs.getString(2));
			  bookRow.setAuthor(rs.getString(3));
			  bookRow.setGenre(rs.getString(4));
			  bookRow.setPrice(rs.getFloat(5));
			  bookRow.setAvilableNo(rs.getInt(6));
			  bookRow.setCover(rs.getString(7));
			  bookRow.setRating(rs.getFloat(8));
			  bookRow.setCountRating(rs.getInt(9));
			  bookRow.setCountReview(rs.getInt(10));
			  
			  
		
		   return bookRow;
		  }

}
