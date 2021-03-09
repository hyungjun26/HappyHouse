package com.ssafy.happyhouse.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.QnA;

@Repository
public class QNARepoImpl implements QNARepo {

	public static String ns = "com.ssafy.happyhouse.mapper.QNA.";
	
	@Autowired
	SqlSessionTemplate temp; 
	
	@Override
	public List<QnA> selectQnA() {
		
		return temp.selectList(ns+"selectQnA") ;
	}

	@Override
	public QnA detailQnA(int qnaNo) {
		
		return temp.selectOne(ns+"detailQnA", qnaNo);
	}

	@Override
	public int writeQnA(QnA qna) {
		
		return temp.insert(ns+"writeQnA", qna);
	}

	@Override
	public int updateQnA(QnA qna) {
		
		return temp.update(ns+"updateQnA", qna);
	}

	@Override
	public int deleteQnA(int qnaNo) {
		
		return temp.delete(ns+"deleteQnA", qnaNo);
	}

}
