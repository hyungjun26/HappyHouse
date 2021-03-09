package com.ssafy.happyhouse.controller;

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

import com.ssafy.happyhouse.dto.PreferArea;
import com.ssafy.happyhouse.service.PreferAreaService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/prefer")
public class PreferAreaController {

	private static final Logger logger = LoggerFactory.getLogger(PreferAreaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PreferAreaService preferService;

    @ApiOperation(value = "회원이 가진 선호지역  정보를 반환한다.", response = List.class)
	@GetMapping("{puserId}")
	public ResponseEntity<List<PreferArea>> retrieveBoard(@PathVariable String puserId) throws Exception {
		logger.debug("retrievePrefer - 호출");
		System.out.println(puserId);
		List<PreferArea> plist = preferService.retrievePrefer(puserId);
		System.out.println(plist.toString());
		return new ResponseEntity<List<PreferArea>>(preferService.retrievePrefer(puserId), HttpStatus.OK);
	}
    
    
    @ApiOperation(value = "새로운 유저의 선호지역 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertPrefer(@RequestBody PreferArea prefer) {
		logger.debug("insertPrefer - 호출");
		if (preferService.insertPrefer(prefer)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "preferNo에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{preferNo}")
	public ResponseEntity<String> updatePrefer(@RequestBody PreferArea prefer) {
		logger.debug("updatePrefer - 호출");
		logger.debug("" + prefer);
		
		if (preferService.updatePrefer(prefer)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    @ApiOperation(value = "preferNo에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{preferNo}")
	public ResponseEntity<String> deletePrefer(@PathVariable int preferNo) {
		logger.debug("deleteNotice - 호출");
		
		System.out.println(preferNo);
		if (preferService.deletePrefer(preferNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}