package com.ssafy.happyhouse.repo;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.UserInfo;

@Repository
public class UserInfoRepoImpl implements UserInfoRepo {

	public static String ns = "com.ssafy.happyhouse.mapper.UserInfo.";
	
	@Autowired
	SqlSessionTemplate temp;
	
	@Override
	public UserInfo login(String userid, String userpwd) throws SQLException {
		UserInfo user = new UserInfo();
		user.setId(userid);
		user.setPassword(userpwd);
		return temp.selectOne(ns+"login", user);
	}

	@Override
	public UserInfo getUser(String id) {
		
		return temp.selectOne(ns+"getUser", id);
	}

	@Override
	public int insert(UserInfo user) {
		
		return temp.insert(ns+"insertUser", user);
	}

	@Override
	public String findPwd(String id, String name) {
		UserInfo user = new UserInfo();
		user.setName(name);
		user.setId(id);
		return temp.selectOne(ns+"findPwd", user);
	}

	@Override
	public int modifyUser(UserInfo user) {
		
		return temp.update(ns+"updateUser", user);
	}

	@Override
	public int deleteUser(String id) {
		
		return temp.delete(ns+"deleteUser", id);
	}

}
