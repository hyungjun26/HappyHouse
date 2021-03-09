package com.ssafy.happyhouse.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.HouseInfoDTO;
import com.ssafy.happyhouse.dto.SidoCodeDTO;


@Repository
public class SelectBoxRepoImpl implements SelectBoxRepo{

	public static String ns = "com.ssafy.happyhouse.mapper.SidoCode.";
	
	@Autowired
	SqlSessionTemplate temp;
	
	@Override
	public List<SidoCodeDTO> selectSido() throws Exception {
		
		return temp.selectList(ns+"selectSido");
	}

	@Override
	public List<SidoCodeDTO> selectGugun(String sido) throws Exception {
		SidoCodeDTO stemp = temp.selectOne(ns+"selectSidoCode",sido);
		String code = stemp.getSido_code().substring(0,2);
		System.out.println(code);
		return temp.selectList(ns+"selectGugun", code);
	}

	@Override
	public List<HouseInfoDTO> selectDong(String sido, String gugun) throws Exception {
		SidoCodeDTO stemp = temp.selectOne(ns+"selectSidoCode",sido);
		String sidocode = stemp.getSido_code().substring(0,2);
		SidoCodeDTO tempdto1 = new SidoCodeDTO();
		tempdto1.setSido_code(sidocode);
		tempdto1.setGugun_name(gugun);
		SidoCodeDTO stemp2 = temp.selectOne(ns+"selectGugunCode",tempdto1);
		String guguncode = stemp2.getGugun_code();
		
		return temp.selectList(ns+"selectDong", guguncode);
	}

	@Override
	public List<HouseInfoDTO> selectApt(String dong) throws Exception {
		
		return temp.selectList(ns+"selectApt", dong);
	}

	@Override
	public SidoCodeDTO selectSidoCode(String sido) throws Exception {
		// TODO Auto-generated method stub
		return temp.selectOne(ns+"selectSidoCode",sido);
	}

	@Override
	public SidoCodeDTO selectGugunCode(String gugun) throws Exception {
		// TODO Auto-generated method stub
		return temp.selectOne(ns+"selectGugunCode",gugun);
	}

}
