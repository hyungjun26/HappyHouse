package com.ssafy.happyhouse.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.UserInfo;
import com.ssafy.happyhouse.service.JwtService;
import com.ssafy.happyhouse.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins= {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Slf4j
public class UserRestController {
	

	static Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/signin")
	public ResponseEntity<Map<String, Object>> signin(@RequestBody UserInfo user, HttpServletResponse res){
		Map<String, Object>resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			
			UserInfo loginUser = userService.login(user.getId(), user.getPassword());
			System.out.println(loginUser.toString());
			//로그인 성공했다면 토큰을 생성한다.
			String token = jwtService.create(loginUser);
			System.out.println(token.toString());
			//토큰 정보는 request의 헤더로 보내고 나머지는 Map 담아주자.
			res.setHeader("jwt-auth-token", token);
			//resultMap.put("auth_token", token);
			
			resultMap.put("status", true);
			resultMap.put("data", loginUser);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			log.error("로그인 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	@PostMapping("/user/join")
	public ResponseEntity<Map<String, Object>> join( @RequestBody UserInfo joinUser) {
		ResponseEntity<Map<String, Object>> entity = null;
		UserInfo user = new UserInfo();
		Date date = new Date();
		System.out.println("join");
		user.setId(joinUser.getId()); user.setPassword(joinUser.getPassword());
		user.setName(joinUser.getName()); user.setEmail(joinUser.getEmail()); user.setAddress(joinUser.getAddress()); user.setPhone(joinUser.getPhone());
		//user.setAttention(joinUser.getAttention());
		user.setRegiDate(date);
		try {
			int result = userService.registerUser(user);
			user = userService.selectById(joinUser.getId());
			entity = handleSuccess(result);
		} catch(Exception e) {
			e.printStackTrace();
			entity = handleException(e);
		}
		return entity;
	}
	@DeleteMapping("/user/withdraw/{id}")
	public ResponseEntity<Map<String, Object>>  withdraw(Model model, @PathVariable String id) {
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			System.out.println(id);
			int result = userService.deleteUser(id);
			entity = handleSuccess(result);
		}catch(Exception e) {
			e.printStackTrace();
			entity = handleException(e);
		}
		return entity;
	}
	
	@PutMapping("/user/modify")
	public ResponseEntity<Map<String, Object>>  modify(@RequestBody UserInfo user) {
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			int result = userService.modifyUser(user);
			entity = handleSuccess(result);
		} catch(Exception e) {
			e.printStackTrace();
			entity = handleException(e);
		}
		return entity;
	}
	
	@GetMapping("/user/findpwd")
	public ResponseEntity<Map<String, Object>> findpwd(@RequestParam(value="id") String id, @RequestParam(value = "name") String name) {
		ResponseEntity<Map<String, Object>> entity = null;
		System.out.println("id: "+id + " name: "+name);
		try {
			String password = userService.findPwd(id,name);
			System.out.println(password);
			entity = handleSuccess(password);
		} catch(Exception e) {
			e.printStackTrace();
			entity= handleException(e);
		}
		return entity;
	}
	@PostMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody UserInfo user){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			// 사용자에 전달할 정보
			
			// 보너스로 토큰에 담긴 정보도 전달해보자. 서버에서 토큰을 사용하는 방법임을 알수 있다.
			
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			
			resultMap.put("status", true);
			resultMap.put("info", "강명훈의 실험 서버");
			resultMap.put("request_body", user);
			status = HttpStatus.ACCEPTED;
		}catch(RuntimeException e) {
			log.error("정보 조회 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
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
