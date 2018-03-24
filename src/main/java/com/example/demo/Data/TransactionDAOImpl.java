package com.example.demo.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.POJO.Cart;
import com.example.demo.POJO.Hire;
import com.example.demo.POJO.History;
import com.example.demo.POJO.Notifications;
import com.example.demo.POJO.Purchase;
import com.example.demo.POJO.Review;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
	
	
	private final static String BOOK_BORROWAL="select hire.hire_id,hire.book_id,hire.user_id,user.name,books.title,hire.`took_date`,hire.`due_date` from hire INNER JOIN books ON hire.`book_id`= books.`book_id`INNER JOIN user ON hire.`user_id`= user.`user_id`;";
	private final static String ADD_BORROWAL="INSERT INTO hire (book_id,user_id,took_date,due_date) values(?,?,?,?)";
	private final static String BOOK_RETURN="DELETE from hire where hire_id=?";
	private final static String BOOK_RESERVE_TITLE="Select available_nos from books where book_id=?";
	private final static String ADD_BOOKNO="update books set available_nos=? where book_id=?";
	private final static String CHECK_BOOKID="select book_id from hire where hire_id=?";
	private final static String MY_HIRES="select distinct hire.hire_id,hire.book_id,books.title,hire.`took_date`,hire.`due_date` from hire INNER JOIN books ON hire.`book_id`= books.`book_id`INNER JOIN user ON hire.`user_id`= ?";
	private final static String GET_TITLE="select title from books where book_id=?";
	private final static String UPDATE_HISTORY="insert into history(user_id,title,took_date,due_date,return_date) values(?,?,?,?,?)";
	private final static String GET_MY_HISTORY="select * from history where user_id=?";
	private final static String GET_MY_NOTIFICATIONS="select * from notifications where user_id=? && acknowledged='n'";
	private final static String UPDATE_NOTIFICATIONS="insert into notifications(notification_date,matter,user_id) values(?,?,?)";
	private final static String REMOVE_NOTIFICATIONS="UPDATE notifications set acknowledged='y' where notification_id=?";
	private final static String ADD_ACK_DATE="update notifications set Ack_Date=? where notification_id=?";
	private final static String CHECK_DUPLICATE="select notification_id from notifications where book_id=?";
	private final static String UPDATE_NOTIFICATIONS_AFTER_CHECK="insert into notifications(notification_date,matter,user_id,book_id) values(?,?,?,?)";
	private final static String GET_ACK_NOTIFICATIONS="select * from notifications where user_id=? && acknowledged='y'";
	private final static String ADD_REVIEW="INSERT INTO review (body,book_id,user_id,review_date) values(?,?,?,?)";
	private final static String GET_REVIEW="select review.body,books.book_id,books.title,user.name from books,review ,user where books.book_id=review.book_id && review.`user_id`=user.user_id && books.book_id=? order by 1 limit 3";
	private final static String GET_COUNT_REVIEW="select no_of_reviews from books where book_id=?";
	private final static String GET_COUNT_RATINGS="select no_of_ratings from books where book_id=?";
	private final static String UPDATE_REVIEW_COUNT="update books set no_of_reviews=? where book_id=?";
	private final static String UPDATE_RATING_COUNT="update books set no_of_ratings=? where book_id=?";
	private final static String GET_RATING="select rating from books where book_id=?";
	private final static String UPDATE_RATING="update books set rating=? where book_id=?";
	private final static String ADD_TEMP="insert into temp (user_id,temp_code) values (?,?)";
	private final static String GET_TEMP_KEY="select temp_code from temp where user_id=?";
	private final static String REMOVE_TEMP_KEY="delete from temp where user_id=?";
	private final static String GET_EMAIL="select email from user where user_id=?";
	private final static String UPDATE_PASSWORD="update user set password=? where user_id=?";
	private final static String GET_USERID="select user_id from user where uname=?";
	private final static String GET_PASSWORD="select  password from user where user_id=?";
	private final static String UPDATE_PROFILE="update user set name=?,email=?,phone=? where user_id=?";
	private final static String ADD_TO_CART="insert into cart (user_id,title,author,price,book_id) values(?,?,?,?,?)";
	private final static String CHECK_ENTRY="select cart_id from cart where book_id=? && user_id=?";
	private final static String GET_COUNT="select count from cart where cart_id=?";
	private final static String UPDATE_COUNT="update cart set count=? where cart_id=?";
	private final static String GET_CART_DETAILS="select cart_id,user_id,title,author,count,price,book_id,(count*price) as subtotal from cart where user_id=? && count>0";
	private final static String REMOVE_FROM_CART="delete from cart where cart_id=?";
	private final static String ADD_TO_PURCHASE="insert into purchase (user_id,title,author,price,book_id,purchase_date) values(?,?,?,?,?,?)";
	private final static String UPDATE_PURCHASE_COUNT="update purchase set count=? where purchase_id=?";
	private final static String CHECK_PURCHASE_ENTRY="select purchase_id from purchase where book_id=? && user_id=? && bill_id=0";
	private final static String REMOVE_FROM_PURCHASE="delete from purchase where purchase_id=?";
	private final static String GET_PURCHASE_ID="select purchase_id from purchase where book_id=? && bill_id=0";
	private final static String UPDATE_PURCHASE="update purchase set bill_id=? where bill_id=0 && user_id=?";
	private final static String MAX_BILLID="select MAX(bill_id) from purchase";
	private final static String CLEAR_CART="delete from cart where user_id=?";
	private final static String CLEAR_PURCHASE="delete from purchase where user_id=? && bill_id=0";
	private final static String MY_PURCHASES="select purchase_id,title,author,count,price,(count*price) as subtotal,book_id,purchase_date,bill_id from purchase where user_id=? order by purchase_date";
	private final static String RETREIVE_SALT="select salt from user where user_id=?";
	private final static String RETREIVE_PASSWORD="select password from user where user_id=?";
	private final static String INSERT_SALT="update user set salt=? where user_id=?";
	private final static String INSERT_PASSWORD="update user set password=? where user_id=?";

	private static String UPLOADED_FOLDER = "/Users/Manu/Desktop/Angular/Global-Library-FrontEnd/src/assets/profilepictures/";



	@Autowired
	private JavaMailSender sender;

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	public TransactionDAOImpl() {
		super();
	}

	public TransactionDAOImpl(JdbcTemplate jdbcTemplate) {
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
	public String bookBorrowal(int bookId, int userId)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		LocalDate tookDate1=LocalDate.now();
		LocalDate dueDate1=LocalDate.now().plusDays(7);
		System.out.println(LocalDate.now());
		System.out.println(LocalDate.now().plusDays(7));
		int available_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{bookId},Integer.class);
		if(available_nos>0){
		int rowCount=jdbcTemplate.update(ADD_BORROWAL,bookId,userId,java.sql.Date.valueOf(tookDate1),java.sql.Date.valueOf(dueDate1));
		String title=jdbcTemplate.queryForObject(GET_TITLE, new Object[]{bookId},String.class);
		if(rowCount>0){
			
			//int available_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{bookId},Integer.class);
			available_nos--;
			int rowCount2=jdbcTemplate.update(ADD_BOOKNO, available_nos,bookId);
			if(rowCount2>0){
				LocalDate returnDate=LocalDate.now();
				int rCount=jdbcTemplate.update(UPDATE_HISTORY,userId,title,java.sql.Date.valueOf(tookDate1),java.sql.Date.valueOf(dueDate1),java.sql.Date.valueOf(returnDate));
			}
			return "Successfully added to your bag!";
		}
		else{
			
			return  "Sorry coudnt make it";
		}
		}else
		{
			return "Sorry Book is already purchased by somebody else";
		}
		
	}

	@Override
	public List<Hire> getAllHires() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Hire> hire=jdbcTemplate.query(BOOK_BORROWAL,new Object[]{},new HireMapper());
		return hire;

	}

	@Override
	public String bookReturn(int hireId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int bookId=jdbcTemplate.queryForObject(CHECK_BOOKID, new Object[]{hireId},Integer.class);
		int rowCount=jdbcTemplate.update(BOOK_RETURN,hireId);
		if(rowCount>0){
			int available_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{bookId},Integer.class);
			available_nos++;
			int rowCount2=jdbcTemplate.update(ADD_BOOKNO,available_nos,bookId);
			return "Thanks for returning the book back";
		}
		else{
			return "Sorry! something has happened in between";
		}
	}

	@Override
	public List<Hire> getMyHires(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Hire> hire=jdbcTemplate.query(MY_HIRES,new Object[]{userId},new MyHireMApper());
		return hire;
	}

	@Override
	public List<History> getMyHistory(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<History> history=jdbcTemplate.query(GET_MY_HISTORY,new Object[]{userId},new HistoryMapper());
		return history;

	}

	@Override
	public List<Notifications> getMyNotifications(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Notifications> notifications=jdbcTemplate.query(GET_MY_NOTIFICATIONS,new Object[]{userId},new NotificationsMapper());
		return notifications;
	}

	@Override
	public String addNotifications(String matter, int userId)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		LocalDate notificationDate=LocalDate.now();
		int rowCount=jdbcTemplate.update(UPDATE_NOTIFICATIONS,java.sql.Date.valueOf(notificationDate),matter,userId);
		if(rowCount>0){
			return "Added to notification Table";
		}
		else{
			return "Table updation failed";
		}
		
	}

	@Override
	public String removeNotifications(int NotificationId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		LocalDate ackDate=LocalDate.now();
		int rowCount=jdbcTemplate.update(REMOVE_NOTIFICATIONS,NotificationId);
		int rCount=jdbcTemplate.update(ADD_ACK_DATE,ackDate,NotificationId);
		if(rowCount>0 && rCount>0){
			return "Notification acknowledged";
		}
		else{
			return "Acknowledgement failure";
		}

	}

	@Override
	public String checkDuplicate(int bookId,String matter,int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		try{
			int notificationId=jdbcTemplate.queryForObject(CHECK_DUPLICATE, new Object[]{bookId},Integer.class);
			return "Entry Already exixts";
		}catch(EmptyResultDataAccessException e){
		
			LocalDate notificationDate=LocalDate.now();
			int rowCount=jdbcTemplate.update(UPDATE_NOTIFICATIONS_AFTER_CHECK,java.sql.Date.valueOf(notificationDate),matter,userId,bookId);
			if(rowCount>0){
				return "Added to notification Table";
			}
			else{
				return "Table updation failed";
			}
		}

	}

	@Override
	public List<Notifications> getAcknowledgedNotifications(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Notifications> notifications=jdbcTemplate.query(GET_ACK_NOTIFICATIONS,new Object[]{userId},new NotificationsMapper());
		return notifications;
	}

	@Override
	public String addReview(int bookId, int userId, String body,float rating) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		LocalDate reviewDate=LocalDate.now();
		int reviewCount=jdbcTemplate.queryForObject(GET_COUNT_REVIEW, new Object[]{bookId},Integer.class);
		reviewCount++;
		int revCont=jdbcTemplate.update(UPDATE_REVIEW_COUNT,reviewCount,bookId);
		int ratingCount=jdbcTemplate.queryForObject(GET_COUNT_RATINGS, new Object[]{bookId},Integer.class);
		ratingCount++;
		int ratCont=jdbcTemplate.update(UPDATE_RATING_COUNT,ratingCount,bookId);
		float ratingOld=jdbcTemplate.queryForObject(GET_RATING,new Object[]{bookId},Float.class);
		float ratingNew=(rating+ratingOld)/2;
		int rc=jdbcTemplate.update(UPDATE_RATING,ratingNew,bookId);
		int rowCount=jdbcTemplate.update(ADD_REVIEW,body,bookId,userId,java.sql.Date.valueOf(reviewDate));
		if(rowCount>0){
			return "Review successfully submitted";
		}
		else{
			return "Something went wrong";
		}
	}

	@Override
	public List<Review> getReviews(int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Review> review=jdbcTemplate.query(GET_REVIEW,new Object[]{bookId},new ReviewMapper());
		return review;
	}

	@Override
	public String sendMail(int userId,String email) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        System.out.println(n);
        int rowCount3=jdbcTemplate.update(REMOVE_TEMP_KEY,userId);
        try {
        	
        	String emailDb=jdbcTemplate.queryForObject(GET_EMAIL,new Object[]{userId},String.class);
        	System.out.println(email);
        	System.out.println(emailDb);
        	if(emailDb.equals(email)){
			helper.setTo(emailDb);
			helper.setText("Hi,Your Recovery code is given below"+n+"If you didnt request a password recovery contact Admin. Regards,Global Library Team");
	        helper.setSubject("Password Recovery");
	        sender.send(message);
	        int rowCount=jdbcTemplate.update(ADD_TEMP,userId,n);
	        if(rowCount>0){
	        return "Mail Sent Successfully";
	        }
	        else{
	        	return "Coudnt add recovery code to Database"; 
	        }
	        }
        	else{
        		return "Email id is not matching";
        	}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Mail sending failed";
		}
       
        
       
	}

	@Override
	public String resetPassword(int userId, int tempCode,String passwordNew) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int tempDb=jdbcTemplate.queryForObject(GET_TEMP_KEY, new Object[]{userId},Integer.class);
		if(tempCode==tempDb){
			//int rowCount=jdbcTemplate.update(UPDATE_PASSWORD,passwordNew,userId);
			int rowC=generatehash(passwordNew,userId);
			if(rowC==0){
				int rCount=jdbcTemplate.update(REMOVE_TEMP_KEY,userId);
				{
					if(rCount>0){
						return "Password changed sucesssfully";
					}
					else{
						return "Password coudn't update";
					}
				}
			}
			else{
				return "Failed";
			}
		}
		else{
			return "Sorry Check your verfication code once again";
		}
	}

	@Override
	public int getUserId(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		try{
    	int userId=jdbcTemplate.queryForObject(GET_USERID,new Object[]{userName},Integer.class);
		return userId;
		}catch(EmptyResultDataAccessException e){
			return 0;
		}

	}

	@Override
	public String updatePassword(int userId, String oldPassword, String passwordNew)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String passOld="";
		try{
		  passOld=jdbcTemplate.queryForObject(GET_PASSWORD, new Object[]{userId},String.class);
		}catch(EmptyResultDataAccessException e){
			return "User does not exist";
		}
		String pass=generateHashedPass(oldPassword,userId);
		if(passOld.equals(pass)){
			int passNew=generatehash(passwordNew,userId);
			//int rowCount=jdbcTemplate.update(UPDATE_PASSWORD,passNew,userId);
			if(passNew==0){
			return "Password updated successfully";
			}
			else{
				return "Password updation failed";
			}
		}
		return "Wrong password";
	}

	@Override
	public String imageUpload(MultipartFile file,String name) throws SQLException, ClassNotFoundException ,IOException{
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

	@Override
	public String updateProfile(int userId, String name, String email, Double phone)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int rowCount=jdbcTemplate.update(UPDATE_PROFILE,name,email,phone,userId);
		if(rowCount>0){
			return "Profile updated successfully";
		}
		else{
			return "Updation failed";
		}
	}

	@Override
	public String addToCart(int bookId,int userId, String title, String author, float price)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		int available_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{bookId},Integer.class);
		if(available_nos>0){
			try{
			int cartId=jdbcTemplate.queryForObject(CHECK_ENTRY, new Object[]{bookId,userId},Integer.class);
			int purchaseId=jdbcTemplate.queryForObject(CHECK_PURCHASE_ENTRY, new Object[]{bookId,userId},Integer.class);
			System.out.println("Purchase id "+purchaseId);
			int count2=jdbcTemplate.queryForObject(GET_COUNT,new Object[]{cartId},Integer.class);
			count2++;
			int rCount=jdbcTemplate.update(UPDATE_COUNT,count2,cartId);
			int rc=jdbcTemplate.update(UPDATE_PURCHASE_COUNT,count2,purchaseId);
			//int available_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{bookId},Integer.class);
			available_nos--;
			int rowCount2=jdbcTemplate.update(ADD_BOOKNO,available_nos,bookId);
			if(rCount>0){
			return "success";
			}
			else{
				return "failure";
			}
			}catch(EmptyResultDataAccessException e){
				int rowCount=jdbcTemplate.update(ADD_TO_CART,userId,title,author,price,bookId);
				LocalDate purchaseDate=LocalDate.now();
				int rC=jdbcTemplate.update(ADD_TO_PURCHASE,userId,title,author,price,bookId,purchaseDate);
				//int available_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{bookId},Integer.class);
				available_nos--;
				int rowCount2=jdbcTemplate.update(ADD_BOOKNO,available_nos,bookId);
				if(rowCount>0){
					return "added to cart";
				}
				else{
					return "coudnt add to cart";
				}
				
			}
			
		}else{
			return "Sorry this book is currently unavailable ";
		}
		
		
		}

	@Override
	public List<Cart> cartReview(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Cart> cart=jdbcTemplate.query(GET_CART_DETAILS,new Object[]{userId},new CartMapper());
		return cart;
	}

	@Override
	public String RemoveFromCart(int cartId, int count,int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int rowCount=jdbcTemplate.update(REMOVE_FROM_CART,cartId);
		int rc=jdbcTemplate.queryForObject(GET_PURCHASE_ID,new Object[]{bookId},Integer.class);
		int roWC=jdbcTemplate.update(REMOVE_FROM_PURCHASE,rc);
		if(rowCount>0){
			int available_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{bookId},Integer.class);
			System.out.println(available_nos);
			int available_nos2=available_nos+count;
			System.out.println(available_nos2);
			int rowCount2=jdbcTemplate.update(ADD_BOOKNO,available_nos2,bookId);
			if(rowCount2>0){
				return "book table updated";
			}
			else{
				return "coudnt update book table";
			}
			
		}
		else{
			return "coudnt remove from cart";
		}
		
	}

	@Override
	public String incrementItem(int cartId,int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int available_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{bookId},Integer.class);
		if(available_nos>0){
			System.out.println("inside if checked available nos");
		int count2=jdbcTemplate.queryForObject(GET_COUNT,new Object[]{cartId},Integer.class);
		System.out.println("Count from cart"+count2);
		count2++;
		System.out.println("Count incremented "+count2);

		int rCount=jdbcTemplate.update(UPDATE_COUNT,count2,cartId);
		System.out.println("Count updated in cart");
		int rc=jdbcTemplate.queryForObject(GET_PURCHASE_ID,new Object[]{bookId},Integer.class);
		System.out.println("got id from purchase"+rc);

		int rco=jdbcTemplate.update(UPDATE_PURCHASE_COUNT,count2,rc);
		System.out.println("updated purchase count");

		if(rCount>0){
			available_nos--;
			int rowCount2=jdbcTemplate.update(ADD_BOOKNO,available_nos,bookId);
			
			return "success";
		}
		else{
			return "failed";
		}
		}
		else{
			return "no books available";
		}
		
		
	}

	@Override
	public String decrementItem(int cartId,int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int available_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{bookId},Integer.class);
		int count2=jdbcTemplate.queryForObject(GET_COUNT,new Object[]{cartId},Integer.class);
		count2--;
		int rCount=jdbcTemplate.update(UPDATE_COUNT,count2,cartId);
		int rc=jdbcTemplate.queryForObject(GET_PURCHASE_ID,new Object[]{bookId},Integer.class);
		int rco=jdbcTemplate.update(UPDATE_PURCHASE_COUNT,count2,rc);
		if(rCount>0){
			available_nos++;
			int rowCount2=jdbcTemplate.update(ADD_BOOKNO,available_nos,bookId);
			return "success";
		}
		else{
			return "failed";
		}
		
		
	}

	@Override
	public String updatePurchase(int userId, int billId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int rowCount=jdbcTemplate.update(UPDATE_PURCHASE,billId,userId);
		int rowCount2=jdbcTemplate.update(CLEAR_CART,userId);
		if(rowCount2>0){
			return "bill id added to purchase table";
		}
		else{
			return "bill id addition failed";
		}
	}

	@Override
	public int getMaxBillId() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		int billId=jdbcTemplate.queryForObject(MAX_BILLID, new Object[]{},Integer.class);
		return billId;
	}

	@Override
	public String clearPurchase(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Cart> cart=jdbcTemplate.query(GET_CART_DETAILS,new Object[]{userId},new CartMapper());
	
		for(int i=0;i<cart.size();i++){
			int count=cart.get(i).getCount();
			int bookId=cart.get(i).getBookId();
			System.out.println("BookId "+bookId+"Count "+count);
			int available_nos=jdbcTemplate.queryForObject(BOOK_RESERVE_TITLE,new Object[]{bookId},Integer.class);
			System.out.println("Available no before"+available_nos);
			int available_nos2=available_nos+count;
			System.out.println("Available no after"+available_nos2);
			int rowCount2=jdbcTemplate.update(ADD_BOOKNO,available_nos2,bookId);
		}
		
		int rowCount=jdbcTemplate.update(CLEAR_CART,userId);
		int rowCount2=jdbcTemplate.update(CLEAR_PURCHASE,userId);
		if(rowCount>0){
			return "Succes";
		}
		else{
			return "Failed";
		}
	}

	@Override
	public List<Purchase> purchaseHistory(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Purchase> purchase=jdbcTemplate.query(MY_PURCHASES,new Object[]{userId},new PurchaseMapper());
		return purchase;
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

public int generatehash(String pass,int userId){
	
	System.out.println("call came");
	String salt=generateString().toLowerCase();
	System.out.println("salt is "+salt);
	System.out.println("pass is "+pass);
	
	int rowCount=jdbcTemplate.update(INSERT_SALT,salt,userId);
	
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

	int rowCount2=jdbcTemplate.update(INSERT_PASSWORD,str,userId);
	if(rowCount2>0){
		return 0;
	}
	else{
		return 1;
	}
	
}
public String checkPassword(String username,String password){
	
	String salt=jdbcTemplate.queryForObject(RETREIVE_SALT, new Object[]{username},String.class);
	String passfromdb=jdbcTemplate.queryForObject(RETREIVE_PASSWORD, new Object[]{username},String.class);

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
	
public String generateHashedPass(String pass,int userId){
	
	String salt=jdbcTemplate.queryForObject(RETREIVE_SALT, new Object[]{userId},String.class);
	String passfromdb=jdbcTemplate.queryForObject(RETREIVE_PASSWORD, new Object[]{userId},String.class);

	//String hash=generatehash("hello","manu");
	
	String newString=pass+salt;

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
	return str;
	
}

}
