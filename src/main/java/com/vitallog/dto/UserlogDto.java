package com.vitallog.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserlogDto {
	// 공통 데이터
	private String name;
	private Date exerDate;
	
	// 하루, 기간별, 전체 모든 로그
	private int seq;
	private int exerTime;
	private double kcal;
	private String exercise;

	// 하루 데이터 합계 데이터
	private double totalKcal;
	private BigDecimal totalExerTime;

}
