package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.PreferArea;

public interface PreferAreaService {
	List<PreferArea> retrievePrefer(String puserId);

	boolean insertPrefer(PreferArea prefer);
	
	boolean updatePrefer(PreferArea prefer);
	
	boolean deletePrefer(int preferNo);
}
