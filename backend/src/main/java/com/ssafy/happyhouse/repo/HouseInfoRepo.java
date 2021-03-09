package com.ssafy.happyhouse.repo;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.HouseInfo;

public interface HouseInfoRepo {
	public List<HouseInfo> searchAllHouse() throws SQLException;
	
	public HouseInfo searchHouseInfo(int no) throws SQLException;
	
	public int insertHouseInfo(HouseInfo h) throws SQLException;
	
	public int updateHouseInfo(int no, String rename) throws SQLException;
	
	public int deleteHouseInfo(String name) throws SQLException;
}
