package com.example.demo.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.POJO.Hire;
import com.example.demo.POJO.Review;

public class ReviewMapper implements RowMapper<Review>{


	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
			   // TODO Auto-generated method stub
					
					
					
				
						
						  Review reviewRow=new Review();
						  reviewRow.setBody(rs.getString(1));
						  reviewRow.setName(rs.getString(4));
						  
						  return reviewRow;
}
}