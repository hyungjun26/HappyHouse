package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.SearchInfo;
import com.ssafy.happyhouse.repo.HouseDealRepo;

@Service
public class HouseDealServiceImpl implements HouseDealService {
	
	@Autowired
	HouseDealRepo dDao;
	
	@Override
	public List<HouseDeal> retrieveDeal() {
		// TODO Auto-generated method stub
		return dDao.selectAll();
	}

	@Override
	public HouseDeal detailDeal(int no) {		
		return dDao.searchNo(no);
	}

	@Override
	public boolean writeDeal(HouseDeal deal) {		
		return dDao.insert(deal)==1;
	}

	@Override
	public boolean updateDeal(HouseDeal deal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDeal(int no) {		
		return dDao.delete(no)==1;
	}

	@Override
	public Integer dealsCount(SearchInfo info) {		
		return dDao.dealsCount(info);
	}

	@Override
	public List<HouseDeal> retrieveDealBySearch(SearchInfo info) {		
		return dDao.dealBySearch(info);
	}

	@Override
	public HouseDeal retrieveByNo(int no) {		
		return dDao.searchNo(no);
	}

	@Override
	public List<HouseDeal> retrieveRecnetDeal(HouseDeal deal) {		
		return dDao.recentDeal(deal);
	}

}
