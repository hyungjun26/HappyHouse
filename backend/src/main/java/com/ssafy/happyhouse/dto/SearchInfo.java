package com.ssafy.happyhouse.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchInfo {
	boolean aptTrade;
	boolean aptRent;
	boolean houseTrade;
	boolean houseRent;
	String key;
	String word;
	int currentPage;
	List<Integer> type=new ArrayList<Integer>();
	int start;
	public void cal() {
		this.start = (this.currentPage-1)*20;
	}
}
