package com.ssafy.happyhouse;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.intercepter.JwtInterceptor;

@SpringBootApplication
public class HappyHouseFinalDemoApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(HappyHouseFinalDemoApplication.class, args);
	}
	
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	// jwtInterceptor를 설치한다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**") // 기본 적용 경로
				.excludePathPatterns(Arrays.asList("/api/user/**"))
				.excludePathPatterns(Arrays.asList("/api/qna/**"))
				.excludePathPatterns(Arrays.asList("/api/select/**"))
				.excludePathPatterns(Arrays.asList("/api/notice/**"))
				.excludePathPatterns(Arrays.asList("/api/prefer/**"))
				.excludePathPatterns(Arrays.asList("/api/deal/**"))
				.excludePathPatterns(Arrays.asList("/api/news/**"));// 적용 제외 경로
	}

	// Interceptor를 이용해서 처리하므로 전역의 Cross origin 처리를 해준다.
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowedHeaders("*")
		.exposedHeaders("jwt-auth-token");
	}
}
