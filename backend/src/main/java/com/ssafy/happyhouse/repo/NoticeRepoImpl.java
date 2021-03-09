package com.ssafy.happyhouse.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.Notice;

@Repository
public class NoticeRepoImpl implements NoticeRepo {

	public static String ns = "com.ssafy.happyhouse.mapper.Notice.";
	
	@Autowired
	SqlSessionTemplate temp; 
	
	@Override
	public List<Notice> selectNotice() {
		
		return temp.selectList(ns+"selectNotice") ;
	}

	@Override
	public Notice detailNotice(int noticeNo) {
		
		return temp.selectOne(ns+"detailNotice", noticeNo);
	}

	@Override
	public int writeNotice(Notice notice) {
		
		return temp.insert(ns+"writeNotice", notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		
		return temp.update(ns+"updateNotice", notice);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		
		return temp.delete(ns+"deleteNotice", noticeNo);
	}

}
