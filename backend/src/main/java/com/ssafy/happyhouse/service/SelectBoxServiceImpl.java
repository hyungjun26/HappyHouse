package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.HouseInfoDTO;
import com.ssafy.happyhouse.dto.SidoCodeDTO;
import com.ssafy.happyhouse.repo.SelectBoxRepo;

@Service
public class SelectBoxServiceImpl implements SelectBoxService{

	@Autowired
	SelectBoxRepo sbRepo;
	
	@Override
	public List<SidoCodeDTO> selectSido() throws Exception {
		
		return sbRepo.selectSido();
	}

	@Override
	public List<SidoCodeDTO> selectGugun(String sido) throws Exception {
		
		return sbRepo.selectGugun(sido);
	}

	@Override
	public List<HouseInfoDTO> selectDong(String sido, String gugun) throws Exception {
		
		return sbRepo.selectDong(sido, gugun);
	}

	@Override
	public List<HouseInfoDTO> selectApt(String dong) throws Exception {
		
		return sbRepo.selectApt(dong);
	}

}
