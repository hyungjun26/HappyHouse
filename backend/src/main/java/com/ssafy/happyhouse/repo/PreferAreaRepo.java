package com.ssafy.happyhouse.repo;

import java.util.List;

import com.ssafy.happyhouse.dto.PreferArea;

public interface PreferAreaRepo {
	List<PreferArea> selectPreferArea(String puserId);
	
	int insertPreferArea(PreferArea prefer);
	
	int updatePreferArea(PreferArea prefer);
	
	int deletePreferArea(int preferNo);
}
