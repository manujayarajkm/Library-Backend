package com.example.demo.Data;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.POJO.Hire;
import com.example.demo.POJO.History;
import com.example.demo.POJO.User;

public interface MemberDAO {
	
	public String AddMember(String name,String email,String uname,String password,double phone)throws SQLException,ClassNotFoundException;
	public String RemoveMember(int userId)throws SQLException,ClassNotFoundException;
	public User login(String username,String password)throws SQLException,ClassNotFoundException;
	public String checkUsername(String username)throws SQLException,ClassNotFoundException;
	public String encryptPassword(String password)throws SQLException,ClassNotFoundException;
	public String decryptPassword(String password)throws SQLException,ClassNotFoundException;


}
