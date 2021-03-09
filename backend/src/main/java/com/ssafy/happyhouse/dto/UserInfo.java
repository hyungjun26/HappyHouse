package com.ssafy.happyhouse.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable, Comparable<UserInfo> {
	private String id;
	private String password;
	private String name;
	private String email;
	private String address;
	private String phone;
	//private String attention;
	private Date regiDate;
	private int grade;


	public UserInfo(String id, String password, String name, String email, String address, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		//this.attention = attention;
		this.grade = 3;
	}

//	public String getAttention() {
//		return attention;
//	}
//
//	public void setAttention(String attention) {
//		this.attention = attention;
//	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}

	@Override
	public String toString() {
		return id + " " + name + " " + password + " " + address + " " + email + " " + phone + " " + regiDate;
	}

	@Override
	public int compareTo(UserInfo o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
