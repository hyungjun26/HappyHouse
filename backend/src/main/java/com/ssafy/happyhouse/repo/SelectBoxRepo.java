package com.ssafy.happyhouse.repo;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseInfoDTO;
import com.ssafy.happyhouse.dto.SidoCodeDTO;

public interface SelectBoxRepo {
	List<SidoCodeDTO> selectSido() throws Exception;

	List<SidoCodeDTO> selectGugun(String sido) throws Exception;

	List<HouseInfoDTO> selectDong(String sido, String gugun) throws Exception;

	List<HouseInfoDTO> selectApt(String dong) throws Exception;
	
	SidoCodeDTO selectSidoCode(String sido) throws Exception;
	
	SidoCodeDTO selectGugunCode(String gugun) throws Exception;
}
