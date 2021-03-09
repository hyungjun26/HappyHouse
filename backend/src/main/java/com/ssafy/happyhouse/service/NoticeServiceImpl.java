package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.Notice;
import com.ssafy.happyhouse.repo.NoticeRepo;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeRepo nrepo;
		
	@Override
	public List<Notice> retrieveNotice() {
		
		return nrepo.selectNotice();
	}
	@Override
	public Notice detailNotice(int noticeNo) {
		return nrepo.detailNotice(noticeNo);
	}

	@Override
	public boolean writeNotice(Notice notice) {
		return nrepo.writeNotice(notice)==1;
	}

	@Override
	public boolean updateNotice(Notice notice) {		
		return nrepo.updateNotice(notice)==1;
	}

	@Override
	public boolean deleteNotice(int noticeNo) {		
		return nrepo.deleteNotice(noticeNo)==1;
	}

}
