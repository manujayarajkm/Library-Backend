package com.example.demo.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.POJO.Cart;

public class CartMapper implements RowMapper<Cart>{

	@Override
	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		   // TODO Auto-generated method stub
		
		
		
	
			 Cart cartRow=new Cart();
			 cartRow.setCartId(rs.getInt(1));
			 cartRow.setUserId(rs.getInt(2));
			 cartRow.setTitle(rs.getString(3));
			 cartRow.setAuthor(rs.getString(4));
			 cartRow.setCount(rs.getInt(5));
			 cartRow.setPrice(rs.getFloat(6));
			 cartRow.setBookId(rs.getInt(7));
			 cartRow.setSubTotal(rs.getFloat(8));
			 return cartRow;
			
			
			
			  
		  }

}
