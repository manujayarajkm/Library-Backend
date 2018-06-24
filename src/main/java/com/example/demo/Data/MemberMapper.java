package com.example.demo.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.POJO.Book;
import com.example.demo.POJO.User;

public class MemberMapper implements RowMapper<User>{
	
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		   // TODO Auto-generated method stub
			  
			  User userRow=new User();
			  userRow.setUserId(rs.getInt(1));
			  userRow.setName(rs.getString(2));
			  userRow.setEmail(rs.getString(3));
			  userRow.setActive(rs.getString(4));
			  userRow.setRemarks(rs.getString(5));
			  userRow.setPhone(rs.getDouble(6));
			  userRow.setUserName(rs.getString(7));
			  userRow.setPassword(rs.getString(8));
			  
			  
			  
		
		   return userRow;
		  }

}
