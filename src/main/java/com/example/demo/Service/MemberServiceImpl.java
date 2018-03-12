package com.example.demo.Service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Data.MemberDAO;
import com.example.demo.POJO.User;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDAO memberDAO;
	
	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	public MemberServiceImpl(MemberDAO memberDAO) {
		super();
		this.memberDAO = memberDAO;
	}

	@Override
	public String AddMember(String name, String email, String uname, String password, double phone)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return memberDAO.AddMember(name, email, uname, password, phone);
	}

	@Override
	public String RemoveMember(int userId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return memberDAO.RemoveMember(userId);
	}

	@Override
	public User login(String username, String password) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return memberDAO.login(username, password);
	}

	@Override
	public String checkUsername(String username) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return memberDAO.checkUsername(username);
	}

}
