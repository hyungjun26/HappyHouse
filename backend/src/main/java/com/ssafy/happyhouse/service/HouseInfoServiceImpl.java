package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.repo.HouseInfoRepo;

@Service
public class HouseInfoServiceImpl implements HouseInfoService {

	@Autowired
	HouseInfoRepo hiRepo;
	
	@Override
	public List<HouseInfo> searchAll() {
		// TODO Auto-generated method stub
		try {
			return hiRepo.searchAllHouse();
		}catch(SQLException e) {
			throw new HappyHouseException("주택 정보 조회중 오류 발생");
		}
		
	}

	@Override
	public HouseInfo search(int no) {
		
		try {
			HouseInfo info = hiRepo.searchHouseInfo(no);
			if(info == null) {
				throw new HappyHouseException(String.format("아파트 번호 %d가 없습니다.", no));
			}
			return info;
		}catch(SQLException e) {
			throw new HappyHouseException("주택 정보 조회 중 오류발생");
		}
	}

	@Override
	public void insertHouseInfo(HouseInfo h) {
		try {
			hiRepo.insertHouseInfo(h);
		}catch(SQLException e) {
			throw new HappyHouseException("주택 정보 입력 중 오류발생");
		}
		
	}

	@Override
	public void updateHouseInfo(int no, String rename) {
		try {
			hiRepo.updateHouseInfo(no, rename);
		}catch(SQLException e) {
			throw new HappyHouseException("주택 정보 수정 중 오류발생");
		}
		
	}

	@Override
	public void deleteHouseInfo(String name) {
		try {
			hiRepo.deleteHouseInfo(name);
		}catch(SQLException e) {
			throw new HappyHouseException("주택 정보 삭제 중 오류발생");
		}
		
	}

}
