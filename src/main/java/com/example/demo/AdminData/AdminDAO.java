package com.example.demo.AdminData;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.POJO.Book;
import com.example.demo.POJO.Hire;
import com.example.demo.POJO.User;

public interface AdminDAO {

	public List<Hire> getAllHires()throws SQLException,ClassNotFoundException;
	public List<User> getAllMembers()throws SQLException,ClassNotFoundException;
	public String RemoveMember(int userId)throws SQLException,ClassNotFoundException;
	public List<Book> getAllBooks()throws SQLException,ClassNotFoundException;
	public String blockMember(int userId)throws SQLException,ClassNotFoundException;
	public String unBlockMember(int userId)throws SQLException,ClassNotFoundException;
	public String addRemarks(int userId)throws SQLException,ClassNotFoundException;
	public List<Hire> getHireDetails(int hireTd)throws SQLException,ClassNotFoundException;

}
