package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseInfoDTO;
import com.ssafy.happyhouse.dto.SidoCodeDTO;
import com.ssafy.happyhouse.service.SelectBoxService;

@RestController
@RequestMapping("/api/select")
public class SelectBoxXController {

	
	static Logger logger = LoggerFactory.getLogger(SelectBoxXController.class);
	
	@Autowired
	SelectBoxService sbservice;
	
	@GetMapping("/sido")
	public ResponseEntity<Map<String, Object>> sido(){
		ResponseEntity<Map<String, Object>> entity = null;
		List<SidoCodeDTO> sidos = null;
		
		try {
			sidos = sbservice.selectSido();
			System.out.println(sidos.toString());

			entity = handleSuccess(sidos);
		}catch(Exception e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@GetMapping("/gugun/{sido}")
	public ResponseEntity<Map<String, Object>> gugun(@PathVariable String sido){
		ResponseEntity<Map<String, Object>> entity = null;
		List<SidoCodeDTO>glists = null;
		try {
			glists = sbservice.selectGugun(sido);
			System.out.println(glists.toString());
			entity = handleSuccess(glists);
		}catch(Exception e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@GetMapping("/dong")
	public ResponseEntity<Map<String, Object>> dong(@RequestParam(value="sido") String sido, @RequestParam(value = "gugun") String gugun){
		ResponseEntity<Map<String, Object>> entity = null;
		List<HouseInfoDTO>dlists = null;
		try {
			dlists = sbservice.selectDong(sido,gugun);
			System.out.println(dlists.toString());
			entity = handleSuccess(dlists);
		}catch(Exception e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@GetMapping("/apt/{dong}")
	public ResponseEntity<Map<String, Object>> apt(@PathVariable String dong){
		ResponseEntity<Map<String, Object>> entity = null;
		System.out.println(dong);
		List<HouseInfoDTO>alists = null;
		try {
			alists = sbservice.selectApt(dong);
			for(int i = 0; i < alists.size(); i++) {
				System.out.println(alists.get(i).toString());
			}
			entity = handleSuccess(alists);
		}catch(Exception e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", true);
		resultMap.put("data", data);
		return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
	}
	
	private ResponseEntity<Map<String, Object>> handleException(Exception e){
		logger.error("예외 발생", e);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", false);
		resultMap.put("data", e.getMessage());
		return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
