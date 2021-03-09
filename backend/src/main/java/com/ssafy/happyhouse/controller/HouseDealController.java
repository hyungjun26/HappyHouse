package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.SearchInfo;
import com.ssafy.happyhouse.service.HouseDealService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/deal")
public class HouseDealController {

	private static final Logger logger = LoggerFactory.getLogger(HouseDealController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
    @Autowired
	HouseDealService dService;
	
	@ApiOperation(value = "모든 거래정보를 조회하여 반환", response = List.class)
	@GetMapping
	public ResponseEntity<List<HouseDeal>> retrieveDeal() {
		logger.debug("retrieveDeal - 호출");		
		return new ResponseEntity<List<HouseDeal>>(dService.retrieveDeal(), HttpStatus.OK);
	}
	@ApiOperation(value = "모든 거래정보를 조회하여 반환", response = List.class)
	@PostMapping("/count")
	public ResponseEntity<Integer> dealsCount(@RequestBody SearchInfo info) {
		logger.debug("dealsCount - 호출");
		if(info.isAptTrade())info.getType().add(1);
		if(info.isAptRent())info.getType().add(2);
		if(info.isHouseTrade())info.getType().add(3);
		if(info.isHouseRent())info.getType().add(4);
		info.cal();
		return new ResponseEntity<Integer>(dService.dealsCount(info), HttpStatus.OK);
	}
	
	@ApiOperation(value = "모든 거래정보를 조회하여 반환", response = List.class)
	@GetMapping("{no}")
	public ResponseEntity<HouseDeal> retrieveByNo (@PathVariable int no) {
		logger.debug("retrieveByNo - 호출 : " + no);		
		return new ResponseEntity<HouseDeal>(dService.retrieveByNo(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "검색조건에 해당하는 거래리스트 반환", response = List.class)
	@PostMapping("/search")
	public ResponseEntity<List<HouseDeal>> retrieveDealBySearch(@RequestBody SearchInfo info) {
		logger.debug("retrieveDealBySearch - 호출");
		if(info.isAptTrade())info.getType().add(1);
		if(info.isAptRent())info.getType().add(2);
		if(info.isHouseTrade())info.getType().add(3);
		if(info.isHouseRent())info.getType().add(4);
		info.cal();
		logger.debug(info.toString());
		return new ResponseEntity<List<HouseDeal>>(dService.retrieveDealBySearch(info), HttpStatus.OK);
	}
	
	@ApiOperation(value = "같은 조건의 주택 거래리스트 반환", response = List.class)
	@PostMapping("/recent")
	public ResponseEntity<List<HouseDeal>> retrieveRecnetDeal(@RequestBody HouseDeal deal) {		
		logger.debug("retrieveRecnetDeal - 호출");
		logger.debug(deal.toString());
		return new ResponseEntity<List<HouseDeal>>(dService.retrieveRecnetDeal(deal), HttpStatus.OK);
	}
}