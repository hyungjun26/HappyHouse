package com.ssafy.happyhouse.repo;

import java.util.List;

import com.ssafy.happyhouse.dto.QnA;

public interface QNARepo {
	List<QnA> selectQnA();
	
	QnA detailQnA(int qnaNo);

	int writeQnA(QnA qna);
	
	int updateQnA(QnA qna);
	
	int deleteQnA(int qnaNo);
}
