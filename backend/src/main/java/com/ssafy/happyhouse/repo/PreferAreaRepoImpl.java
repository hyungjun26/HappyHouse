package com.ssafy.happyhouse.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.PreferArea;

@Repository
public class PreferAreaRepoImpl implements PreferAreaRepo {

	public static String ns = "com.ssafy.happyhouse.mapper.PreferArea.";
	
	@Autowired
	SqlSessionTemplate temp; 
	
	@Override
	public List<PreferArea> selectPreferArea(String puserId) {
		
		return temp.selectList(ns+"selectPrefer", puserId) ;
	}

	
	@Override
	public int insertPreferArea(PreferArea prefer) {
		
		return temp.insert(ns+"insertPrefer", prefer);
	}

	@Override
	public int updatePreferArea(PreferArea prefer) {
		
		return temp.update(ns+"updatePrefer", prefer);
	}

	@Override
	public int deletePreferArea(int preferNo) {
		
		return temp.delete(ns+"deletePrefer", preferNo);
	}

}
