package com.example.demo.Data;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.POJO.Book;
import com.example.demo.POJO.Hire;

public class HireMapper implements RowMapper<Hire>{

	@Override
	public Hire mapRow(ResultSet rs, int rowNum) throws SQLException {
		   // TODO Auto-generated method stub
		
		
		
	
			
			  Hire hireRow=new Hire();
			  hireRow.setHireId(rs.getInt(1));
			  hireRow.setBookId(rs.getInt(2));
			  hireRow.setUserId(rs.getInt(3));
			  hireRow.setName(rs.getString(4));
			  hireRow.setTitle(rs.getString(5));
			  java.util.Date date1=rs.getDate(6);
			  java.util.Date date2=rs.getDate(7);
			  hireRow.setTookDate(date1);
			  hireRow.setDueDate(date2);
			  
			  
			  return hireRow;
		  }

}
