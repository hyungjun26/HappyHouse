package com.ssafy.happyhouse.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
	private int noticeNo; 
	private String noticeTitle; 
	private String noticeContent; 
	private String noticeWriter; 
	private Date noticeDatetime; 
	 
}
