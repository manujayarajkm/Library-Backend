package com.example.demo.AdminController;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AdminService.AdminService;
import com.example.demo.POJO.Book;
import com.example.demo.POJO.Hire;
import com.example.demo.POJO.User;

@RestController
@RequestMapping(value="/adminController")
public class AdminController {
	
	@Autowired
	AdminService adminService;

	@RequestMapping(value="/removeMember/{id}",method=RequestMethod.GET)
	public String removeMember(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminService.RemoveMember(id);
	}
	
	@RequestMapping(value="/viewAllHires",method=RequestMethod.GET)
	public List<Hire> viewAllHires() throws ClassNotFoundException, SQLException{
		System.out.println("Call made");
		return adminService.getAllHires();
		
	}
	
	@RequestMapping(value="/viewAllBooks",method=RequestMethod.GET)
	public List<Book> viewAll() throws ClassNotFoundException, SQLException{
		return adminService.getAllBooks();
		
	}
	
	@RequestMapping(value="/getHireDetails/{id}",method=RequestMethod.GET)
	public List<Hire> getHireDetails(@PathVariable int id) throws ClassNotFoundException, SQLException{
		return adminService.getHireDetails(id);
		
	}
	
	@RequestMapping(value="/viewAllMembers",method=RequestMethod.GET)
	public List<User> viewAllMembers() throws ClassNotFoundException, SQLException{
		return adminService.getAllMembers();
		
	}
	
	@RequestMapping(value="/blockMember/{id}",method=RequestMethod.GET)
	public String blockMember(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminService.blockMember(id);
	}
	@RequestMapping(value="/unblockMember/{id}",method=RequestMethod.GET)
	public String unBlockMember(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return adminService.unBlockMember(id);
	}

}
