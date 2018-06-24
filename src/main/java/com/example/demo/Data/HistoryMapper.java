package com.example.demo.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.POJO.History;

public class HistoryMapper implements RowMapper<History>{
	
	@Override
	public History mapRow(ResultSet rs, int rowNum) throws SQLException {
			   // TODO Auto-generated method stub
			
			
			
		
				
				  History historyRow=new History();
				  historyRow.setHistoryId(rs.getInt(1));
				  historyRow.setUserId(rs.getInt(2));
				  historyRow.setTitle(rs.getString(3));
				  java.util.Date date1=rs.getDate(4);
				  java.util.Date date2=rs.getDate(5);
				  java.util.Date date3=rs.getDate(6);
				  historyRow.setTookDate(date1);
				  historyRow.setDueDate(date2);
				  historyRow.setReturnDate(date3);

				  
				  return historyRow;
			  }




}
