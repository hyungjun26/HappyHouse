package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.UserInfo;

public interface UserService {

	public UserInfo login(String userid, String userpwd) throws Exception;

	public int registerUser(UserInfo user) throws Exception;

	public UserInfo selectById(String id) throws Exception;

	public String findPwd(String id, String name);

	public int modifyUser(UserInfo user);

	public int deleteUser(String id);
	
}
