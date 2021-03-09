package com.ssafy.happyhouse.repo;

import java.sql.SQLException;

import com.ssafy.happyhouse.dto.UserInfo;

public interface UserInfoRepo {
	public UserInfo login(String userid, String userpwd) throws SQLException;

	public UserInfo getUser(String id);

	public int insert(UserInfo user);

	public String findPwd(String id, String name);

	public int modifyUser(UserInfo user);

	public int deleteUser(String id);
}
