package com.ssafy.happyhouse.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.News;
import com.ssafy.happyhouse.service.NewsService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/news")
public class NewsController {

	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NewsService nService;

   
    
    @ApiOperation(value = "검색어에 해당하는 네이버 검색 정보를 반환한다.", response = List.class)
	@GetMapping("{searchval}")
	public ResponseEntity<List<News>> detailNotice(@PathVariable String searchval) throws Exception {
		logger.debug("newsService - 호출");
		
		 String clientId = "NoFQNK0Y2Qq35yxnXCtx"; //애플리케이션 클라이언트 아이디값"
	     String clientSecret = "kfuoSS3FvQ"; //애플리케이션 클라이언트 시크릿값"

	     String text = null;
	     try {
	         text = URLEncoder.encode(searchval +" 부동산", "UTF-8");
	     } catch (UnsupportedEncodingException e) {
	         throw new RuntimeException("검색어 인코딩 실패",e);
	     }
	     
	     logger.debug(searchval);
	     String apiURL = "https://openapi.naver.com/v1/search/news?sort=sim&query=" + text ;    // json 결과
	     //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

	     Map<String, String> requestHeaders = new HashMap<>();
	     requestHeaders.put("X-Naver-Client-Id", clientId);
	     requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	     String responseBody = nService.getNews(apiURL,requestHeaders);
	     
	     JSONParser parser = new JSONParser();
	     Object obj = parser.parse(responseBody);
	     JSONObject jsonObj = (JSONObject) obj;
	     
	     JSONArray item = (JSONArray) jsonObj.get("items");
	     List<News> nlist = new ArrayList<News>();
	     for(int i = 0; i < item.size(); i++) {
	    	JSONObject object = (JSONObject)item.get(i);
	    	String title = (String) object.get("title");
	    	String originallink = (String) object.get("originallink");
	    	String link = (String) object.get("link");
	    	String description = (String) object.get("description");
	    	String pubDate = (String) object.get("pubDate");
	    	News news = new News(title, originallink,link,description,pubDate);
	    	nlist.add(news);
	     }
	     
		return new ResponseEntity<List<News>>(nlist, HttpStatus.OK);
	}
    
  

}