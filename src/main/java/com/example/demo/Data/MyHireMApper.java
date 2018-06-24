package com.example.demo.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.POJO.Hire;

public class MyHireMApper implements RowMapper<Hire> {
	
@Override
public Hire mapRow(ResultSet rs, int rowNum) throws SQLException {
			   // TODO Auto-generated method stub
			
			
			
		
				
				  Hire hireRow=new Hire();
				  hireRow.setHireId(rs.getInt(1));
				  hireRow.setBookId(rs.getInt(2));
				  hireRow.setTitle(rs.getString(3));
				  java.util.Date date1=rs.getDate(4);
				  java.util.Date date2=rs.getDate(5);
				  hireRow.setTookDate(date1);
				  hireRow.setDueDate(date2);
				  
				  
				  return hireRow;
			 
}

}
