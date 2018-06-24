package com.example.demo.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.POJO.Notifications;

public class NotificationsMapper implements RowMapper<Notifications>{
		
		@Override
		public Notifications mapRow(ResultSet rs, int rowNum) throws SQLException {
				   // TODO Auto-generated method stub
				
				
				
			
					
					  Notifications notificationsRow=new Notifications();
					  notificationsRow.setNotificationId(rs.getInt(1));
					  java.util.Date date1=rs.getDate(2);
					  notificationsRow.setNotificationDate(date1);
					  notificationsRow.setMatter(rs.getString(3));
					  notificationsRow.setUserid(rs.getInt(4));
					  java.util.Date date2=rs.getDate(7);
					  notificationsRow.setAckDate(date2);
					  return notificationsRow;
				  }




	

}
