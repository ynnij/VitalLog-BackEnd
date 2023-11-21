package com.vitallog.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogsView {
	@Id
	private int seq;
	private Date exerDate;
	private int exerTime;
	private double kcal;
	private String memberName;
	private String exercise;
	private String memberid;
}
