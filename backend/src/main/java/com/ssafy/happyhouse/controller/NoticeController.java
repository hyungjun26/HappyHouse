package com.ssafy.happyhouse.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.Notice;
import com.ssafy.happyhouse.service.NoticeService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeService noticeService;

    @ApiOperation(value = "모든 공지 게시판 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Notice>> retrieveBoard() throws Exception {
		logger.debug("retrieveNotice - 호출");
		return new ResponseEntity<List<Notice>>(noticeService.retrieveNotice(), HttpStatus.OK);
	}
    
    @ApiOperation(value = "noticeNo에 해당하는 글의 정보를 반환한다.", response = List.class)
	@GetMapping("{noticeNo}")
	public ResponseEntity<Notice> detailNotice(@PathVariable int noticeNo) throws Exception {
		logger.debug("detailNotice - 호출");
		return new ResponseEntity<Notice>(noticeService.detailNotice(noticeNo), HttpStatus.OK);
	}
    
    @ApiOperation(value = "새로운 공지사항 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeNotice(@RequestBody Notice notice) {
		logger.debug("writeNotice - 호출");
		Date date = new Date();
		notice.setNoticeDatetime(date);
		if (noticeService.writeNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "noticeNo에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{noticeNo}")
	public ResponseEntity<String> updateNotice(@RequestBody Notice notice) {
		logger.debug("updateNotice - 호출");
		logger.debug("" + notice);
		
		if (noticeService.updateNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    @ApiOperation(value = "noticeNo에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{noticeNo}")
	public ResponseEntity<String> deleteNotice(@PathVariable int noticeNo) {
		logger.debug("deleteNotice - 호출");
		if (noticeService.deleteNotice(noticeNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}