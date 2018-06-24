package com.example.demo.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.CORSFIlter;
import com.example.demo.POJO.Book;
import com.example.demo.POJO.Cart;
import com.example.demo.POJO.Hire;
import com.example.demo.POJO.History;
import com.example.demo.POJO.Login;
import com.example.demo.POJO.Notifications;
import com.example.demo.POJO.Purchase;
import com.example.demo.POJO.Review;
import com.example.demo.POJO.User;
import com.example.demo.Service.BookService;
import com.example.demo.Service.MemberService;
import com.example.demo.Service.TransactionService;

@RestController
@RequestMapping(value="/librarycontroller")
public class LibraryController {
	
	@Autowired
	BookService bookService;
	@Autowired
	MemberService memberService;
	@Autowired
	TransactionService transactionService;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(LibraryController.class);
	
	
	
	@RequestMapping(value="/addNewBook/{title}/{author}/{genre}/{price}/{cover}",method=RequestMethod.GET)
	public String addNewBook(@PathVariable String title,@PathVariable String author,@PathVariable String genre,@PathVariable float price,@PathVariable String cover) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		LOGGER.info("Hit Call on Add New Book");
		return bookService.addNewBook(title, author, genre, price, cover);
	}
	
	@RequestMapping(value="/addToCart/{bookId}/{userId}/{title}/{author}/{price}",method=RequestMethod.GET)
	public String addToCart(@PathVariable int bookId,@PathVariable int userId,@PathVariable String title,@PathVariable String author,@PathVariable float price) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		LOGGER.info("Calling Add to Cart Function");
		return transactionService.addToCart(bookId,userId, title, author, price);
	}
	@RequestMapping(value="/removeFromCart/{cartId}/{count}/{bookId}",method=RequestMethod.GET)
	public String removeFromCart(@PathVariable int cartId,@PathVariable int count,@PathVariable int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionService.RemoveFromCart(cartId, count, bookId);
	}
	@RequestMapping(value="/updatePurchase/{userId}/{billId}",method=RequestMethod.GET)
	public String updatePurchase(@PathVariable int userId,@PathVariable int billId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionService.updatePurchase(userId, billId);
	}
	@RequestMapping(value="/incrementItem/{cartid}/{bookId}",method=RequestMethod.GET)
	public String incrementItem(@PathVariable int cartid,@PathVariable int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionService.incrementItem(cartid,bookId);
	}
	@RequestMapping(value="/decrementItem/{cartid}/{bookId}",method=RequestMethod.GET)
	public String decrementItem(@PathVariable int cartid,@PathVariable int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionService.decrementItem(cartid,bookId);
	}
	@RequestMapping(value="/removeBook/{id}",method=RequestMethod.GET)
	public String removeBook(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return bookService.bookRemove(id);
	}
	@RequestMapping(value="/getMaxBillId",method=RequestMethod.GET)
	public int getMaxBillId() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionService.getMaxBillId();
	}
	@RequestMapping(value="/getSingleBook/{id}",method=RequestMethod.GET)
	public List<Book> getSingleBook(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return bookService.getSingleBook(id);
	}
	@RequestMapping(value="/cartReview/{id}",method=RequestMethod.GET)
	public List<Cart> cartReview(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionService.cartReview(id);
	}
	@RequestMapping(value="/purchaseHistory/{id}",method=RequestMethod.GET)
	public List<Purchase> purchseHistory(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionService.purchaseHistory(id);
	}
	@RequestMapping(value="/clearPurchase/{id}",method=RequestMethod.GET)
	public String clearPurchase(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionService.clearPurchase(id);
	}
	
	@RequestMapping(value="/viewAllBooks",method=RequestMethod.GET)
	public List<Book> viewAll() throws ClassNotFoundException, SQLException{
		LOGGER.info("@Controller Fetching all available books");
		
		return bookService.getAllBooks();
		
	}
	
	@RequestMapping(value="/viewNewBooks",method=RequestMethod.GET)
	public List<Book> viewNew() throws ClassNotFoundException, SQLException{
		return bookService.getNewBooks();
		
	}
	@RequestMapping(value="/searchBooks/{title}",method=RequestMethod.GET)
	public List<Book> searchBooks(@PathVariable String title) throws ClassNotFoundException, SQLException{
		System.out.println(title);
		return bookService.searchBooks(title);
		
	}
	
	@RequestMapping(value="/availablity/{id}",method=RequestMethod.GET)
	public int checkAvailability(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return bookService.bookReserve(id);
	}
	
	@RequestMapping(value="/addNewMember/{name}/{email}/{uname}/{password}/{phone}",method=RequestMethod.GET)
	public String addNewBook(@PathVariable String name,@PathVariable String email,@PathVariable String uname,@PathVariable String password,@PathVariable double phone) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println(name);
		System.out.println(uname);
		System.out.println(password);
		System.out.println(email);
		System.out.println(phone);
		return memberService.AddMember(name, email, uname, password, phone);
	}
	
	@RequestMapping(value="/removeMember/{id}",method=RequestMethod.GET)
	public String removeMember(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return memberService.RemoveMember(id);
	}
	
	@RequestMapping(value="/viewAllHires",method=RequestMethod.GET)
	public List<Hire> viewAllHires() throws ClassNotFoundException, SQLException{
		return transactionService.getAllHires();
		
	}
	
	@RequestMapping(value="/addNewHire/{bookId}/{userId}",method=RequestMethod.GET)
	public String bookBorrowal(@PathVariable int bookId,@PathVariable int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println(bookId);
		System.out.println(userId);
		return transactionService.bookBorrowal(bookId, userId);
	}
	
	@RequestMapping(value="/bookReturn/{id}",method=RequestMethod.GET)
	public String bookReturn(@PathVariable int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(id);
		return transactionService.bookReturn(id);
	}
	
	@RequestMapping(value="/checkUsername/{uname}",method=RequestMethod.GET)
	public String checkUsername(@PathVariable String uname) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return memberService.checkUsername(uname);
	}
	@RequestMapping(value="/getUid/{uname}",method=RequestMethod.GET)
	public int getUid(@PathVariable String uname) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return transactionService.getUserId(uname);
	}
	
	@RequestMapping(value="/myHires/{userId}",method=RequestMethod.GET)
	public List<Hire> myHires(@PathVariable int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(userId);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.getMyHires(userId);
	}
	
	@RequestMapping(value="/getMyHistory/{userId}",method=RequestMethod.GET)
	public List<History> getMyHistory(@PathVariable int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(userId);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.getMyHistory(userId);
	}
	
	@RequestMapping(value="/getMyNotifications/{userId}",method=RequestMethod.GET)
	public List<Notifications> getMyNotifications(@PathVariable int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(userId);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.getMyNotifications(userId);
	}
	@RequestMapping(value="/getAckNotifications/{userId}",method=RequestMethod.GET)
	public List<Notifications> getAckNotifications(@PathVariable int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(userId);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.getAcknowledgedNotifications(userId);
	}
	@RequestMapping(value="/getReviews/{bookId}",method=RequestMethod.GET)
	public List<Review> getReviewa(@PathVariable int bookId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(bookId);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.getReviews(bookId);
	}
	@RequestMapping(value="/sendMail",method=RequestMethod.POST,consumes="application/json")
	public String sendMail(@RequestBody User userObj,Model model) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int id=userObj.getUserId();
		String email=userObj.getEmail();
		System.out.println(id);
		System.out.println(email);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.sendMail(id,email);
	}
	@RequestMapping(value="/resetPassword/{userId}/{tempCode}/{passwordNew}",method=RequestMethod.GET)
	public String resetPassword(@PathVariable int userId,@PathVariable int tempCode,@PathVariable String passwordNew) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(userId);
		System.out.println(tempCode);
		System.out.println(passwordNew);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.resetPassword(userId, tempCode, passwordNew);
	}
	@RequestMapping(value="/updatePassword/{userId}/{passwordOld}/{passwordNew}",method=RequestMethod.GET)
	public String updatePassword(@PathVariable int userId,@PathVariable String passwordOld,@PathVariable String passwordNew) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(userId);
		System.out.println(passwordOld);
		System.out.println(passwordNew);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.updatePassword(userId, passwordOld, passwordNew);
	}
	@RequestMapping(value="/updateProfile/{userId}/{name}/{email}/{phone}",method=RequestMethod.GET)
	public String updateProfile(@PathVariable int userId,@PathVariable String name,@PathVariable String email,@PathVariable double phone) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(userId);
		System.out.println(name);
		System.out.println(email);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.updateProfile(userId, name, email, phone);
	}
	
	@RequestMapping(value="/addNotifications/{matter}/{userId}",method=RequestMethod.GET)
	public String addNotifications(@PathVariable String matter,@PathVariable int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(userId);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.addNotifications(matter, userId);
	}
	@RequestMapping(value="/addReview/{body}/{userId}/{bookId}/{rating}",method=RequestMethod.GET)
	public String addReview(@PathVariable String body,@PathVariable int userId,@PathVariable int bookId,@PathVariable int rating) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(userId);
		System.out.println(body);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.addReview(bookId, userId, body,rating);
	}
	@RequestMapping(value="/checkDuplicate/{bookId}/{matter}/{userId}",method=RequestMethod.GET)
	public String checkDuplicate(@PathVariable int bookId,@PathVariable String matter,@PathVariable int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(userId);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.checkDuplicate(bookId, matter, userId);
	}
	@RequestMapping(value="/removeNotifications/{notificationsId}",method=RequestMethod.GET)
	public String removeNotifications(@PathVariable int notificationsId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(notificationsId);
		//System.out.println(transactionService.getMyHires(userId));
		return transactionService.removeNotifications(notificationsId);
	}
	@RequestMapping(value="/addReview",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public @ResponseBody String addReview(@RequestBody Review reviewObj,Model model) throws Exception

	{
	 System.out.println(reviewObj.getBody());
	 System.out.println(reviewObj.getBookId());
	 System.out.println(reviewObj.getUserId());
	 System.out.println(reviewObj.getRating());
	
	return(transactionService.addReview(reviewObj.getBookId(), reviewObj.getUserId(), reviewObj.getBody(), reviewObj.getRating()));
	}
	@RequestMapping(value="/memberLogin",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public @ResponseBody User memberLogin(@RequestBody Login loginObj,Model model) throws Exception

	{
	 System.out.println(loginObj.getUserName());
	 System.out.println(loginObj.getPassword());
	String userName=loginObj.getUserName();
	String password=loginObj.getPassword();
	User user=memberService.login(userName, password);	 
	 	return user;
	}
	
	
	@RequestMapping(value="/getUserId/{username}",method=RequestMethod.GET)
	public int getUserId(@PathVariable String username) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		return memberService.getUserId(username);
	}
	
	
	
	@PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("name") String name) throws IOException,SQLException,ClassNotFoundException{
		
		
		System.out.println(name);
			return transactionService.imageUpload(file,name);
		
    }
	
	
	
	
	
	
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String testing(){
		
		return "working";
	}
}
