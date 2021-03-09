package com.ssafy.happyhouse.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
	String title;
	String originallink;
	String link;
	String description;
	String pubDate;
}
