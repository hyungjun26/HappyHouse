package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseInfo;

public interface HouseInfoService {
	public List<HouseInfo> searchAll();
	
	public HouseInfo search(int no);
	
	public void insertHouseInfo(HouseInfo h);
	
	public void updateHouseInfo(int no, String rename);
	
	public void deleteHouseInfo(String name);
}
