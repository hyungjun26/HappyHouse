package com.ssafy.happyhouse.repo;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.SearchInfo;

public interface HouseDealRepo {
	List<HouseDeal> selectAll();
	int insert(HouseDeal deal);
	int delete(int no);
	HouseDeal searchNo(int no);
	List<HouseDeal> selectByKeword(SearchInfo search);
	HouseDeal selectOne(String id);
	Integer dealsCount(SearchInfo info);
	List<HouseDeal> dealBySearch(SearchInfo info);
	List<HouseDeal> recentDeal(HouseDeal deal);
	
}
