package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.PreferArea;
import com.ssafy.happyhouse.repo.PreferAreaRepo;

@Service
public class PreferAreaServieImpl implements PreferAreaService {

	@Autowired
	private PreferAreaRepo prepo;
		
	@Override
	public List<PreferArea> retrievePrefer(String puserId) {
		
		return prepo.selectPreferArea(puserId);
	}
	
	@Override
	public boolean insertPrefer(PreferArea prefer) {
		return prepo.insertPreferArea(prefer)==1;
	}

	@Override
	public boolean updatePrefer(PreferArea prefer) {		
		return prepo.updatePreferArea(prefer)==1;
	}

	@Override
	public boolean deletePrefer(int preferNo) {		
		return prepo.deletePreferArea(preferNo)==1;
	}

}
