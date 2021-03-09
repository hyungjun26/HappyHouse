package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.Notice;

public interface NoticeService {
	List<Notice> retrieveNotice();
	
	Notice detailNotice(int noticeNo);

	boolean writeNotice(Notice notice);
	
	boolean updateNotice(Notice notice);
	
	boolean deleteNotice(int noticeNo);
}
