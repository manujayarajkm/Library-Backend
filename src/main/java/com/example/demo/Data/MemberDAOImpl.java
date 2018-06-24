package com.example.demo.Data;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.POJO.History;
import com.example.demo.POJO.User;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	
	private final static String ADD_MEMBER="INSERT INTO user (name,email,uname,active,phone) values(?,?,?,?,?)";
	private final static String REMOVE_MEMBER="DELETE from user where user_id=?";
	private final static String CHECK_MEMBER="select user_id from user where uname=?";
	private final static String LOGIN_MEMBER="select user_id from user where uname=? && password=? ";
	private final static String RETREIVE_EMAIL="select email from user where user_id=?";
	private final static String RETREIVE_ACTIVE="select active from user where user_id=?";
	private final static String RETREIVE_PHONE="select phone from user where user_id=?";
	private final static String RETREIVE_REMARKS="select remarks from user where user_id=?";
	private final static String RETREIVE_NAME="select name from user where user_id=?";
	private final static String RETREIVE_USERID="select user_id from user where uname=?";
	private final static String RETREIVE_USERNAME="select uname from user where user_id=?";
	private final static String RETREIVE_SALT="select salt from user where uname=?";
	private final static String RETREIVE_PASSWORD="select password from user where uname=?";
	private final static String INSERT_SALT="update user set salt=? where uname=?";
	private final static String INSERT_PASSWORD="update user set password=? where uname=?";


	

	
	@Autowired
	JdbcTemplate jdbctemplate;

	public MemberDAOImpl() {
		super();
	}

	public MemberDAOImpl(JdbcTemplate jdbctemplate) {
		super();
		this.jdbctemplate = jdbctemplate;
	}

	public JdbcTemplate getJdbctemplate() {
		return jdbctemplate;
	}

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	@Override
	public String AddMember(String name, String email, String uname, String password,double phone)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		try{
			int userid=jdbctemplate.queryForObject(CHECK_MEMBER,new Object[]{uname},Integer.class);
			return "User Already Exists";
			
		}catch(EmptyResultDataAccessException e)
		{
			int rowCount=jdbctemplate.update(ADD_MEMBER,name,email,uname,"y",phone);
			if(rowCount>0){
				int result=generatehash(password,uname);
				if(result==0){
				return "Registration Success";
				}
				else{
					return "failed";
				}
				
			}
			else{
				return "Registration Failure";
			}
		}
		
		
	}

	@Override
	public String RemoveMember(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int rowCount=jdbctemplate.update(REMOVE_MEMBER,userId);
		if(rowCount>0){
			return "Member Removed";
		}
		else{
			return "Removal Failed";
		}
	}

	@Override
	public User login(String username, String password) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
	
		try{
			String pass=checkPassword(username,password);
			int userId=jdbctemplate.queryForObject(LOGIN_MEMBER,new Object[]{username,pass},Integer.class);
			String active=jdbctemplate.queryForObject(RETREIVE_ACTIVE,new Object[]{userId},String.class);
			String remarks=jdbctemplate.queryForObject(RETREIVE_REMARKS,new Object[]{userId},String.class);
			String email=jdbctemplate.queryForObject(RETREIVE_EMAIL,new Object[]{userId},String.class);
			String name=jdbctemplate.queryForObject(RETREIVE_NAME,new Object[]{userId},String.class);
			double phone=jdbctemplate.queryForObject(RETREIVE_PHONE,new Object[]{userId},Double.class);
			String uname=jdbctemplate.queryForObject(RETREIVE_USERNAME,new Object[]{userId},String.class);
			User user=new User();
			user.setUserId(userId);
			user.setName(name);
			user.setActive(active);
			user.setEmail(email);
			user.setPhone(phone);
			user.setRemarks(remarks);
			user.setUserName(uname);
			return user;
		}catch(EmptyResultDataAccessException e){
			
			//generatehash("hello","manu");
			//checkPassword("manu","hellojj");
			User user=new User();
			user.setUserId(0);
			return user;
		}
		
	}

	@Override
	public String checkUsername(String username) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		try{
			encryptPassword(username);
			int userid=jdbctemplate.queryForObject(RETREIVE_USERID,new Object[]{username},Integer.class);
			return "Username Exist Please try something else";
		}catch(EmptyResultDataAccessException e){
			return "Username is Available";
		}
	}

	@Override
	public String encryptPassword(String password) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		final int[] keys={1234,564,34,787,6564,8763};
		System.out.println(password);
		String modified="";
		char ch;
		int key=0;
		int length=password.length();
		for(int i=0;i<length;i++){
			if(key>=keys.length-1){
				key=0;
			}
			ch=password.charAt(i);
			ch+=keys[key];
			modified+=ch;
			key++;
		}
		System.out.println(modified);
		decryptPassword(modified);
		return modified;
	}

	@Override
	public String decryptPassword(String password) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		final int[] keys={1234,564,34,787,6564,8763};
		System.out.println(password);
		String modified="";
		char ch;
		int key=0;
		int length=password.length();
		for(int i=0;i<length;i++){
			if(key>=keys.length-1){
				key=0;
			}
			ch=password.charAt(i);
			ch-=keys[key];
			modified+=ch;
			key++;
		}
		System.out.println(modified);
		return modified;
		
	}
	
	public static String generateString() {
		
	        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 18) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;

	    
    }
	
	public int generatehash(String pass,String uname){
		
		System.out.println("call came");
		String salt=generateString().toLowerCase();
		System.out.println("salt is "+salt);
		System.out.println("pass is "+pass);
		
		int rowCount=jdbctemplate.update(INSERT_SALT,salt,uname);
		
		String newString=pass+salt;
		
		System.out.println("new string is "+newString);

		String[] checkarray = new String[newString.length()];
		for(int i=0;i<newString.length();i++){
			checkarray[i]=newString.substring(i, i+1);
		}
		System.out.println(Arrays.toString(checkarray));
		
		Arrays.sort(checkarray);
		System.out.println(Arrays.toString(checkarray));

		String st=Arrays.toString(checkarray);
		System.out.println("hashed value is "+st);
		String str = String.join("", checkarray);
		System.out.println("converted  is "+str);

		int rowCount2=jdbctemplate.update(INSERT_PASSWORD,str,uname);
		if(rowCount2>0){
			return 0;
		}
		else{
			return 1;
		}
		
	}
	public String checkPassword(String username,String password){
		
		String salt=jdbctemplate.queryForObject(RETREIVE_SALT, new Object[]{username},String.class);
		String passfromdb=jdbctemplate.queryForObject(RETREIVE_PASSWORD, new Object[]{username},String.class);

		//String hash=generatehash("hello","manu");
		
		String newString=password+salt;
	
		System.out.println("new string is "+newString);

		String[] checkarray = new String[newString.length()];
		for(int i=0;i<newString.length();i++){
			checkarray[i]=newString.substring(i, i+1);
		}
		System.out.println(Arrays.toString(checkarray));
		
		Arrays.sort(checkarray);
		System.out.println(Arrays.toString(checkarray));
		String str = String.join("", checkarray);

		System.out.println("after conversion "+str);
		if(str.equals(passfromdb)){
			System.out.println("passwords matching ");
		}else{
			System.out.println("not matching");
		}
		return str;
		
	}

	@Override
	public int getUserId(String username) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		int userId=jdbctemplate.queryForObject(RETREIVE_USERID, new Object[]{username},Integer.class);
		return userId;
	}

	
}
