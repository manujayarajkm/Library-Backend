package com.example.demo.AdminData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Data.BookMapper;
import com.example.demo.Data.HireMapper;
import com.example.demo.Data.MemberMapper;
import com.example.demo.POJO.Book;
import com.example.demo.POJO.Hire;
import com.example.demo.POJO.User;

@Repository
public class AminDAOImpl implements AdminDAO {
	
	private final static String BOOK_BORROWAL="select hire.hire_id,hire.book_id,hire.user_id,user.name,books.title,hire.`took_date`,hire.`due_date` from hire INNER JOIN books ON hire.`book_id`= books.`book_id`INNER JOIN user ON hire.`user_id`= user.`user_id`;";
	private final static String BOOK_STATUS="SELECT * FROM books";
	private final static String REMOVE_MEMBER="DELETE from user where user_id=?";
	private final static String MEMBER_RETRIEVE="SELECT * FROM user";
	private final static String BLOCK_MEMBER="update user set active='n' where user_id=?";
	private final static String UNBLOCK_MEMBER="update user set active='y' where user_id=?";
	private final static String HIRE_DETAILS="select hire.hire_id,hire.book_id,hire.user_id,user.name,books.title,hire.`took_date`,hire.`due_date` from hire INNER JOIN books ON hire.`book_id`= books.`book_id`INNER JOIN user ON hire.`user_id`= user.`user_id` where hire_id=?";
	
	private static String UPLOADED_FOLDER = "/Users/Manu/Desktop/Angular/Global-Library-FrontEnd/src/assets/images/";

	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public AminDAOImpl() {
		super();
	}

	public AminDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Hire> getAllHires() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Hire> hire=jdbcTemplate.query(BOOK_BORROWAL,new Object[]{},new HireMapper());
		return hire;

	}

	@Override
	public List<User> getAllMembers() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<User> user=jdbcTemplate.query(MEMBER_RETRIEVE,new Object[]{},new MemberMapper());
		return user;
	}

	@Override
	public String RemoveMember(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int rowCount=jdbcTemplate.update(REMOVE_MEMBER,userId);
		if(rowCount>0){
			return "Member has been removed successfully";
		}
		else{
			return "Sorry something went wrong";
		}
	}

	@Override
	public List<Book> getAllBooks() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
			List<Book> book=jdbcTemplate.query(BOOK_STATUS,new Object[]{},new BookMapper());
			return book;

		
	}

	@Override
	public String blockMember(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		int rowCount=jdbcTemplate.update(BLOCK_MEMBER,userId);
		if(rowCount>0){
			return "Selected member has been banned";
		}
		else{
			return "Sorry something bad ";
		}
		
	}

	@Override
	public String unBlockMember(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int rowCount=jdbcTemplate.update(UNBLOCK_MEMBER,userId);
		if(rowCount>0){
			return "Selected member has been unblocked";
		}
		else{
			return "Sorry something bad ";
		}
	}

	@Override
	public String addRemarks(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hire> getHireDetails(int hireTd) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Hire> hire=jdbcTemplate.query(HIRE_DETAILS,new HireMapper(),hireTd);
		return hire;
	}

	

	@Override
	public String addNewBook(String title, String author, String genre, float price, String cover)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveCoverImage(MultipartFile file,String name) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		if (file.isEmpty()) {
			
			System.out.println("Image empty");
            
            return "File is empty";
        }
		try {
			
			System.out.println("File recieved");

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER +name+".jpg");
            Files.write(path, bytes);
            
            System.out.println("File saved "+path);

            return "upload success";

        } catch (IOException e) {
            e.printStackTrace();
            
            return "failed";
        }

       
		
	}

}
