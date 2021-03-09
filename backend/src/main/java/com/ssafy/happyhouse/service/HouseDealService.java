package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.SearchInfo;

public interface HouseDealService {
	List<HouseDeal> retrieveDeal();

	HouseDeal detailDeal(int no);

	boolean writeDeal(HouseDeal deal);
	
	boolean updateDeal(HouseDeal deal);
	
	boolean deleteDeal(int no);

	Integer dealsCount(SearchInfo info);

	List<HouseDeal> retrieveDealBySearch(SearchInfo info);

	HouseDeal retrieveByNo(int no);

	List<HouseDeal> retrieveRecnetDeal(HouseDeal deal);
}
