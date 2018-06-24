package com.example.demo.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.POJO.Purchase;

public class PurchaseMapper implements RowMapper<Purchase>{

	@Override
	public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
		   // TODO Auto-generated method stub
		
		
		
	
			 Purchase cartRow=new Purchase();
			 cartRow.setPurchaseId(rs.getInt(1));
			 cartRow.setTitle(rs.getString(2));
			 cartRow.setAuthor(rs.getString(3));
			 cartRow.setCount(rs.getInt(4));
			 cartRow.setPrice(rs.getFloat(5));
			 cartRow.setSubTotal(rs.getFloat(6));
			 cartRow.setBookId(rs.getInt(7));
			  java.util.Date date1=rs.getDate(8);
			  cartRow.setPurchaseDate(date1);
			 cartRow.setBillId(rs.getInt(9));
			 return cartRow;
			
			
			
			  
		  }

}
