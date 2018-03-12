package com.example.demo.Service;

import java.sql.SQLException;

import com.example.demo.POJO.User;

public interface MemberService {
	
	public String AddMember(String name,String email,String uname,String password,double phone)throws SQLException,ClassNotFoundException;
	public String RemoveMember(int userId)throws SQLException,ClassNotFoundException;
	public User login(String username,String password)throws SQLException,ClassNotFoundException;
	public String checkUsername(String username)throws SQLException,ClassNotFoundException;
}
