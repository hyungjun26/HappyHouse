package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.QnA;
import com.ssafy.happyhouse.repo.QNARepo;

@Service
public class QnAServieImpl implements QnAService {

	@Autowired
	private QNARepo qrepo;
		
	@Override
	public List<QnA> retrieveQnA() {
		
		return qrepo.selectQnA();
	}
	@Override
	public QnA detailQnA(int qnaNo) {
		return qrepo.detailQnA(qnaNo);
	}

	@Override
	public boolean writeQnA(QnA qna) {
		return qrepo.writeQnA(qna)==1;
	}

	@Override
	public boolean updateQnA(QnA qna) {		
		return qrepo.updateQnA(qna)==1;
	}

	@Override
	public boolean deleteQnA(int qnaNo) {		
		return qrepo.deleteQnA(qnaNo)==1;
	}

}
