package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferArea {
	int preferNo;
	String puserId;
	String sidoName;
	String gugunName;
	String dongName;
}
