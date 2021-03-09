package com.ssafy.happyhouse.repo;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.HouseInfo;

@Repository
public class HouseInfoRepoImpl implements HouseInfoRepo {

	public static String ns = "com.ssafy.happyhouse.mapper.houseInfo.";
	
	@Autowired
	SqlSessionTemplate temp;
	
	
	@Override
	public List<HouseInfo> searchAllHouse() throws SQLException {
		
		return temp.selectList(ns+"selectAllHouseInfo");
	}

	@Override
	public HouseInfo searchHouseInfo(int no) throws SQLException {
		
		return temp.selectOne(ns+"selectHouseInfo", no);
	}

	@Override
	public int insertHouseInfo(HouseInfo h) throws SQLException {
		
		return temp.insert(ns+"insertHouseInfo", h);
	}

	@Override
	public int updateHouseInfo(int no, String rename) throws SQLException {
		HouseInfo house = new HouseInfo();
		house.setNo(no);
		house.setJibun(rename);
		return temp.update(ns+"updateHouseInfo", house);
		
	}

	@Override
	public int deleteHouseInfo(String name) throws SQLException {
		return temp.delete(ns+"deleteHouseInfo", name);
	}

}
