package com.ssafy.happyhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.UserInfo;
import com.ssafy.happyhouse.repo.UserInfoRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserInfoRepo uRepo;
	
	@Override
	public UserInfo login(String userid, String userpwd) throws Exception {
		if(userid == null || userpwd == null)
			return null;
		return uRepo.login(userid, userpwd);
	}

	@Override
	public int registerUser(UserInfo user) throws Exception {
		
		return uRepo.insert(user);
	}

	@Override
	public UserInfo selectById(String id) throws Exception {
		
		return uRepo.getUser(id);
	}

	@Override
	public String findPwd(String id, String name) {
		
		return uRepo.findPwd(id, name);
	}

	@Override
	public int modifyUser(UserInfo user) {
		
		return uRepo.modifyUser(user);
	}

	@Override
	public int deleteUser(String id) {
		
		return uRepo.deleteUser(id);
	}

}
