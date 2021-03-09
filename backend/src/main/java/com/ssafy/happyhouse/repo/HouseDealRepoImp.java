package com.ssafy.happyhouse.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.SearchInfo;

@Repository
public class HouseDealRepoImp implements HouseDealRepo{

	
	public static String ns = "com.ssafy.happyhouse.repo.HouseDealRepo.";
	
	@Autowired
	SqlSessionTemplate temp;
	
	@Override
	public List<HouseDeal> selectAll() {
		// TODO Auto-generated method stub
		return temp.selectList(ns+"selectAll");
	}

	@Override
	public int insert(HouseDeal deal) {
		// TODO Auto-generated method stub
		return temp.insert(ns+"insert", deal);
	}

	@Override
	public int delete(int no) {
		// TODO Auto-generated method stub
		return temp.delete(ns+"delete", no);
	}

	@Override
	public HouseDeal searchNo(int no) {
		// TODO Auto-generated method stub
		return temp.selectOne(ns+"searchNo", no);
	}

	@Override
	public List<HouseDeal> selectByKeword(SearchInfo search) {
		// TODO Auto-generated method stub
		return temp.selectList(ns+"dealBySearch", search);
	}

	@Override
	public HouseDeal selectOne(String id) {
		// TODO Auto-generated method stub
		return temp.selectOne(ns+"searchNo", id);
	}

	@Override
	public Integer dealsCount(SearchInfo info) {
		// TODO Auto-generated method stub
		
		return temp.selectOne(ns+"dealsCount",info);
	}

	@Override
	public List<HouseDeal> dealBySearch(SearchInfo info) {
		// TODO Auto-generated method stub
		return temp.selectList(ns+"dealBySearch", info);
	}

	@Override
	public List<HouseDeal> recentDeal(HouseDeal deal) {
		// TODO Auto-generated method stub
		return temp.selectList(ns+"recentDeal", deal);
	}

}
