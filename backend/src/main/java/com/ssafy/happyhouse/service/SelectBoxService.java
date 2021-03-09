package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseInfoDTO;
import com.ssafy.happyhouse.dto.SidoCodeDTO;

public interface SelectBoxService {
	List<SidoCodeDTO> selectSido() throws Exception;

	List<SidoCodeDTO> selectGugun(String sido) throws Exception;

	List<HouseInfoDTO> selectDong(String sido, String gugun) throws Exception;

	List<HouseInfoDTO> selectApt(String dong) throws Exception;
}
