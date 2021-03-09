package com.ssafy.happyhouse.repo;

import java.util.List;

import com.ssafy.happyhouse.dto.Notice;

public interface NoticeRepo {
	List<Notice> selectNotice();
	
	Notice detailNotice(int noticeNo);

	int writeNotice(Notice notice);
	
	int updateNotice(Notice notice);
	
	int deleteNotice(int noticeNo);
}
