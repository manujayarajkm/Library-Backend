package com.example.demo.AdminService;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.AdminData.AdminDAO;
import com.example.demo.POJO.Book;
import com.example.demo.POJO.Hire;
import com.example.demo.POJO.User;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDAO adminDAO;

	public AdminServiceImpl() {
		super();
	}

	public AdminServiceImpl(AdminDAO adminDAO) {
		super();
		this.adminDAO = adminDAO;
	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	@Override
	public List<Hire> getAllHires() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminDAO.getAllHires();
	}

	@Override
	public List<User> getAllMembers() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminDAO.getAllMembers();
	}

	@Override
	public String RemoveMember(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminDAO.RemoveMember(userId);
	}

	@Override
	public List<Book> getAllBooks() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminDAO.getAllBooks();
	}

	@Override
	public String blockMember(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminDAO.blockMember(userId);
	}

	@Override
	public String unBlockMember(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminDAO.unBlockMember(userId);
	}

	@Override
	public List<Hire> getHireDetails(int hireTd) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminDAO.getHireDetails(hireTd);
	}

	@Override
	public String addNewBook(String title, String author, String genre, float price, String cover)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminDAO.addNewBook(title, author, genre, price, cover);
	}

	@Override
	public String saveCoverImage(MultipartFile file, String name) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminDAO.saveCoverImage(file, name);
	}

}
